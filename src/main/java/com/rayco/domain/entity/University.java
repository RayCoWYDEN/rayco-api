package com.rayco.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "universities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "id_university_seq", name = "seq_generator")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "average_rank", nullable = false)
    private double averageRank;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "private", nullable = false)
    private boolean privateInstitution;

    @ManyToMany
    @JoinTable(
            name = "university_entry_type",
            joinColumns = @JoinColumn(name = "id_university"),
            inverseJoinColumns = @JoinColumn(name = "id_entry_type")
    )
    private Set<EntryTypes> entryTypes;

    @ManyToMany
    @JoinTable(
            name = "university_course",
            joinColumns = @JoinColumn(name = "id_university"),
            inverseJoinColumns = @JoinColumn(name = "id_course")
    )
    private Set<Course> courses;

    @OneToMany(mappedBy = "university")
    private Set<FavoriteUniversity> favoriteUniversities;
}