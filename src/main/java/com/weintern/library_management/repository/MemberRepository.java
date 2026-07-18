package com.weintern.library_management.repository;

import com.weintern.library_management.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
