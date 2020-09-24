package com.tutorial.crud.controller;

import com.tutorial.crud.dto.JWTDto;
import com.tutorial.crud.dto.LogginUser;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.NewUser;
import com.tutorial.crud.entity.Rol;
import com.tutorial.crud.entity.User;
import com.tutorial.crud.enums.RolName;
import com.tutorial.crud.jwt.JWTProvider;
import com.tutorial.crud.jwt.JWTTokenFilter;
import com.tutorial.crud.service.RolService;
import com.tutorial.crud.service.UserService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private final static Logger logger= LoggerFactory.getLogger(JWTTokenFilter.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @Autowired
    JWTProvider jwtProvider;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser (@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("credenciales invalidas"), HttpStatus.BAD_REQUEST);
        if (userService.existsByUserName(newUser.getUserName()))
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if (userService.existByEmail(newUser.getEmail()))
            return new ResponseEntity<>(new Mensaje("ya existe email ya existe"), HttpStatus.BAD_REQUEST);
        User user = new User(newUser.getName(), newUser.getUserName(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
        if (newUser.getRoles().contains("admin"))
            roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);

        return new ResponseEntity(new Mensaje("Usuario creado"), HttpStatus.CREATED);
    }

    @PostMapping("/loggin")
        public ResponseEntity<JWTDto> loggin(@Valid @RequestBody LogginUser logginUser, BindingResult bindingResult){
            if (bindingResult.hasErrors())
                return new ResponseEntity(new Mensaje("credenciales invalidas"), HttpStatus.BAD_REQUEST);
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logginUser.getUserName() ,logginUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt= jwtProvider.generateToken(authentication);
            UserDetails userDetails= (UserDetails) authentication.getPrincipal();
            JWTDto jwtDto = new JWTDto(jwt,userDetails.getUsername(),userDetails.getAuthorities());
            return  new ResponseEntity(jwtDto,HttpStatus.OK);
        }



    }

