package com.teamwork.boutique.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name="city_province")
public class CityProvinceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")

    private String name;
    private String city;


    @OneToMany(mappedBy = "cityProvince")
    private Set<AddressEntity> addresses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AddressEntity> getAddresses() {
        return addresses;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddresses(Set<AddressEntity> addresses) {
        this.addresses = addresses;
    }
}
