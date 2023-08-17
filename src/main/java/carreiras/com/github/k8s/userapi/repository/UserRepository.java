package carreiras.com.github.k8s.userapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import carreiras.com.github.k8s.userapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
