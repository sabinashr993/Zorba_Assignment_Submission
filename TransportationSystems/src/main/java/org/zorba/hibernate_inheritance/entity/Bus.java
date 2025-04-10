package org.zorba.hibernate_inheritance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table
public class Bus extends Vehicle {
    @Column(name = "comfort_type")
    private String comfortType;

}
