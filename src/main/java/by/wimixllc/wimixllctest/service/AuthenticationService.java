package by.wimixllc.wimixllctest.service;

import by.wimixllc.wimixllctest.dto.AuthenticationRequestDto;
import by.wimixllc.wimixllctest.entity.User;
import by.wimixllc.wimixllctest.repository.UserRepository;
import by.wimixllc.wimixllctest.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationService(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Map<Object, Object> login(AuthenticationRequestDto authenticationRequestDto) {
        log.info("Try to login in account {}", authenticationRequestDto.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDto.getEmail(), authenticationRequestDto.getPassword()));
        User user = userRepository.findByEmail(authenticationRequestDto.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        Set<String> strings = new HashSet<>();
        user.getUserRoles().forEach(role -> strings.add(role.getRoleName()));
        String token = jwtTokenProvider.createToken(user.getId().toString(), authenticationRequestDto.getEmail(), strings);
        Map<Object, Object> response = new HashMap<>();
        response.put("email", authenticationRequestDto.getEmail());
        response.put("token", token);
        return response;
    }
}
