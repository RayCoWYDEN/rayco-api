package com.rayco.rayco_api.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @SequenceGenerator(sequenceName = "id_user_seq", name = "seq_generator")
        val id: Long? = null,

        @Column(name = "name", nullable = false)
        val name: String = "",

        @Column(name = "email")
        val email: String = "",

        @OneToOne
        @JoinColumn(name = "id_academic_info", nullable = true)
        val academicInfo: AcademicInfo? = null
)