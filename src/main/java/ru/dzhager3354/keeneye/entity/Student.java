package ru.dzhager3354.keeneye.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

@Entity
@Table(name = "students")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(0)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String surname;
    @Column(nullable = false)
    @NotBlank
    private String name;
    @Column
    private String patronymic;

    @ManyToOne(fetch = FetchType.LAZY)
    private StudentGroup studentGroup;

    @Column(nullable = false, length = 15)
    @NotBlank
    private String phone;

    @OneToOne(mappedBy = "student")
    @JsonBackReference
    private User user;
}
