package com.ivan.thehealthsoftwarecompany.hisv3.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "middlename", length = Integer.MAX_VALUE)
    private String middlename;

    @Column(name = "surname", length = Integer.MAX_VALUE)
    private String surname;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

}