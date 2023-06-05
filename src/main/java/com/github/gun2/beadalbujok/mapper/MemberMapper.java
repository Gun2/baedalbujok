package com.github.gun2.beadalbujok.mapper;

import com.github.gun2.beadalbujok.domain.Member;
import com.github.gun2.beadalbujok.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper

public interface MemberMapper {

    Optional<Member> findByUsername(String username);

    int insert(Member member);

    int update(Member member);

    Optional<Member> findById(Integer id);

    List<Member> findAll();

    Optional<MemberDto.FormResponse> findByUsernameWithRoleName(String username);

    int updateRoleIdById(String roleId, Integer id);
}




