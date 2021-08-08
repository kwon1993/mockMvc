package com.example.mockmvc.application;

import com.example.mockmvc.entity.Member;
import com.example.mockmvc.form.MemberForm.Request.Modify;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@Transactional
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    public void saveTest() {
        Member member = Member.builder().memberId("member").password("password").build();

        given(memberRepository.save(any())).willReturn(member);

        memberService.save(member);

        verify(memberRepository).save(member);

        assertEquals(member.getMemberId(), "member");
        assertEquals(member.getPassword(), "password");
    }

    @Test
    public void findByIdTest() {
        Member member = Member.builder().id(1L).memberId("member").password("password").build();

        given(memberRepository.findById(anyLong())).willReturn(Optional.of(member));

        Member findById = memberService.findById(1L);

        verify(memberRepository).findById(eq(1L));

        assertEquals(1L, findById.getId());
        assertEquals("member", findById.getMemberId());
    }

    @Test
    public void findAllTest() {
        List<Member> members = Arrays.asList(
                Member.builder().id(1L).memberId("member1").password("1111").build(),
                Member.builder().id(2L).memberId("member2").password("2222").build(),
                Member.builder().id(3L).memberId("member3").password("3333").build(),
                Member.builder().id(4L).memberId("member4").password("4444").build(),
                Member.builder().id(5L).memberId("member5").password("5555").build()
        );

        given(memberRepository.findAll()).willReturn(members);

        List<Member> findAll = memberService.findAll();

        verify(memberRepository).findAll();

        assertEquals(members.size(), findAll.size());
    }

    @Test
    public void updateTest() {
        Member member = Member.builder().id(1L).memberId("member").password("password").build();
        Modify modify = Modify.builder().memberId("modifiedMember").password("modifiedPassword").build();

        given(memberRepository.findById(1L)).willReturn(Optional.of(member));

        memberService.update(1L, modify);

        verify(memberRepository).findById(eq(1L));

        assertEquals(member.getMemberId(), "modifiedMember");
    }

    @Test
    public void deleteTest() {
        memberService.deleteById(1L);

        verify(memberRepository).deleteById(eq(1L));
    }

}