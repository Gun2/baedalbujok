package com.github.gun2.beadalbujok.dao;

import com.github.gun2.beadalbujok.domain.Member;
import com.github.gun2.beadalbujok.dto.MemberDto;
import com.github.gun2.beadalbujok.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberDao {
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    public Optional<Member> findByUsername(String username){
        Optional<Member> byUsername = memberMapper.findByUsername(username);
        return byUsername;
    }

    public Member insert(Member member){
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberMapper.insert(member);
        return member;
    }

    public int update(Member member){
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberMapper.update(member);
    }

    public Optional<Member> findById(Integer id){
        return memberMapper.findById(id);
    }

    public List<Member> findAll(){
        return memberMapper.findAll();
    }

    public Optional<MemberDto.FormResponse> findByUsernameWithRoleName(String username){
        return memberMapper.findByUsernameWithRoleName(username);
    }

    public int updateRoleIdById(String roleId, Integer id){
        return memberMapper.updateRoleIdById(roleId, id);
    }
}
