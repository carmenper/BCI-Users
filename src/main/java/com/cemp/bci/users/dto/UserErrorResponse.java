package com.cemp.bci.users.dto;

import com.cemp.bci.users.util.DateUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class UserErrorResponse {

    @Getter
    private final List<ErrorResponse> error;

    public UserErrorResponse(int codigo, String detail) {
        ErrorResponse er = new ErrorResponse(codigo, detail, DateUtil.getTimeStamp());
        error = new ArrayList<>();
        error.add(er);
    }

}
