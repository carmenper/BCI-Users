package com.cemp.bci.users.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties({"id"})
@Entity
@Table(name = "PHONE_TABLE")
public class PhoneEntity implements Serializable {

    @Id
    @Column(name = "phone_id")
    @GeneratedValue
    private Long id;

    @Column(name = "number")
    private long number;

    @Column(name = "city_code")
    private int citycode;

    @Column(name = "country_code")
    private String countrycode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private InputEntity user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getCitycode() {
        return citycode;
    }

    public void setCitycode(int citycode) {
        this.citycode = citycode;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public InputEntity getUser() {
        return user;
    }

    public void setUser(InputEntity user) {
        this.user = user;
    }

}
