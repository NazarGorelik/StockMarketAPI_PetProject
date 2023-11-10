package java_code.dto;

import jakarta.validation.constraints.NotNull;

public record UserDTO(@NotNull String username, @NotNull String password) {
}
