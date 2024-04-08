package cl.javadevs.springsecurityjwt.controllers;

import cl.javadevs.springsecurityjwt.dtos.DtoAuthRespuesta;
import cl.javadevs.springsecurityjwt.dtos.DtoLogin;
import cl.javadevs.springsecurityjwt.dtos.DtoRegistro;
import cl.javadevs.springsecurityjwt.models.UserTypesEntity;
import cl.javadevs.springsecurityjwt.models.UserEntity;
import cl.javadevs.springsecurityjwt.repositories.IUserTypesRepository;
import cl.javadevs.springsecurityjwt.repositories.IUserRepository;
import cl.javadevs.springsecurityjwt.security.JwtGenerador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/")
public class RestControllerAuth {

    private static final Logger log = LoggerFactory.getLogger(RestControllerAuth.class);
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private IUserTypesRepository rolesRepository;
    private IUserRepository usuariosRepository;
    private JwtGenerador jwtGenerador;

    @Autowired

    public RestControllerAuth(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, IUserTypesRepository rolesRepository, IUserRepository usuariosRepository, JwtGenerador jwtGenerador) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
        this.usuariosRepository = usuariosRepository;
        this.jwtGenerador = jwtGenerador;
    }
    //Método para poder registrar usuarios con role "user"
    @PostMapping("register")
    public ResponseEntity<String> registrar(@RequestBody DtoRegistro dtoRegistro) {
        log.debug("Registrando usuario: {}", dtoRegistro.getUsername());
        if (usuariosRepository.existsByUsername(dtoRegistro.getUsername())) {
            log.warn("El usuario ya existe: {}", dtoRegistro.getUsername());
            return new ResponseEntity<>("El usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(dtoRegistro.getUsername());
        userEntity.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));

        // Find the 'Usuario' role in the database
        Optional<UserTypesEntity> rolesOptional = rolesRepository.findByName("Usuario");
        if (rolesOptional.isPresent()) {
            UserTypesEntity userTypesEntity = rolesOptional.get();
            // Set the roles for the user
            userEntity.setRoles(Collections.singletonList(userTypesEntity));
        } else {
            // Role not found, return an error
            return new ResponseEntity<>("El rol 'Usuario' no se encuentra en el sistema", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Save the new user entity to the database
        usuariosRepository.save(userEntity);
        return new ResponseEntity<>("Registro de usuario exitoso", HttpStatus.OK);
    }


    //Método para poder guardar usuarios de tipo ADMIN
    @PostMapping("registerAdm")
    public ResponseEntity<String> registrarAdmin(@RequestBody DtoRegistro dtoRegistro) {
        if (usuariosRepository.existsByUsername(dtoRegistro.getUsername())) {
            return new ResponseEntity<>("El usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(dtoRegistro.getUsername());
        userEntity.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));

        Optional<UserTypesEntity> rolesOptional = rolesRepository.findByName("Administrador");
        if (rolesOptional.isPresent()) {
            UserTypesEntity userTypesEntity = rolesOptional.get();
            userEntity.setRoles(Collections.singletonList(userTypesEntity));
        } else {
            return new ResponseEntity<>("El rol 'ADMIN' no se encuentra en el sistema", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        usuariosRepository.save(userEntity);
        return new ResponseEntity<>("Registro de administrador exitoso", HttpStatus.OK);
    }

    //Método para poder logear un usuario y obtener un token
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody DtoLogin dtoLogin) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dtoLogin.getUsername(), dtoLogin.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtGenerador.generarToken(authentication);

            // Retorna el token en un objeto DtoAuthRespuesta
            return ResponseEntity.ok(new DtoAuthRespuesta(token));

        } catch (AuthenticationException e) {
            // Si la autenticación falla, retorna un error de autenticación
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nombre de usuario o contraseña incorrectos");
        }
    }


}
