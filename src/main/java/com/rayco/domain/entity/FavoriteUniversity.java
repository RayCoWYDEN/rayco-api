package com.rayco.domain.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "favorite_universities")
@NoArgsConstructor
@Getter
@Setter
public class FavoriteUniversity {

    @EmbeddedId
    private FavoriteUniversitiesId id;

    @ManyToOne(optional = false)
    @MapsId("userId")
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(optional = false)
    @MapsId("universityId")
    @JoinColumn(name = "id_university")
    private University university;

    public FavoriteUniversity(User user, University university) {
        this.user = user;
        this.university = university;
        this.id = new FavoriteUniversitiesId(user.getId(), university.getId());
    }

}