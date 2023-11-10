package java_code.dto;

import jakarta.validation.constraints.NotEmpty;

public record AuthenticationDTO(@NotEmpty String username, @NotEmpty String password) {
}
