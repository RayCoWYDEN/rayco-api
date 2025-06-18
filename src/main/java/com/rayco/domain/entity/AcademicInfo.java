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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "academics_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_academics_info_seq")
    @SequenceGenerator(
            name = "id_academics_info_seq",
            sequenceName = "rayco_schema.id_academics_info_seq",
            allocationSize = 1
    )
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