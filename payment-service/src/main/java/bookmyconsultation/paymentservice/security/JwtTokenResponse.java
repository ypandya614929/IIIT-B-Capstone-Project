package bookmyconsultation.paymentservice.security;

public class JwtTokenResponse {
    private String accessToken;

    public JwtTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
