package com.rayco.rayco_api.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "entry_types")
data class EntryTypes(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "id_entry_types", name = "seq_generator")
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String
)