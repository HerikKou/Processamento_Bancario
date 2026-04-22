package Cliente.ServiceCliente.Contoller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Cliente.ServiceCliente.DTO.CadastroDTO;
import Cliente.ServiceCliente.DTO.ClienteRespostaDTO;
import Cliente.ServiceCliente.DTO.LoginDTO;
import Cliente.ServiceCliente.DTO.LoginRespostaDTO;
import Cliente.ServiceCliente.Service.ClienteService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/client")
public class Controller {
    private final ClienteService clienteserviice;
    public Controller(ClienteService clienteserviice) {
        this.clienteserviice = clienteserviice;
    }
    @PostMapping("/registry")
    public ResponseEntity<ClienteRespostaDTO> registry(@RequestBody CadastroDTO cadastro) {
        ClienteRespostaDTO resposta = clienteserviice.registryClient(cadastro);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginRespostaDTO> login(@RequestBody LoginDTO login) {
        LoginRespostaDTO resposta = clienteserviice.login(login);
        return ResponseEntity.ok(resposta);
    }
    
    @GetMapping("/getAllClient")
    public ResponseEntity<List<ClienteRespostaDTO>> getAllClients() {
        List<ClienteRespostaDTO> clientes = clienteserviice.getAllClients();
        return ResponseEntity.ok(clientes);
    }
}

