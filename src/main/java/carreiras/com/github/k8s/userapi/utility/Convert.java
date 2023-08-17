package carreiras.com.github.k8s.userapi.utility;

import carreiras.com.github.k8s.dto.user.UserDTO;
import carreiras.com.github.k8s.userapi.entity.User;

public class Convert {

    public static User convertUserDTOToUser(UserDTO userDTO) {
        return User.builder()
                .name(userDTO.getName())
                .address(userDTO.getAddress())
                .cpf(userDTO.getCpf())
                .email(userDTO.getEmail())
                .phoneNumber(userDTO.getPhoneNumber())
                .registrationDate(userDTO.getRegistrationDate())
                .build();
    }

    public static UserDTO convertUserToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .cpf(user.getCpf())
                .address(user.getAddress())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .registrationDate(user.getRegistrationDate())
                .build();
    }
}
