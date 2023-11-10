package java_code.controllers;

import jakarta.validation.Valid;
import java_code.dto.AuthenticationDTO;
import java_code.dto.UserDTO;
import java_code.dto.responses.AuthenticationResponse;
import java_code.services.AuthService;
import java_code.services.UserService;
import java_code.util.exceptions.ErrorUtil;
import java_code.util.exceptions.presentationLayer.InvalidCredentialsException;
import java_code.util.exceptions.presentationLayer.PersonNotCreatedException;
import java_code.util.validators.AuthValidator;
import java_code.util.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserValidator userValidator;
    private final AuthValidator authValidator;
    private final ErrorUtil errorUtil;


    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody @Valid AuthenticationDTO authenticationDTO, BindingResult bindingResult) {
        authValidator.validate(authenticationDTO, bindingResult);

        if (bindingResult.hasErrors())
            throw new InvalidCredentialsException(errorUtil.builtErrorResponse(bindingResult));

        return authService.attemptLogin(authenticationDTO.username(), authenticationDTO.password());
    }

    @PostMapping("/registration")
    public HttpStatus registration(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors())
            throw new PersonNotCreatedException(errorUtil.builtErrorResponse(bindingResult));

        userService.save(userDTO);
        return HttpStatus.OK;
    }
}


