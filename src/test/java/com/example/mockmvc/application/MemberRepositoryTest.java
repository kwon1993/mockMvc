package com.example.mockmvc.application;

import com.example.mockmvc.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void save() {
        Member member = Member.builder().memberId("member").password("password").build();
        Member savedMember = memberRepository.save(member);

        assertEquals(member.getId(), savedMember.getId());
    }

    @Test
    public void findById() {
        Member member = Member.builder().memberId("member").password("password").build();
        memberRepository.save(member);

        assertEquals(member, memberRepository.findById(member.getId()).get());
    }

    @Test
    public void findAll() {
        memberRepository.save(Member.builder().id(1L).memberId("member1").password("password1").build());
        memberRepository.save(Member.builder().id(2L).memberId("member2").password("password2").build());
        memberRepository.save(Member.builder().id(3L).memberId("member3").password("password3").build());
        memberRepository.save(Member.builder().id(4L).memberId("member4").password("password4").build());
        memberRepository.save(Member.builder().id(5L).memberId("member5").password("password5").build());

        assertEquals(memberRepository.findAll().size(), 5);
    }

    @Test
    public void update() {
        Member member = Member.builder().memberId("member").password("password").build();
        memberRepository.save(member);

        Member findById = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        findById.modifyMemberId("modifiedMemberId");

        assertEquals(memberRepository.findById(1L).get().getMemberId(), "modifiedMemberId");
    }

    @Test
    public void delete() {
        Member member = Member.builder().memberId("member").password("password").build();
        memberRepository.save(member);

        memberRepository.deleteById(1L);

        Assertions.assertThatThrownBy(() -> {
            memberRepository.deleteById(1L);
        }).isInstanceOf(Exception.class);
    }

}