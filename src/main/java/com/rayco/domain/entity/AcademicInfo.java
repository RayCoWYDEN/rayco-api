package com.rayco.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "academics_info")
public class AcademicInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "id_academics_info_seq", name = "seq_generator")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_course")
    private Course course;

    @Column(name = "period", nullable = false)
    private int period;

    @Column(name = "tuition_fee", nullable = false)
    private double tuitionFee;

    @ManyToOne
    @JoinColumn(name = "id_university")
    private University university;
}