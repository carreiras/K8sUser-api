package carreiras.com.github.k8s.userapi.service;

import static carreiras.com.github.k8s.userapi.utility.Convert.convertUserRequestToUser;

import java.util.List;

import org.springframework.stereotype.Service;

import carreiras.com.github.k8s.dto.user.UserRequest;
import carreiras.com.github.k8s.userapi.entity.User;
import carreiras.com.github.k8s.userapi.exception.ResourceNotFoundException;
import carreiras.com.github.k8s.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String USER_NOT_FOUND = "Usuário não encontrado.";

    private final UserRepository userRepository;

    public User create(UserRequest userRequest) {
        User user = convertUserRequestToUser(userRequest);
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));
    }

    public User findByCpf(String cpf) {
        return userRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));
    }

    public List<User> findByNameContainingIgnoreCase(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    public User update(Long id, UserRequest userRequest) {
        findByCpf(userRequest.getCpf());

        return userRepository.findById(id)
                .map(f -> {
                    User user = convertUserRequestToUser(userRequest);
                    user.setId(id);
                    user.setCpf(f.getCpf());
                    user.setRegistrationDate(f.getRegistrationDate());
                    userRepository.save(user);
                    return user;
                })
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));
    }

    public void delete(long id) {
        userRepository.findById(id)
                .map(f -> {
                    userRepository.delete(f);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));
    }
}
