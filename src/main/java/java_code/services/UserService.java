package java_code.services;

import java_code.dto.UserDTO;
import java_code.mappers.UserMapper;
import java_code.models.User;
import java_code.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void save(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.toUser(userDTO);
        enrichPerson(user);
        userRepository.save(user);
    }

    private void enrichPerson(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setRole("ROLE_USER");
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
}
