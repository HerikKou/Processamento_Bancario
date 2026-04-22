package Cliente.ServiceCliente;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import Cliente.ServiceCliente.DTO.CadastroDTO;
import Cliente.ServiceCliente.DTO.ClienteEventoDTO;
import Cliente.ServiceCliente.DTO.ClienteRespostaDTO;
import Cliente.ServiceCliente.Model.Cliente;
import Cliente.ServiceCliente.Repository.ClienteRepository;
import Cliente.ServiceCliente.Service.ClienteService;

@ExtendWith(MockitoExtension.class)
public class RegistryClientTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private KafkaTemplate<String, ClienteEventoDTO> kafkaTemplate;

    @InjectMocks
    private ClienteService clienteService;

    private CadastroDTO cadastroDTO;
    private Cliente clienteSalvo;

    @BeforeEach
    void setUp() {
        cadastroDTO = new CadastroDTO();
        cadastroDTO.setName("John Doe");
        cadastroDTO.setEmail("john.doe@example.com");
        cadastroDTO.setPhone("(12) 34567-8901");
        cadastroDTO.setCpf("123.456.789-00");
        cadastroDTO.setPassword("12345678");

        clienteSalvo = new Cliente();
        clienteSalvo.setId(1L);
        clienteSalvo.setName("John Doe");
        clienteSalvo.setEmail("john.doe@example.com");
        clienteSalvo.setPhone("(12) 34567-8901");
        clienteSalvo.setCpf("123.456.789-00");
        clienteSalvo.setPassword("encodedPassword");
    }

    @Test
    public void testRegistryClient_Success() {
       
        when(clienteRepository.existsByEmail(anyString())).thenReturn(false);
        when(clienteRepository.existsByCpf(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteSalvo);

      
        ClienteRespostaDTO respostaDTO = clienteService.registryClient(cadastroDTO);

       
        assertNotNull(respostaDTO, "Resposta não deve ser nula");
        assertEquals(1L, respostaDTO.getId(), "ID deve corresponder");
        assertEquals(cadastroDTO.getName(), respostaDTO.getName(), "Nome deve corresponder");
        assertEquals(cadastroDTO.getEmail(), respostaDTO.getEmail(), "Email deve corresponder");
        
        assertEquals(cadastroDTO.getPhone(), respostaDTO.getPhone(), "Telefone deve corresponder");

        verify(clienteRepository).save(any(Cliente.class));
        verify(kafkaTemplate).send(eq("cliente-cadastro"), eq("John Doe"), any(ClienteEventoDTO.class));
    }

    @Test
    public void testRegistryClient_EmailAlreadyExists() {
       
        when(clienteRepository.existsByEmail("john.doe@example.com")).thenReturn(true);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            clienteService.registryClient(cadastroDTO);
        });
        assertTrue(exception.getMessage().contains("Email already exists"));
    }

    @Test
    public void testRegistryClient_InvalidCPF() {
       
        cadastroDTO.setCpf("invalid-cpf");

        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            clienteService.registryClient(cadastroDTO);
        });
        assertTrue(exception.getMessage().contains("CPF must be in the format"));
    }
}