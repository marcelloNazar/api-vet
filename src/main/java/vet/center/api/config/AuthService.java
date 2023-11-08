package vet.center.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vet.center.api.atendimento.Atendimento;
import vet.center.api.user.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .nome(request.getNome())
                .telefone(request.getTelefone())
                .crmv(request.getCrmv())
                .email(request.getEmail())
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return  AuthResponse.builder()
                .token(jwtToken)
                .role(user.getRole())
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return  AuthResponse.builder()
                .token(jwtToken)
                .role(user.getRole())
                .build();
    }

    public User updateUser(Long id, RegisterRequest request) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setNome(request.getNome());
        user.setTelefone(request.getTelefone());
        user.setCrmv(request.getCrmv());
        user.setEmail(request.getEmail());
        return repository.save(user);
    }


    public Page<User> getAllVeterinarios(Pageable pageable) {
        Role role = Role.USER;
        return repository.findAllByRole(role, pageable);
    }

    public Page<User> getAllUsuarios(Pageable pageable) {
        return repository.findAll(pageable);
    }


    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }
}