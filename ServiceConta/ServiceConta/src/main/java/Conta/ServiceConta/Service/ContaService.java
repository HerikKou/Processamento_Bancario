package Conta.ServiceConta.Service;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import Conta.ServiceConta.DTO.ClienteCriadoEventoDTO;
import Conta.ServiceConta.Enum.Roles;

import Conta.ServiceConta.DTO.ContaEventoDTO;
import Conta.ServiceConta.DTO.ContaRespostaDTO;
import Conta.ServiceConta.DTO.SaldoRespostaDTO;
import Conta.ServiceConta.DTO.ValidarSaldoDTO;
import Conta.ServiceConta.Model.Conta;
import Conta.ServiceConta.Repository.ContaRepository;
import Conta.ServiceConta.Exception.InsufficientException;
import Conta.ServiceConta.Exception.NotFoundException;

import Conta.ServiceConta.Enum.TypeAccount;
@Service
public class ContaService {
    
private final ContaRepository repository;
private final KafkaTemplate<String, ContaEventoDTO> kafkaTemplate;
public ContaService(ContaRepository repository, KafkaTemplate<String, ContaEventoDTO> kafkaTemplate){
	this.kafkaTemplate = kafkaTemplate;
	this.repository = repository;
}

    @KafkaListener(topics = "cliente_cadastrado",groupId = "service-conta-group-v2")
   public void consumeMessage(ClienteCriadoEventoDTO cliente) {
	System.out.println("DEBUG: Recebi o cliente: " + cliente.getName());
    Conta account = new Conta();
    account.setClientId(cliente.getId());
    account.setBalance(0.0);
    account.setAccountType(TypeAccount.CHECKING);
    account.setAccountNumber(generateNumberAccount());
    account.setAgency("0001"); 
    account.setValue(0.0);
    account.setRole(Roles.USER);
    Conta savedAccount = repository.save(account);
try {
        kafkaTemplate.send("conta_criada",savedAccount.getId().toString(), new ContaEventoDTO(
            savedAccount.getId(),
            savedAccount.getClientId(),
            savedAccount.getAccountType()
        ));
    } catch (Exception e) {
        System.out.println("Kafka indisponível: " + e.getMessage());
    }
}
	
	private String generateNumberAccount() {
    java.util.Random random = new java.util.Random();
    int parte1 = random.nextInt(90000) + 10000; 
    int parte2 = random.nextInt(10);          
    return parte1 + "-" + parte2;              
}

public SaldoRespostaDTO checkBalance(Long accountId){
	Conta account = repository.findById(accountId).orElseThrow(() -> new NotFoundException("Account not found"));
	 
	 return new SaldoRespostaDTO(account.getBalance(), account.getRole());
}

public ValidarSaldoDTO validateBalance(Long accountId){
	Conta account = repository.findById(accountId).orElseThrow(() -> new NotFoundException("Account not found"));

	if(account.getBalance() <= 0){
		throw new InsufficientException("Insufficient balance");
	}
	
	
	return new ValidarSaldoDTO(account.getBalance(), account.getRole());
}


public List<ContaRespostaDTO> getAllAccount(){
	List<Conta> accounts = repository.findAll();
	return accounts.stream()
			.map(account -> new ContaRespostaDTO(account.getAgency(), account.getAccountNumber(), account.getClientId(), account.getAccountType().toString(), account.getRole()))
			.toList();
}
public void debit(Long accountId, Double amount){
	Conta account = repository.findById(accountId).orElseThrow(() -> new NotFoundException("Account not found"));
	if(account.getBalance() < amount){
		throw new InsufficientException("Insufficient balance");
	}
	account.setBalance(account.getBalance() - amount);
	repository.save(account);
}

public void credit(Long accountId, Double amount){
	Conta account = repository.findById(accountId).orElseThrow(() -> new NotFoundException("Account not found"));
	account.setBalance(account.getBalance() + amount);
	repository.save(account);
}}