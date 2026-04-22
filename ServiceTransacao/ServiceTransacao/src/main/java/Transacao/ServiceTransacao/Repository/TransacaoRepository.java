package Transacao.ServiceTransacao.Repository;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Transacao.ServiceTransacao.Model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    	List<Transacao> findByAccountId(Long accountId);
		
} 