package com.tapianadia.forohub.configuration;

import com.tapianadia.forohub.repositories.UserRepository;
import com.tapianadia.forohub.utilities.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class UserDetailsServices implements UserDetailsServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User usuario = userRepository.findByNombre(username).orElseThrow(() -> new UsernameNotFoundException(username));

        Collection<? extends GrantedAuthority> authority = Arrays.asList(new SimpleGrantedAuthority("ROLE_".concat(usuario.getPerfil().getNombre())));

        return new User(username, usuario.getContrasena(), authority);
    }

    public LoginResponseDTO login(LoginRequestDTO login) {

        String usuario = login.getUsuario();

        String contrasenia = login.getContrasenia();

        Authentication authentication = autenticar(usuario, contrasenia);

        String token = jwtUtils.crearToken(authentication);

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(usuario, token);

        return loginResponseDTO;

    }

    private Authentication autenticar(String usuario, String contrasenia) {
        UserDetails user = loadUserByUsername(usuario);

        if (!passwordEncoder.matches(contrasenia ,user.getPassword())) {
            throw new BadCredentialsException("Hay un error en las credenciales otorgadas");
        }
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

}
