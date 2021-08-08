package com.example.mockmvc.application;

import com.example.mockmvc.entity.Member;
import com.example.mockmvc.form.MemberForm;
import com.example.mockmvc.form.MemberForm.Request.Modify;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public void update(Long id, Modify modify) {
        Member member = memberRepository.findById(id).orElseThrow(RuntimeException::new);
        member.modifyMemberId(modify.getMemberId());
        member.modifyPassword(modify.getPassword());
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

}
