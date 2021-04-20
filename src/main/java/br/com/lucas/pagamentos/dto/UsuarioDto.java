package br.com.lucas.pagamentos.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class UsuarioDto implements Serializable {

    @Getter
    @Setter
    private String login;

    @Getter
    @Setter
    private String senha;

}
