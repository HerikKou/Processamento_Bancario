package Transacao.ServiceTransacao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import Transacao.ServiceTransacao.DTO.TransacaoDTO;
import Transacao.ServiceTransacao.DTO.TransacaoRespostaDTO;
import Transacao.ServiceTransacao.DTO.TransacaoEventoDTO;
import Transacao.ServiceTransacao.Enum.Status;
import Transacao.ServiceTransacao.Enum.TypeTransactional;
import Transacao.ServiceTransacao.Model.Transacao;
import Transacao.ServiceTransacao.Repository.TransacaoRepository;
import Transacao.ServiceTransacao.Exception.NotFoundException;
import Transacao.ServiceTransacao.Service.TransacaoService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @Mock
    private TransacaoRepository repository;

    @Mock
    private KafkaTemplate<String, TransacaoEventoDTO> kafkaTemplate;

    @InjectMocks
    private TransacaoService transacaoService;

    private Transacao transacao;
    private TransacaoDTO transacaoDTO;

    @BeforeEach
    void setUp() {
        transacao = new Transacao();
        
        transacao.setAccountId(1L);
        transacao.setAccountDestinyId(2L);
        transacao.setValue(500.00);
        transacao.setStatus(Status.PENDING);
        transacao.setDate(LocalDateTime.now());
        transacao.setType(TypeTransactional.PIX);

        transacaoDTO = new TransacaoDTO();
        transacaoDTO.setAccountId(1L);
        transacaoDTO.setAccountDestinyId(2L);
        transacaoDTO.setValue(500.00);
        transacaoDTO.setType(TypeTransactional.PIX);
    }

    @Test
    void shouldCreateTransactionSuccessfully() {
        when(repository.save(any(Transacao.class))).thenReturn(transacao);

        TransacaoRespostaDTO response = transacaoService.createTransacion(transacaoDTO);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals(new BigDecimal("500.00"), response.getValue());
        assertEquals(Status.PENDING, response.getStatus());
        verify(repository, times(1)).save(any(Transacao.class));
    }

    

    @Test
    void shouldFindTransactionsByAccountId() {
        when(repository.findByAccountId(1L)).thenReturn(List.of(transacao));

        List<TransacaoRespostaDTO> response = transacaoService.searchTransactionByAccountId(1L);

        assertNotNull(response);
        assertEquals(1, response.size());
        verify(repository, times(1)).findByAccountId(1L);
    }

    

    @Test
    void shouldReturnEmptyListWhenNoTransactionsByAccountId() {
        when(repository.findByAccountId(99L)).thenReturn(List.of());

        List<TransacaoRespostaDTO> response = transacaoService.searchTransactionByAccountId(99L);

        assertNotNull(response);
        assertTrue(response.isEmpty());
    }

    
}