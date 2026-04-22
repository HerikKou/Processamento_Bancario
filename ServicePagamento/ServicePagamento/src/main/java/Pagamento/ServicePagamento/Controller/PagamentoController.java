package Pagamento.ServicePagamento.Controller;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Pagamento.ServicePagamento.DTO.PagamentoRespostaDTO;
import Pagamento.ServicePagamento.Service.PagamentoService;


@RestController
@RequestMapping("/api/Pagamento")
public class PagamentoController {

   private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }
    @PostMapping
    @GetMapping("/getAllPayments")
    public ResponseEntity<List<PagamentoRespostaDTO>> getAllPayments() {
        List<PagamentoRespostaDTO> pagamentos = pagamentoService.getAllPayments();
        return ResponseEntity.ok(pagamentos);
    }
    
    @GetMapping("/getPaymentByTransactionId")
    public ResponseEntity<List<PagamentoRespostaDTO>> getPaymentByTransactionId(@RequestParam Long transactionId) {
        List<PagamentoRespostaDTO> pagamentos = pagamentoService.getPaymentByTransactionId(transactionId);
        return ResponseEntity.ok(pagamentos);


    }}