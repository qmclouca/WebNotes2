package com.qmclouca.base.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper=true)
public class Client extends BaseEntity{

    @Column(unique = true, name = "CLIENT_NAME")
    private String clientName;
    @Column
    private String password;
    @Column
    private String name;
    @Column(name="BIRTH_DATE")
    private LocalDate birthDate;
    @Column
    private String mobile;
    @Column
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_ID")
    private List<Address> address;
}
