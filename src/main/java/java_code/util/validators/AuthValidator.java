package java_code.util.validators;

import java_code.dto.AuthenticationDTO;
import java_code.models.User;
import java_code.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AuthValidator implements Validator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean supports(Class<?> clazz) {
        return AuthenticationDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AuthenticationDTO authenticationDTO = (AuthenticationDTO) target;
        Optional<User> optionalUser = userRepository.findByUsername(authenticationDTO.username());
        boolean isPasswordSame = passwordEncoder.matches(authenticationDTO.password(), optionalUser.get().getPassword());

        if(!optionalUser.isPresent()) {
            errors.rejectValue("username","","Person with such username: " +
                    authenticationDTO.username() + " wasn't found");
        }else if(!isPasswordSame){
            errors.rejectValue("password", "", "Incorrect password");
        }
    }
}
