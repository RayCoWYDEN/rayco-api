package com.rayco.rayco_api.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "academics_info")
data class AcademicInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "id_academics_info_seq", name = "seq_generator")
    val id: Long? = null,

    @OneToOne
    @JoinColumn(name = "id_course", nullable = true )
    val course: Course? = null,

    @Column(name = "period", nullable = false)
    val period: Int,

    @Column(name = "tuition_fee")
    val tuitionFee: Double
)
