package java_code.services;

import java_code.models.User;
import java_code.repositories.UserRepository;
import java_code.security.UserPrincipal;
import java_code.util.exceptions.businessLayer.PersonNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserPrincipalService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (!user.isPresent())
            throw new PersonNotFoundException("Person is not found!!");

        return new UserPrincipal(user.get());
    }
}
