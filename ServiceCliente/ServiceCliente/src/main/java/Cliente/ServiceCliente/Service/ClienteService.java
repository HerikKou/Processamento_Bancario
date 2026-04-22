package Cliente.ServiceCliente.Service;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Cliente.ServiceCliente.Config.TokenConfig;
import Cliente.ServiceCliente.DTO.CadastroDTO;
import Cliente.ServiceCliente.DTO.ClienteEventoDTO;
import Cliente.ServiceCliente.DTO.ClienteRespostaDTO;
import Cliente.ServiceCliente.DTO.LoginDTO;
import Cliente.ServiceCliente.DTO.LoginRespostaDTO;
import Cliente.ServiceCliente.Exceptions.*;
import Cliente.ServiceCliente.Model.Cliente;
import Cliente.ServiceCliente.Model.Role;
import Cliente.ServiceCliente.Repository.ClienteRepository;

@Service
public class ClienteService {
        private final ClienteRepository clienteRepository;
        private final PasswordEncoder passwordEncoder;
        private final KafkaTemplate<String, ClienteEventoDTO> kafkaTemplate;
        private final TokenConfig tokenConfig;
        private final AuthenticationManager authenticationManager;
    public ClienteService(ClienteRepository clienteRepository, PasswordEncoder passwordEncoder,
     KafkaTemplate<String, ClienteEventoDTO> kafkaTemplate, TokenConfig tokenConfig, AuthenticationManager authenticationManager) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.kafkaTemplate = kafkaTemplate;
        this.tokenConfig = tokenConfig;
        this.authenticationManager = authenticationManager;
     }
     public ClienteRespostaDTO registryClient(CadastroDTO cadastro) {
        String email = verifyRegistration(cadastro.getEmail(), cadastro.getCpf());
        String cpf = verifyCPFwithRegex(cadastro.getCpf());
        String telefone = verifyPhoneWithRegex(cadastro.getPhone());
        Cliente cliente = new Cliente();
        cliente.setName(cadastro.getName());
        cliente.setEmail(email);
        cliente.setCpf(cpf);
        cliente.setPhone(telefone);
        cliente.setPassword(passwordEncoder.encode(cadastro.getPassword()));
        cliente.setRole(Role.USER);
        Cliente savedCliente = clienteRepository.save(cliente);
        try {
        kafkaTemplate.send("cliente_cadastrado", savedCliente.getId().toString(),new ClienteEventoDTO(savedCliente.getId(), savedCliente.getName(), savedCliente.getEmail()));
    } catch (Exception e) {
        System.out.println("Kafka indisponível, evento não publicado: " + e.getMessage());
    }
        return new ClienteRespostaDTO(savedCliente.getId(), savedCliente.getName(), savedCliente.getEmail(),savedCliente.getPhone());
     }
    private String verifyCPFwithRegex(String cpf) {
        String regex = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
        if (cpf.matches(regex)) {
            return cpf;
        } else {
            throw new InvalidDataException("CPF must be in the format XXX.XXX.XXX-XX");
        }
    }

    private String verifyPhoneWithRegex(String telefone) {
        String regex = "\\(\\d{2}\\) \\d{4,5}-\\d{4}";
        if (telefone.matches(regex)) {
            return telefone;
        } else {
            throw new InvalidDataException("Phone number must be in the format (XX) XXXXX-XXXX ");
        }
    }

    private String verifyRegistration(String email, String cpf){
        if(clienteRepository.existsByEmail(email)){
            throw new AlreadyExistsException("Email already exists");
        }
        if(clienteRepository.existsByCpf(cpf)){
            throw new AlreadyExistsException("CPF already exists");
        }
        return email;

    }
    public LoginRespostaDTO login(LoginDTO dto) {
    UsernamePasswordAuthenticationToken usernameAndPassword =
            new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());

    Authentication authentication = authenticationManager.authenticate(usernameAndPassword);

    Cliente cliente = (Cliente) authentication.getPrincipal();

    String token = tokenConfig.generatedToken(cliente);

    return new LoginRespostaDTO(token, cliente.getEmail(), cliente.getRole().name());
}

public List<ClienteRespostaDTO> getAllClients() {
    List<Cliente> clientes = clienteRepository.findAll();
    return clientes.stream()
            .map(cliente -> new ClienteRespostaDTO(cliente.getId(), cliente.getName(), cliente.getEmail(), cliente.getPhone()))
            .toList();
}}
