package br.com.lucas.pagamentos.model.funcionario;

public enum Sexo {

    MASCULINO,
    FEMININO;

    public static Sexo fromName(String name) {
        for (Sexo sexo : Sexo.values()) {
            if (sexo.name().equalsIgnoreCase(name)) {
                return sexo;
            }
        }

        return null;
    }

}

