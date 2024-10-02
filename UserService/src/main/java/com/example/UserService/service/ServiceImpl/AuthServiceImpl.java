package com.example.UserService.service.ServiceImpl;

import com.example.UserService.dto.*;
import com.example.UserService.exception.InvalidPasswordException;
import com.example.UserService.exception.InvalidTokenException;
import com.example.UserService.exception.UserAlreadyExist;
import com.example.UserService.exception.UserNotFoundException;
import com.example.UserService.model.Session;
import com.example.UserService.model.SessionType;
import com.example.UserService.model.User;
import com.example.UserService.repository.SessionRepository;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto) throws UserNotFoundException {
        User user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(()-> new UserNotFoundException("User not found"));
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid Password");
        }
        Map<String,String> map = new LinkedHashMap<>();
        map.put("userId",user.getId().toString());
        map.put("email", user.getEmail());
        map.put("roles",user.getRoles().toString());
        map.put("issuedAt", String.valueOf(Instant.now().toEpochMilli()));
        map.put("expiresAt", String.valueOf(Instant.EPOCH.plusSeconds(1200).toEpochMilli()));


        // Replace with your secret key
        String secretKey = "Ph7Zp3oY2sT1X9W0VkqL8Eu4jNrF6mDaG5bBCcQHiIxwAJyKgOtSnelUdzRPV";
        byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        Key key = Keys.hmacShaKeyFor(secretKeyBytes);

        // Create a JWT string
        String jwtToken = Jwts.builder()
                .claims(map)
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);

        Session session = new Session();
        session.setToken(jwtToken);
        session.setUser(user);
        session.setExpiresAt(Instant.now().plusSeconds(1200));
        session.setSessionType(SessionType.ACTIVE);

        Session savedSession = sessionRepository.save(session);

        return new ResponseEntity<>(new LoginResponseDto(savedSession.getId().toString(),jwtToken),headers,200);
    }

    @Override
    public UserResponseDto signup(SignupRequestDto signupRequestDto) {
        Optional<User> existingUser =  userRepository.findByEmail(signupRequestDto.getEmail());
        if (existingUser.isPresent()) {
            throw new  UserAlreadyExist("User with email already exist");
        }
        User user = SignupRequestDto.from(signupRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserResponseDto.from(userRepository.save(user));
    }

    @Override
    public SessionType validateSession(ValidateRequestDto requestDto) throws InvalidTokenException {
        Optional<Session> optionalSession = sessionRepository.findByToken(requestDto.getToken());

        if (!optionalSession.isPresent()) {
            return SessionType.INVALID;
        }
        Session session = optionalSession.get();
        System.out.println(session);
        if(session.getExpiresAt().isBefore(Instant.now())) {
            return SessionType.TIMEOUT;
        }
        return session.getSessionType();
    }

    @Override
    public LogoutResponseDto logout(LogoutRequestDto logoutRequestDto) {
        UUID sessionId = UUID.fromString(logoutRequestDto.getSessionId());
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        if (optionalSession.isPresent()) {
            Session session = optionalSession.get();
            session.setSessionType(SessionType.LOGOUT);
            session.setExpiresAt(Instant.now());
            sessionRepository.save(session);
        }
        LogoutResponseDto logoutResponseDto = new LogoutResponseDto();
        logoutResponseDto.setMessage("Logout Successful");
        return logoutResponseDto;
    }
}
