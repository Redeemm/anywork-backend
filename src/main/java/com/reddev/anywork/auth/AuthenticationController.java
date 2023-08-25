package com.reddev.anywork.auth;



import com.reddev.anywork.exception.HttpHandler.NotAcceptableException;
import com.reddev.anywork.exception.HttpHandler.NotfoundException;
import com.reddev.anywork.exception.HttpHandler.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Requests request) throws Exception {
        return ResponseEntity.ok(authenticationService.register(request));
    }



    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) throws UnauthorizedException, NotfoundException, NotAcceptableException, NotfoundException, NotAcceptableException, UnauthorizedException {
        return ResponseEntity.ok(authenticationService.login(request));
    }



    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

}
