package com.kursach.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "FUEL")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FUEL_TYPE", unique = true)
    private String fuelType;

    @Column(name = "COUNT")
    private Integer count;

    @Override
    public String toString() {
        return getFuelType();
    }

}
