package br.com.lucas.pagamentos.config.security;

import br.com.lucas.pagamentos.dto.UsuarioDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

@Configuration
public class TokenAuthenticationService {

    private static final String LOGIN = "admin";
    private static final String SENHA = "password";

    //EXPIRATION_TIME = 30 min
    static final long EXPIRATION_TIME = 1_800_000;
    static final String SECRET = "MySecretMySecretMySecretMySecretMySecretMySecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public String authWithLoginAndPassword(UsuarioDto user) {
        if (user.getLogin().equalsIgnoreCase(LOGIN) && user.getSenha().equalsIgnoreCase(SENHA)) {

            return Jwts.builder()
                    .setSubject(user.getLogin())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS256, SECRET)
                    .compact();
        }

        return null;
    }

    static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // faz parse do token
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
        }

        return null;
    }
}
