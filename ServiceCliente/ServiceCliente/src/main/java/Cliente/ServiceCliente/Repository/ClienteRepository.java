package Cliente.ServiceCliente.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Cliente.ServiceCliente.Model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
   Optional<Cliente> findByEmail(String email);
   boolean existsByEmail(String email);
   boolean existsByCpf(String cpf);
}
