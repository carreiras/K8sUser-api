package carreiras.com.github.k8s.userapi.service;

import static carreiras.com.github.k8s.userapi.utility.Convert.convertUserDTOToUser;
import static carreiras.com.github.k8s.userapi.utility.Convert.convertUserToUserDTO;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import carreiras.com.github.k8s.dto.user.UserDTO;
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

    public UserDTO create(UserDTO userDTO) {
        User user = convertUserDTOToUser(userDTO);
        User savedUser = userRepository.save(user);
        return convertUserToUserDTO(savedUser);
    }

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(Convert::convertUserToUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));
        return convertUserToUserDTO(user);
    }

    public UserDTO findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));
        return convertUserToUserDTO(user);
    }
}
