package com.github.gun2.beadalbujok.controller;

import com.github.gun2.beadalbujok.component.PreAuthComponent;
import com.github.gun2.beadalbujok.domain.Member;
import com.github.gun2.beadalbujok.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    private final MemberService memberService;
    private final PreAuthComponent preAuthComponent;

    @GetMapping("/")
    public String home(){
        return "redirect:/order";
    }

    @GetMapping("/login")
    public String login(Model model,
                        HttpServletRequest httpServletRequest){
        model.addAttribute("error", httpServletRequest.getParameterMap().containsKey("error"));
        return "login";
    }

    @GetMapping("/sign-up")
    public String signUp(){
        return "signUp";
    }

    @GetMapping("/template/head")
    public String head(){
        return "template/head";
    }

    @GetMapping("/template/nav")
    public String nav(Authentication authentication,
                      Model model){
        if(authentication != null && authentication.isAuthenticated()){
            String name = authentication.getName();
            Optional<Member> byUsername = memberService.findByUsernameEager(name);
            if (byUsername.isPresent()){
                model.addAttribute("member", byUsername.get());
            }
        }
        return "template/nav";
    }

    @GetMapping("/template/footer")
    public String footer(){
        return "template/footer";
    }

    @GetMapping("/pre-auth")
    public String preAuth(HttpServletRequest request){
        if(preAuthComponent.isPreAuthenticated(request)){
            return "redirect:/";
        }
        return "preAuth";
    }

    @PostMapping("/pre-auth")
    public String preAuthCheck(@RequestParam("preAuth") String preAuth,
                               HttpServletResponse response){
        if(preAuthComponent.isEqualsPreAuthPassword(preAuth)){
            preAuthComponent.setPreAuthKeyInCookie(response);
            return "redirect:/";
        }else{
            return "redirect:/pre-auth?error=notMatched";
        }
    }
}
