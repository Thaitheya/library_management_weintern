package com.weintern.library_management.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "member")
    List<BookIssue> issues;

}
