package Pagamento.ServicePagamento.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;

import Pagamento.ServicePagamento.DTO.PagamentoRespostaDTO;
import Pagamento.ServicePagamento.DTO.TransacaoRealizadaDTO;
import Pagamento.ServicePagamento.Enum.StatusPagamento;
import Pagamento.ServicePagamento.Enum.TypePagamento;
import Pagamento.ServicePagamento.Exception.InvalidException;
import Pagamento.ServicePagamento.Model.Pagamento;
import Pagamento.ServicePagamento.Repository.PagamentoRepository;

@Service
public class PagamentoService {
    
    private final PagamentoRepository repository;
    public PagamentoService(PagamentoRepository repository){
	
		this.repository = repository;
	}
	@KafkaListener(topics = "transacao_realizada", groupId = "service-pagamento-group")
	public void consumeMessage(TransacaoRealizadaDTO transacao) {
		Pagamento payment = new Pagamento();
		payment.setTransactionId(transacao.getTransactionId());
		payment.setStatusPayment(validatePayment(transacao.getTransactionId()));
		payment.setTypePayment(generatePaymentType(transacao));
		repository.save(payment);

	}	
private StatusPagamento validatePayment(Long transactionId) {
	if(repository.existsByTransactionId(transactionId)) {
		return StatusPagamento.COMPLETED;
	} else {
		return StatusPagamento.FAILED;
	}

}


private TypePagamento  generatePaymentType(TransacaoRealizadaDTO transacao) {
	 switch (transacao.getType()) {
			case PIX:
				return TypePagamento.PIX;
			case TED:
				return TypePagamento.TED;
			case DOC:
				return TypePagamento.DOC;
			case BOLETO:
				return TypePagamento.BOLETO;
			default:
				throw new InvalidException("Invalid payment type: " + transacao.getType());
	 }

}


public List<PagamentoRespostaDTO> getAllPayments() {
	List<Pagamento> payment = repository.findAll();
	return payment.stream()
			.map(p-> new PagamentoRespostaDTO(p.getId(), p.getTransactionId(), p.getStatusPayment(), p.getTypePayment(), p.getRole(), p.getCreatedAt()))
			.collect(Collectors.toList());

}
public List<PagamentoRespostaDTO> getPaymentByTransactionId(Long transactionId) {
	List<Pagamento> payment = repository.findByTransactionId(transactionId);
	return payment.stream()
			.map(p-> new PagamentoRespostaDTO(p.getId(), p.getTransactionId(), p.getStatusPayment(), p.getTypePayment(), p.getRole(), p.getCreatedAt()))
			.collect(Collectors.toList());

}



}









