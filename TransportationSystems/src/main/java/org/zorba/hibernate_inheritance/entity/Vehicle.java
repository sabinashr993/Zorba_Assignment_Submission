package org.zorba.hibernate_inheritance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "pk_table")
    @TableGenerator(allocationSize = 1, name = "pk_table", initialValue = 100)
    private int id;

    @Column(name = "vehicle_name")
    private String vehicleName;

    @Column(name = "number_of_wheels")
    private int numberOfWheels;

    @Column(name = "capacity")
    private int capacity;
}
