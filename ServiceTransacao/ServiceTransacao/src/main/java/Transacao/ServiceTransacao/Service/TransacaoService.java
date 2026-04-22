package Transacao.ServiceTransacao.Service;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import Transacao.ServiceTransacao.DTO.ContaCriadaEventoDTO;
import Transacao.ServiceTransacao.DTO.TransacaoDTO;
import Transacao.ServiceTransacao.DTO.TransacaoEventoDTO;
import Transacao.ServiceTransacao.DTO.TransacaoRespostaDTO;
import Transacao.ServiceTransacao.Model.Transacao;
import Transacao.ServiceTransacao.Repository.TransacaoRepository; 
import Transacao.ServiceTransacao.Enum.*;
import Transacao.ServiceTransacao.Exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.*;
@Service
public class TransacaoService {
    private final KafkaTemplate<String, TransacaoEventoDTO> KafkaTemplate;
    private final TransacaoRepository repository;
    public TransacaoService(KafkaTemplate<String, TransacaoEventoDTO> kafkaTemplate,TransacaoRepository repository){
	this.KafkaTemplate = kafkaTemplate;
	this.repository = repository;
	}
	@KafkaListener(topics = "conta_criada", groupId = "service-transacao-group-v2")
	public void consumeMessage(ContaCriadaEventoDTO account) {
    		System.out.println("Conta recebida: " + account.getAccountId() + " Cliente: " + account.getClientId());
	    }
        	
	  public TransacaoRespostaDTO createTransacion(TransacaoDTO dto){
		Transacao transaction = new Transacao();
		transaction.setAccountId(dto.getAccountId());
		transaction.setAccountDestinyId(dto.getAccountId());
		transaction.setValue(dto.getValue());
		transaction.setStatus(Status.PENDING);
		transaction.setDate(LocalDateTime.now());
		transaction.setType(dto.getType());
		Transacao savedTransaction = repository.save(transaction);
		try{

		KafkaTemplate.send("transacao_realizada",savedTransaction.getId().toString(), new TransacaoEventoDTO(savedTransaction.getId(),savedTransaction.getAccountId(),savedTransaction.getAccountDestinyId(),
		savedTransaction.getValue(),savedTransaction.getStatus(),savedTransaction.getDate(),savedTransaction.getType()));
   	       }catch (Exception e) {
                 System.out.println("Kafka indisponível: " + e.getMessage());
   		 }
		return new TransacaoRespostaDTO(
   	 	savedTransaction.getId(),
		savedTransaction.getAccountId(),savedTransaction.getAccountDestinyId(),
		savedTransaction.getValue(),
		savedTransaction.getStatus(),savedTransaction.getDate(),savedTransaction.getType()
			);
		
	   }

	public List<TransacaoRespostaDTO> searchTransactionByAccountId(Long accountId) {
    List<Transacao> transactions = repository.findByAccountId(accountId);
    return transactions.stream()
        .map(t -> new TransacaoRespostaDTO(
            t.getId(),
            t.getAccountId(),
            t.getAccountDestinyId(),
            t.getValue(),
            t.getStatus(),
            t.getDate(),
            t.getType()
        ))
        .toList();
	}
	private void debitAccount(Long accountId, Double value) {
		 Transacao  transacao = repository.findById(accountId).orElseThrow(() -> new NotFoundException("Transaction not found"));
		
	}



}
		
	











