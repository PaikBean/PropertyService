package com.propertyservice.propertyservice.domain.common;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "gender")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gender_id")
    private Long genderId;
    @Column(nullable = false)
    private String gender;

    @Builder
    public Gender(Long genderId, String gender) {
        this.genderId = genderId;
        this.gender = gender;
    }
}
