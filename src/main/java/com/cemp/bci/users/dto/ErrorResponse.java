package com.cemp.bci.users.dto;

import lombok.Getter;


public class ErrorResponse {

    @Getter
    private int codigo;
    @Getter private final String timestamp;
    @Getter private String detail;

    private ErrorResponse(String timestamp) {
        this.timestamp = timestamp;
    }

    public ErrorResponse(int codigo, String timestamp) {
        this(timestamp);
        this.codigo = codigo;
        this.detail = "Error sin detalle";
    }

    public ErrorResponse(int codigo, String detail, String timestamp) {
        this(timestamp);
        this.codigo = codigo;
        this.detail = detail;
    }

}
