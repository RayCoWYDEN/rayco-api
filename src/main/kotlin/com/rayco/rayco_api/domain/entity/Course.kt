package com.rayco.rayco_api.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "courses")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "id", name = "seq_generator")
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String = "",

    @ManyToMany(mappedBy = "courses")
    val universities: MutableSet<University>
)
