package com.weintern.library_management.service;

import com.weintern.library_management.models.Member;
import com.weintern.library_management.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getAMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Member not found with id: " + id));
    }

    public Member addMember(Member member) {
        member.setCreatedAt(LocalDateTime.now());
        if (member.getJoinDate() == null) {
            member.setJoinDate(LocalDate.now());
        }
        // Default new members to active unless explicitly set otherwise
        member.setStatus(true);
        return memberRepository.save(member);
    }

    public Member updateMember(Long id, Member memberDetails) {
        Member existingMember = getAMember(id);

        existingMember.setMemberCode(memberDetails.getMemberCode());
        existingMember.setFirstName(memberDetails.getFirstName());
        existingMember.setLastName(memberDetails.getLastName());
        existingMember.setEmail(memberDetails.getEmail());
        existingMember.setPhone(memberDetails.getPhone());
        existingMember.setAddress(memberDetails.getAddress());
        existingMember.setJoinDate(memberDetails.getJoinDate());
        existingMember.setStatus(memberDetails.isStatus());

        return memberRepository.save(existingMember);
    }

    public void deleteMember(Long id) {
        Member existingMember = getAMember(id); // throws if not found
        memberRepository.delete(existingMember);
    }
}