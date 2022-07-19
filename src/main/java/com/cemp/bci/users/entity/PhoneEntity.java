package com.cemp.bci.users.entity;

import javax.persistence.*;
import java.io.Serializable;

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

}
