package com.kursach.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AUTO")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class Auto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NUM", nullable = false, unique = true)
    private String num;

    @Column(name = "BRAND", nullable = false)
    private String brand;

    @Column(name = "MODEL", nullable = false)
    private String model;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FUEL_ID")
    private Fuel fuel;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @JoinColumn(name = "AUTO_ID")
    private List<Driver> drivers = new ArrayList<>();

    @Override
    public String toString() {
        return "Идентификационный номер автомобиля: " + getId() + ",\n" +
                "бренд автомобиля: " + getBrand() + ",\n" +
                "модель автомобиля: " + getModel() + ",\n" +
                "регистрационный номер автомобиля: " + getNum() + ",\n" +
                "тип топлива автомобиля: " + getFuel().toString();
    }
}
