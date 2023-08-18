package carreiras.com.github.k8s.userapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import carreiras.com.github.k8s.userapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByCpf(String cpf);
}
