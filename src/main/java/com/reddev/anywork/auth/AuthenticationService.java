package com.reddev.anywork.auth;



import com.reddev.anywork.config.JwtService;
import com.reddev.anywork.exception.ApiRequestException;
import com.reddev.anywork.exception.HttpHandler.NotAcceptableException;
import com.reddev.anywork.exception.HttpHandler.NotfoundException;
import com.reddev.anywork.exception.HttpHandler.UnauthorizedException;
import com.reddev.anywork.model.user.User;
import com.reddev.anywork.repository.UserRepository;
import com.reddev.anywork.utils.token.Token;
import com.reddev.anywork.utils.token.TokenRepository;
import com.reddev.anywork.utils.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;


  public AuthenticationResponse register(Requests request) {


    var user = User.builder()
            .serviceType(request.getServiceType())
            .role(request.getRole())
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .phoneNumber(request.getPhoneNumber())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .enabled(true)
            .locked(false)
            .build();

    boolean userExists = userRepository
            .findByEmail(user.getEmail())
            .isPresent();

    if (userExists) {
      throw new ApiRequestException("User already exist!");
    }

    var savedUser = userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);

    saveUserToken(savedUser, jwtToken);

    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .build();
  }




  public AuthenticationResponse login(AuthenticationRequest request) throws UnauthorizedException, NotfoundException, NotAcceptableException {
    boolean userExists = userRepository
            .findByEmail(request.getEmail())
            .isPresent();

    if (!userExists) {
      throw new NotfoundException("User not found!");
    }


    var user = userRepository.findByEmail(request.getEmail())
            .orElseThrow();

    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);

    if (!user.isEnabled()) {
      throw new NotAcceptableException(jwtToken);
    }

    try {

      authenticationManager
              .authenticate(
                      new UsernamePasswordAuthenticationToken(
                              request.getEmail(),
                              request.getPassword()
                      )
              );

    } catch (Exception e) {
      throw new UnauthorizedException("Bad Credentials");
    }

    user.setLastLogin(LocalDateTime.now());
    userRepository.save(user);

    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);

    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .build();
  }



  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
            .user(user)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
    tokenRepository.save(token);
  }

}