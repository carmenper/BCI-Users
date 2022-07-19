package com.cemp.bci.users.util;

import com.cemp.bci.users.entity.InputEntity;
import com.cemp.bci.users.enums.EnumException;
import com.cemp.bci.users.exception.ApplicationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuilderUtil {

    @Autowired
    private ObjectMapper mapper;

    public InputEntity getUser(JsonNode user) {
        try {
            return mapper.readValue(user.toString(), InputEntity.class);
        } catch (Exception e) {
            throw new ApplicationException(EnumException.MAPPING_EXCEPTION);
        }
    }

}
