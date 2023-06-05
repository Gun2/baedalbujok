package com.github.gun2.beadalbujok.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gun2.beadalbujok.constant.SuccessCode;
import com.github.gun2.beadalbujok.domain.Member;
import com.github.gun2.beadalbujok.domain.Role;
import com.github.gun2.beadalbujok.dto.MemberDto;
import com.github.gun2.beadalbujok.dto.SuccessResponseDto;
import com.github.gun2.beadalbujok.exception.ImpossibleUpdateSelfException;
import com.github.gun2.beadalbujok.service.MemberService;
import com.github.gun2.beadalbujok.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final RoleService roleService;
    private final ObjectMapper objectMapper;

    @GetMapping("/my-info")
    public String myInfo(Model model,
                         Authentication authentication) {
        MemberDto.FormResponse formResponse = memberService.findByAuthenticationWithRoleName(authentication);
        model.addAttribute("member", formResponse);
        return "manage/myInfo";
    }

    @GetMapping("/member")
    public String memberList(Model model) throws JsonProcessingException {
        List<Role> roleList = roleService.findAll();

        model.addAttribute("roleJsonArray", objectMapper.writeValueAsString(roleList));

        return "manage/memberList";
    }

    @RestController
    @RequestMapping("/api")
    public class Api {
        @PostMapping("/v1/member")
        public ResponseEntity create(
                @Validated @RequestBody MemberDto.SignUpRequest signUpRequest) {
            memberService.insert(signUpRequest);
            return SuccessResponseDto.of("OK").toResponseEntity(SuccessCode.CREATED);
        }

        @PutMapping("/v1/member")
        public ResponseEntity update(
                Authentication authentication,
                @Validated @RequestBody MemberDto.FormRequest formRequest
        ) {
            memberService.update(formRequest, authentication);
            return SuccessResponseDto.of("OK").toResponseEntity(SuccessCode.OK);
        }

        @GetMapping("/v1/member")
        public ResponseEntity findAll(){
            List<Member> all = memberService.findAll();
            return SuccessResponseDto.of(all).toResponseEntity(SuccessCode.OK);
        }

        @PutMapping("/v1/member-role")
        public ResponseEntity updateRole(
                Authentication authentication,
                @Validated @RequestBody MemberDto.RoleUpdateRequest roleUpdateRequest
        ){
            if(((MemberDto.User)authentication.getPrincipal()).getId() == roleUpdateRequest.getMemberId()){
                throw new ImpossibleUpdateSelfException();
            }
            memberService.updateRole(roleUpdateRequest);
            return SuccessResponseDto.of("OK").toResponseEntity(SuccessCode.OK);
        }
    }
}
