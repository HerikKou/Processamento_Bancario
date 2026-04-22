package Cliente.ServiceCliente.Config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Cliente.ServiceCliente.Repository.ClienteRepository;


@Service
public class AuthConfig implements UserDetailsService {

    private final ClienteRepository clienteRepository;

    public AuthConfig(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clienteRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}