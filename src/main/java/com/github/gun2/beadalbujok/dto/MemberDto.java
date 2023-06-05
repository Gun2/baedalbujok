package com.github.gun2.beadalbujok.dto;

import com.github.gun2.beadalbujok.domain.Member;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto implements DomainDto<Member> {
    /**
     *
     */
    private Integer id;

    /**
     * 사용자 아이디
     */

    private String username;

    /**
     * 닉네임
     */
    private String name;

    /**
     * 역할 id
     */
    private String roleId;

    /**
     *
     */
    private Date createdDate;

    /**
     *
     */
    private Date updatedDate;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.name = member.getName();
        this.roleId = member.getRoleId();
        this.createdDate = member.getCreatedDate();
        this.updatedDate = member.getUpdatedDate();
    }

    @Override
    public Member toVo() {
        return Member.builder()
                .id(this.id)
                .username(this.username)
                .name(this.name)
                .roleId(this.roleId)
                .createdDate(this.createdDate)
                .updatedDate(this.updatedDate)
                .build();
    }

    @Data
    public static class SignUpRequest{
        @NotBlank
        private String username;
        @NotBlank
        private String password;
        @NotBlank
        private String name;
    }

    @Data
    public static class FormRequest{
        @NotNull
        private Integer id;
        @NotBlank
        private String name;
        @NotBlank
        private String password;
        private String changePassword;
        private String changePasswordConfirm;

        @AssertTrue(message = "변경 비밀번호를 다르게 입력하셨습니다.")
        boolean isEqualsChangePassword(){
            return changePassword.equals(changePasswordConfirm);
        }
    }

    @Data
    public static class FormResponse{
        private Integer id;
        private String username;
        private String name;
        private String roleName;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
    }

    @Data
    public static class RoleUpdateRequest{
        @NotNull
        private Integer memberId;
        @NotBlank
        private String roleId;
    }

    @Getter
    @Setter
    @ToString
    public static class User extends MemberDto implements UserDetails{
        /**
         * 비밀번호
         */
        private String password;
        private String role;

        public User(Member member) {
            super(member);
            this.password = member.getPassword();
            this.role = member.getRoleId();
        }

        public User() {
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority("ROLE_"+this.role));
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }

}
