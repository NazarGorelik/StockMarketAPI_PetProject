package java_code.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import java_code.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class JWTToPrincipalConverter {

    private final UserRepository userRepository;

    public UserPrincipal convert(DecodedJWT decodedJWT) {
        return new UserPrincipal(userRepository.findByUsername(decodedJWT.getClaim("username").asString()).get());
    }

    //FOR FUTURE IF USER HAS MANY ROLES
//    private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT decodedJWT) {
//        var claim = decodedJWT.getClaim("roles");
//        if (claim.isNull() || claim.isMissing()) return List.of();
//        return claim.asList(SimpleGrantedAuthority.class);
//    }
}
