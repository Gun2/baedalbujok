package com.github.gun2.beadalbujok.service.impl;

import com.github.gun2.beadalbujok.dao.MemberDao;
import com.github.gun2.beadalbujok.domain.Member;
import com.github.gun2.beadalbujok.domain.Point;
import com.github.gun2.beadalbujok.dto.MemberDto;
import com.github.gun2.beadalbujok.exception.DuplicationUsernameException;
import com.github.gun2.beadalbujok.exception.NotEqualsMemberPasswordException;
import com.github.gun2.beadalbujok.service.MemberService;
import com.github.gun2.beadalbujok.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {
    private final MemberDao memberDao;
    private final PointService pointService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> memberOptional = memberDao.findByUsername(username);
        if(memberOptional.isPresent()){
            Member member = memberOptional.get();
            return new MemberDto.User(member);
        }
        throw new UsernameNotFoundException("user를 찾을 수 없습니다.");
    }

    @Override
    public Optional<Member> findByUsername(String username){
        return memberDao.findByUsername(username);
    }

    @Override
    public Member findByUsername(Authentication authentication){
        Optional<Member> byUsername = this.findByUsername(authentication.getName());
        if(byUsername.isEmpty()){
            throw new RuntimeException("로그인 정보를 찾지 못했습니다.");
        }
        return byUsername.get();
    }



    @Override
    public Optional<Member> findByUsernameEager(String username){
        Optional<Member> byUsername = this.findByUsername(username);
        if(byUsername.isPresent()){
            Optional<Point> byMemberId = pointService.findByMemberId(byUsername.get().getId());
            if(byMemberId.isPresent()){
                byUsername.get().setPoint(byMemberId.get());
            }
        }
        return byUsername;
    }

    @Override
    @Transactional
    public void insert(MemberDto.SignUpRequest signUpRequest) {
        Optional<Member> byUsername = memberDao.findByUsername(signUpRequest.getUsername());
        if(byUsername.isPresent()){
            throw new DuplicationUsernameException("중복된 id가 이미 존재합니다.");
        }
        Member member = memberDao.insert(Member.builder()
                .username(signUpRequest.getUsername())
                .password(signUpRequest.getPassword())
                .name(signUpRequest.getName())
                .roleId("USER")
                .build());
        pointService.initInsert(member.getId());
    }

    @Override
    public void update(MemberDto.FormRequest formRequest,
                       Authentication authentication){
        Member member = this.findByUsername(authentication);
        if(member.getId() == formRequest.getId()
                && passwordEncoder.matches(formRequest.getPassword(), member.getPassword())
                && StringUtils.equals(formRequest.getChangePassword(), formRequest.getChangePasswordConfirm())){
            memberDao.update(Member.builder()
                            .id(formRequest.getId())
                            .name(formRequest.getName())
                            .password(StringUtils.isNotBlank(formRequest.getChangePassword())
                                    ? formRequest.getChangePassword()
                                    : formRequest.getPassword())
                    .build());
        }else{
            throw new NotEqualsMemberPasswordException();
        }
    }

    @Override
    public Optional<Member> findById(Integer id){
        return memberDao.findById(id);
    }

    @Override
    public List<Member> findAll(){
        return memberDao.findAll();
    }


    @Override
    public MemberDto.FormResponse findByAuthenticationWithRoleName(Authentication authentication){
        return memberDao.findByUsernameWithRoleName(authentication.getName()).orElseThrow();
    }

    @Override
    public void updateRole(MemberDto.RoleUpdateRequest roleUpdateRequest){
        memberDao.updateRoleIdById(roleUpdateRequest.getRoleId(), roleUpdateRequest.getMemberId());
    }
}




