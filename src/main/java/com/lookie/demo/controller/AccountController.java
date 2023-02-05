package com.lookie.demo.controller;

import com.lookie.demo.domain.User;
import com.lookie.demo.service.UserServiceImpl;
import com.lookie.demo.web.dto.UserJoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final UserServiceImpl userService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false)String error,
                        @RequestParam(value = "exception", required = false)String exception,
                        Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        System.out.println("로그인get");

        return "account/login";
    }

    @GetMapping("/join")
    public String join() {
        System.out.println("회원가입get");
        return "account/join";
    }

    @PostMapping("/join")
    public String join(UserJoinRequestDto user){
        System.out.println("회원가입회원가입");
        userService.join(user);
        System.out.println("회원가입회원가입");
        return "redirect:/";
    }
}
