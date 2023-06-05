package com.github.gun2.beadalbujok.service;

import com.github.gun2.beadalbujok.domain.Member;
import com.github.gun2.beadalbujok.dto.MemberDto;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Optional<Member> findByUsername(String username);

    Member findByUsername(Authentication authentication);

    Optional<Member> findByUsernameEager(String username);

    void insert(MemberDto.SignUpRequest signUpRequest);

    void update(MemberDto.FormRequest formRequest,
                Authentication authentication);

    Optional<Member> findById(Integer id);

    List<Member> findAll();

    MemberDto.FormResponse findByAuthenticationWithRoleName(Authentication authentication);

    void updateRole(MemberDto.RoleUpdateRequest roleUpdateRequest);
}
