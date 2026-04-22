package Pagamento.ServicePagamento.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Pagamento.ServicePagamento.Model.Pagamento;
import java.util.List;



@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

	List<Pagamento> findByTransactionId(Long transactionId);
	boolean existsByTransactionId(Long transactionId);
}