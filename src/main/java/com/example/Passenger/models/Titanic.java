package com.example.Passenger.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "titanic")
public class Titanic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('titanic_id_seq'::regclass)")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "survived")
    private Boolean survived;

    @Column(name = "pclass")
    private Pclass pclass;

    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "age")
    private Integer age;

    @Column(name = "siblings")
    private Integer siblings;

    @Column(name = "parents")
    private Integer parents;

    @Column(name = "fare")
    private Integer fare;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getSurvived() {
        return survived;
    }

    public void setSurvived(Boolean survived) {
        this.survived = survived;
    }

    public Pclass getPclass() {
        return pclass;
    }

    public void setPclass(Pclass pclass) {
        this.pclass = pclass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSiblings() {
        return siblings;
    }

    public void setSiblings(Integer siblingsSpouses) {
        this.siblings = siblings;
    }

    public Integer getParents() {
        return parents;
    }

    public void setParents(Integer parents) {
        this.parents = parents;
    }

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }

    public Titanic() {
    }

    public Titanic(Integer id, Boolean survived, Pclass pclass, String name, String sex, Integer age, Integer siblings, Integer parents, Integer fare) {
        this.id = id;
        this.survived = survived;
        this.pclass = pclass;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.siblings = siblings;
        this.parents = parents;
        this.fare = fare;
    }

    @Override
    public String toString() {
        return  "{" +
                "id=" + id +
                ", survived=" + survived +
                ", pclass='" + pclass + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", siblings=" + siblings +
                ", parents=" + parents +
                ", fare=" + fare +
                '}';
    }

}