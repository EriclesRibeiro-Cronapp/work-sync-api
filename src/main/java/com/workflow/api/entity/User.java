package com.workflow.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "createdBy")
    private List<Task> tasks;

    @JsonIgnore
    @OneToMany(mappedBy = "assignedTo")
    private List<Task> assignedTasks;

    @JsonIgnore
    @OneToMany(mappedBy = "createdBy")
    private List<Tag> tags;
}
