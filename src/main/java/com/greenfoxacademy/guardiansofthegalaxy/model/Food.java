package com.greenfoxacademy.guardiansofthegalaxy.model;

import javax.persistence.*;

@Entity
@Table(name = "foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer amount;
    private Integer calorie;

    public Food() {}

    public Food(String name, Integer amount, Integer calorie) {
        this.name = name;
        this.amount = amount;
        this.calorie = calorie;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getCalorie() {
        return this.calorie;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }
}
