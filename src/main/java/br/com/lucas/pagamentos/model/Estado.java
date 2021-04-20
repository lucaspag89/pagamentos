package br.com.lucas.pagamentos.model;

public enum Estado {
    AC,
    Al,
    AP,
    AM,
    BA,
    CE,
    ES,
    GO,
    MA,
    MT,
    MS,
    MG,
    PA,
    PB,
    PR,
    PE,
    PI,
    RJ,
    RN,
    RS,
    RO,
    RR,
    SC,
    SP,
    SE,
    TO,
    DF;

    public static Estado fromName(String name) {
        for (Estado estado : Estado.values()) {
            if (estado.name().equalsIgnoreCase(name)) {
                return estado;
            }
        }

        return null;
    }
}
