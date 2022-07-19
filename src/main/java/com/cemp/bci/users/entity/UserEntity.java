package com.cemp.bci.users.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"active"})
@MappedSuperclass
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(generator="UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "user_id", updatable = false, nullable = false, unique = true)
    private String id;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern="MMM dd, yyyy hh:mm:ss a")
    @Column(name = "created")
    private LocalDateTime created;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern="MMM dd, yyyy hh:mm:ss a")
    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Transient
    private String token;

    @Column(name = "is_active")
    @JsonProperty(value="isActive")
    private boolean isActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
