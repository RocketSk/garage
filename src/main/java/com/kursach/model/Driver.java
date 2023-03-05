package com.kursach.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DRIVERS")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class Driver implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "FATHERS_NAME")
    private String fathersName;

    @Column(name = "AUTO_NUM")
    private String autoNum;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Auto auto;

    @Override
    public String toString() {
        return "Идентификационный номер водителя: " + getId() + ",\n" +
                "Имя водителя: " + getFirstName() + ",\n" +
                "Фамилия водителя: " + getLastName() + ",\n" +
                "Отчество водителя: " + getFathersName() + ",\n" +
                "Номер закрепленного автомобиля: " + getAuto().getNum();
    }

}
