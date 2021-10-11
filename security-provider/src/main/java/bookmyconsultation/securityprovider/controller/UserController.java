package bookmyconsultation.securityprovider.controller;

import bookmyconsultation.securityprovider.entity.User;
import bookmyconsultation.securityprovider.model.JwtTokenRequest;
import bookmyconsultation.securityprovider.model.UserPrincipal;
import bookmyconsultation.securityprovider.service.TokenProvider;
import bookmyconsultation.securityprovider.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/security")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @GetMapping("/live")
    public ResponseEntity<String> checkStatus() {
        return ResponseEntity.ok("It's running live");
    }

    @PostMapping("/generate-token")
    public ResponseEntity generateToken(@RequestBody JwtTokenRequest tokenRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(tokenRequest.getUsername(), tokenRequest.getPassword())
        );
        String token = tokenProvider.generateToken((UserPrincipal)authentication.getPrincipal());
        LOGGER.info("Token generated {}", token);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/user/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }
}