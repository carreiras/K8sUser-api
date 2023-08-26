package carreiras.com.github.k8s.userapi.service;

import static carreiras.com.github.k8s.userapi.utility.Convert.convertUserRequestToUser;
import static carreiras.com.github.k8s.userapi.utility.Convert.convertUserToUserResponse;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import carreiras.com.github.k8s.dto.user.UserRequest;
import carreiras.com.github.k8s.dto.user.UserResponse;
import carreiras.com.github.k8s.userapi.entity.User;
import carreiras.com.github.k8s.userapi.exception.ResourceNotFoundException;
import carreiras.com.github.k8s.userapi.repository.UserRepository;
import carreiras.com.github.k8s.userapi.utility.Convert;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String USER_NOT_FOUND = "Usuário não encontrado.";

    private final UserRepository userRepository;

    public UserResponse create(UserRequest userRequest) {
        User user = convertUserRequestToUser(userRequest);
        User savedUser = userRepository.save(user);

        return convertUserToUserResponse(savedUser);

    }

    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(Convert::convertUserToUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse findById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));

        return convertUserToUserResponse(user);
    }

    public UserResponse findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));

        return convertUserToUserResponse(user);
    }

    public List<UserResponse> findByNameContainingIgnoreCase(String name) {
        List<User> users = userRepository.findByNameContainingIgnoreCase(name);
        
        return users.stream()
                .map(Convert::convertUserToUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse update(Long id, UserRequest userRequest) {
        findByCpf(userRequest.getCpf());

        User updatedUser = userRepository.findById(id)
                .map(m -> {
                    User user = convertUserRequestToUser(userRequest);
                    user.setId(id);
                    user.setCpf(m.getCpf());
                    user.setRegistrationDate(m.getRegistrationDate());
                    userRepository.save(user);
                    return user;
                })
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));

        return convertUserToUserResponse(updatedUser);
    }

    public void delete(long id) {
        userRepository.findById(id)
                .map(m -> {
                    userRepository.delete(m);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));
    }
}
