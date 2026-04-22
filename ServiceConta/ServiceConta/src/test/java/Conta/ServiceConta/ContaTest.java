package Conta.ServiceConta;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import Conta.ServiceConta.DTO.*;
import Conta.ServiceConta.Enum.Roles;
import Conta.ServiceConta.Enum.TypeAccount;
import Conta.ServiceConta.Exception.InsufficientException;
import Conta.ServiceConta.Exception.NotFoundException;
import Conta.ServiceConta.Model.Conta;
import Conta.ServiceConta.Repository.ContaRepository;
import Conta.ServiceConta.Service.ContaService;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @Mock
    private ContaRepository repository;

    @Mock
    private KafkaTemplate<String, ContaEventoDTO> kafkaTemplate;

    @InjectMocks
    private ContaService service;

   
    

    
    @Test
    void shouldReturnBalanceSuccessfully() {

        Conta conta = new Conta();
        conta.setBalance(500.0);

        when(repository.findById(1L)).thenReturn(Optional.of(conta));

        SaldoRespostaDTO response = service.checkBalance(1L);

        assertEquals(500.0, response.getBalance());
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFoundOnBalance() {

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.checkBalance(1L));
    }

  
    @Test
    void shouldValidateBalanceSuccessfully() {

        Conta conta = new Conta();
        conta.setBalance(100.0);

        when(repository.findById(1L)).thenReturn(Optional.of(conta));

        ValidarSaldoDTO response = service.validateBalance(1L);

        assertEquals(100.0, response.getBalance());
    }

    @Test
    void shouldThrowExceptionWhenBalanceIsZero() {

        Conta conta = new Conta();
        conta.setBalance(0.0);

        when(repository.findById(1L)).thenReturn(Optional.of(conta));

        assertThrows(InsufficientException.class, () -> service.validateBalance(1L));
    }

    @Test
    void shouldReturnAllAccounts() {

        Conta conta = new Conta();
        conta.setAgency("0001");
        conta.setAccountNumber("12345-6");
        conta.setClientId(1L);
        conta.setAccountType(TypeAccount.CHECKING);

        when(repository.findAll()).thenReturn(List.of(conta));

        List<ContaRespostaDTO> result = service.getAllAccount();

        assertEquals(1, result.size());
        assertEquals("0001", result.get(0).getAgency());
    }
}