package Conta.ServiceConta.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Conta.ServiceConta.DTO.ContaDTO;
import Conta.ServiceConta.DTO.ContaRespostaDTO;
import Conta.ServiceConta.DTO.SaldoDTO;
import Conta.ServiceConta.DTO.SaldoRespostaDTO;
import Conta.ServiceConta.DTO.ValidarSaldoDTO;
import Conta.ServiceConta.Service.ContaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/conta")
public class ContaController {
    
    private final ContaService contaService;
    public ContaController(ContaService contaService){
        this.contaService = contaService;
    }

    @GetMapping("/checkBalance/{id}")
    public ResponseEntity<SaldoRespostaDTO> checkBalance(@RequestParam Long clientId){
        SaldoRespostaDTO response = contaService.checkBalance(clientId);
        return ResponseEntity.status(200).body(response);
    }
    @GetMapping("/getAllAccount")
    public ResponseEntity<List<ContaRespostaDTO>> getAllAccount(){
        List<ContaRespostaDTO> response = contaService.getAllAccount();
        return ResponseEntity.status(200).body(response);
    }
    @GetMapping("/validateBalance/{id}")
    public ResponseEntity<ValidarSaldoDTO> validateBalance(@RequestParam Long clientId){
        ValidarSaldoDTO response = contaService.validateBalance(clientId);
        return ResponseEntity.status(200).body(response);
    }
    @PostMapping("/debit")
    public ResponseEntity<?> debit(@RequestBody SaldoDTO conta){
        contaService.debit(conta.getAccountId(), conta.getBalance());
        return ResponseEntity.status(200).build();
    }
    @PostMapping("/credit")
    public ResponseEntity<?> credit(@RequestBody SaldoDTO conta){
        contaService.credit(conta.getAccountId(), conta.getBalance());
        return ResponseEntity.status(200).build();
    }

}
