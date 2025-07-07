package ru.dzhager3354.keeneye.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String surname;
    @Column(nullable = false)
    @NotBlank
    private String name;
    @Column
    private String patronymic;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    private List<StudentGroup> studentGroups;

    @OneToOne(mappedBy = "teacher")
    @JsonBackReference
    private User user;
}
