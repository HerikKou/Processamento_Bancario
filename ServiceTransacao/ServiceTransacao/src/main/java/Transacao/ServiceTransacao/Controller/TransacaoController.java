package Transacao.ServiceTransacao.Controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Transacao.ServiceTransacao.DTO.TransacaoDTO;
import Transacao.ServiceTransacao.DTO.TransacaoRespostaDTO;
import Transacao.ServiceTransacao.Service.TransacaoService;

@RestController
@RequestMapping("/api/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("/create")
    public ResponseEntity<TransacaoRespostaDTO> createTransacional(@RequestBody TransacaoDTO dto) {
        TransacaoRespostaDTO response = transacaoService.createTransacion(dto);
        return ResponseEntity.status(201).body(response);
    }

    

    @GetMapping("/searchByAccount/{accountId}")
    public ResponseEntity<List<TransacaoRespostaDTO>> searchTransactionByAccountId(@PathVariable Long accountId) {
        List<TransacaoRespostaDTO> response = transacaoService.searchTransactionByAccountId(accountId);
        return ResponseEntity.status(200).body(response);
    }

    
}