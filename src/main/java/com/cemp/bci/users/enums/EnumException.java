package com.cemp.bci.users.enums;

public enum EnumException {
    EMAIL_EXCEPTION(1000,
            "Email vacio, nulo o malformado. El email debe seguir un formato " +
            "parecido a 'aaaaaa@undominio.algo'"),
    PASSWORD_EXCEPTION(1001,
            "Password vacio, nulo, o inapropiado. " +
                    "El password debe cumplir con los requisitos de seguridad."),
    DUPLICATE_EXCEPTION(1002, "Usuario ya existe."),
    TOKEN_EXCEPTION(1003, "Token es invalido."),
    CREATION_EXCEPTION(1004, "Problemas creando usuario."),
    REFRESH_EXCEPTION(1005, "Problemas creando/refrescando token."),
    BODY_EXCEPTION(1006, "Cuerpo de la solicitud para creación de usuario está vacio o es nulo."),
    NOT_FOUND_EXCEPTION(1007, "Usuario no pudo ser encontrado para el token proporcionado."),
    MAPPING_EXCEPTION(1008, "Problemas transformando el cuerpo del request."),
    CRYPTO_EXCEPTION(1009, "Problemas generando el secreto de la criptografia."),
    ENCRYPTION_EXCEPTION(1010, "Problemas encriptando."),
    DECRYPTION_EXCEPTION(1011, "Problemas desencriptando."),
    EXTRACTION_EXCEPTION(1012, "Problemas extracting usuario del token."),
    VECTOR_EXCEPTION(1013, "Problemas generando vector de la criptografia."),
    ENCRYPTED_EXCEPTION(1014, "La clave encryptada esta vacia o es nula."),
    ENCRYPTED_FORMAT_EXCEPTION(1014, "Problemas con el valor de la clave encryptada."),
    ;

    private final int codigo;
    private final String detail;

    private EnumException(int codigo, String detail) {
        this.codigo = codigo;
        this.detail = detail;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDetail() {
        return detail;
    }
}
