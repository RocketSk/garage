package com.kursach.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_TABLE")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "START_POINT", nullable = false)
    private String startPoint;

    @Column(name = "END_POINT", nullable = false)
    private String endPoint;

    @Column(name = "DISTANCE", nullable = false)
    private Double distance;

    @Column(name = "COAST", nullable = false)
    private Integer coast;

    @Column(name = "FUEL_VOLUME", nullable = false)
    private Integer fuelVolume;

    @OneToOne
    @JoinColumn(name = "FUEL_TYPE")
    private Fuel fuel;

    @OneToOne
    @JoinColumn(name = "DRIVER")
    private Driver driver;

    @OneToOne
    @JoinColumn(name = "AUTO")
    private Auto auto;


}
