package carreiras.com.github.k8s.userapi.utility;

import carreiras.com.github.k8s.dto.user.UserRequest;
import carreiras.com.github.k8s.userapi.entity.User;

public class Convert {

    public static User convertUserRequestToUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .address(userRequest.getAddress())
                .cpf(userRequest.getCpf())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .registrationDate(userRequest.getRegistrationDate())
                .build();
    }
}
