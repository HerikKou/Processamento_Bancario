package Conta.ServiceConta.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Conta.ServiceConta.Model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    
	Optional<Conta> findByClientId(Long clientId);
} 