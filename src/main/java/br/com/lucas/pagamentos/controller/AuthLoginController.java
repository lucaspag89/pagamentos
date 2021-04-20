package br.com.lucas.pagamentos.controller;

import br.com.lucas.pagamentos.config.security.TokenAuthenticationService;
import br.com.lucas.pagamentos.dto.UsuarioDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class AuthLoginController {

    @Autowired
    TokenAuthenticationService tokenAuthenticationService;

    @ApiOperation(value = "Para solicitar um token utilize login = admin e senha = password")
    @PostMapping(value = "/login")
    public ResponseEntity<String>
    getToken(@RequestBody UsuarioDto usuario) {

        try {

            if (usuario != null) {
                String token = tokenAuthenticationService.authWithLoginAndPassword(usuario);
                if (token != null) {
                    return new ResponseEntity<>(token, HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
