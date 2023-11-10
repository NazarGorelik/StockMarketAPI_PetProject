package java_code.dto.responses;

import lombok.Builder;

@Builder
public record AuthenticationResponse (String accessToken){}
