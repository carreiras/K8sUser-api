package carreiras.com.github.k8s.userapi.handler;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class ApiError {

    private final List<String> messages;

    public ApiError(List<String> messages) {
        this.messages = messages;
    }

    public ApiError(String message) {
        this.messages = Arrays.asList(message);
    }
}
