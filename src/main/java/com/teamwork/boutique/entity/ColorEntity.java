package com.teamwork.boutique.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "color")
public class ColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "color")
    private Set<StockEntity> stocks;

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

    public Set<StockEntity> getStocks() {
        return stocks;
    }

    public void setStocks(Set<StockEntity> stocks) {
        this.stocks = stocks;
    }
}
