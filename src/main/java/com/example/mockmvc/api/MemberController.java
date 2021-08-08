package com.example.mockmvc.api;

import com.example.mockmvc.application.MemberService;
import com.example.mockmvc.entity.Member;
import com.example.mockmvc.form.MemberForm;
import com.example.mockmvc.form.MemberForm.Request.Join;
import com.example.mockmvc.form.MemberForm.Request.Modify;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/save")
    public Member save(Join join) {
        return memberService.save(Member.builder()
                                        .memberId(join.getMemberId())
                                        .password(join.getPassword())
                                        .build());
    }

    @GetMapping(value = "/findById/{id}")
    public Member findById(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @GetMapping(value = "/findAll")
    public List<Member> findAll() {
        return memberService.findAll();
    }

    @PatchMapping(value = "/update/{id}")
    public void update(@PathVariable Long id, Modify modify) {
        memberService.update(id, modify);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id) {
        memberService.deleteById(id);
    }
}
