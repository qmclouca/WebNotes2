package com.qmclouca.base.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper=true)
public class Address extends BaseEntity {
    @Column
    private String street;
    @Column
    private String city;
    @Column
    private String state;
    @Column(name="POSTAL_CODE")
    private String postalCode;
    @Column
    private String number;
    @Column
    private String references;
}
