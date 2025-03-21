package com.rayco.rayco_api.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "universities")
data class University(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "id", name = "seq_generator")
    val id: Long? = null,

    @Column(name = "average_rank")
    val averageRank: Double,

    @Column(name = "latitude")
    val latitude: Double,

    @Column(name = "longitude")
    val longitude: Double,

    @ManyToMany
    @JoinTable(
        name = "university_entry_type",
        joinColumns = [JoinColumn(name = "id_entry_type")],
        inverseJoinColumns = [JoinColumn(name = "id_university")]
    )
    val entryTypes: MutableSet<EntryTypes>,

    @ManyToMany
    @JoinTable(
        name = "university_course",
        joinColumns = [JoinColumn(name = "id_university")],
        inverseJoinColumns = [JoinColumn(name = "id_course")]
    )
    val courses: MutableSet<Course>
)