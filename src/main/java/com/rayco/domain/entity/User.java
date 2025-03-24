package com.rayco.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @SequenceGenerator(sequenceName = "id_user_seq", name = "seq_generator")
        private Long id;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "email")
        private String email;

        @OneToOne
        @JoinColumn(name = "id_academic_info", nullable = true)
        private AcademicInfo academicInfo;
}