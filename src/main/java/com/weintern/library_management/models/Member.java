package com.weintern.library_management.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long member_id;
    private String memberCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private LocalDate joinDate;
    private boolean status;
    private LocalDateTime createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    List<BookIssue> issues;

}
