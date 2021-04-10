package kryklyvets.project.restaurant.services;

import kryklyvets.project.restaurant.components.JwtUtils;
import kryklyvets.project.restaurant.dtos.UserRequest;
import kryklyvets.project.restaurant.entities.security.Role;
import kryklyvets.project.restaurant.entities.security.User;
import kryklyvets.project.restaurant.services.interfaces.IRoleRepository;
import kryklyvets.project.restaurant.services.interfaces.IUserRepository;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final IUserRepository userRepo;
    private final IRoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public User createUser(UserRequest userRequest, String role) {
        var roles = new HashSet<Role>();
        var build = roleRepo.findByName(role);
        roles.add(build.orElseThrow());
        var user = User.builder()
                .username(userRequest
                        .getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .roles(roles)
                .build();
        return userRepo.save(user);
    }

    public String createToken(UserRequest request) {
        var authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return jwtUtils.generateJwtToken(authenticate);
    }

}
