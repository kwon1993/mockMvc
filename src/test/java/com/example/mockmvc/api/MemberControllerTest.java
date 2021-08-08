package com.example.mockmvc.api;

import com.example.mockmvc.application.MemberService;
import com.example.mockmvc.entity.Member;
import com.example.mockmvc.form.MemberForm.Request.Modify;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class MemberControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;


    @BeforeEach
    void setUp(@Autowired MemberController memberController) {
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    public void saveTest() throws Exception {
        Member member = Member.builder().id(1L).memberId("member").password("password").build();
        String content = new ObjectMapper().writeValueAsString(member);

        ResultActions resultActions = mockMvc.perform(post("/save")
                                             .contentType(APPLICATION_JSON)
                                             .content(content)
                                             .accept(APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                     .andDo(print());
    }

    @Test
    public void findByIdTest() throws Exception {
        Long id = 1L;

        Member member = Member.builder().id(1L).memberId("member").password("password").build();

        given(memberService.findById(id)).willReturn(member);

        ResultActions resultActions = mockMvc.perform(get("/findById/{id}", id)
                                             .accept(APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                     .andDo(print());
    }

    @Test
    public void findAllTest() throws Exception {
        List<Member> members = Arrays.asList(
                Member.builder().id(1L).memberId("member1").password("password1").build(),
                Member.builder().id(2L).memberId("member2").password("password2").build(),
                Member.builder().id(3L).memberId("member3").password("password3").build(),
                Member.builder().id(4L).memberId("member4").password("password4").build(),
                Member.builder().id(5L).memberId("member5").password("password5").build()
        );

        given(memberService.findAll()).willReturn(members);

        ResultActions resultActions = mockMvc.perform(get("/findAll")
                                             .accept(APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                     .andDo(print());
    }

    @Test
    public void updateTest() throws Exception {
        Long id = 1L;

        Member member = Member.builder().id(1L).memberId("member").password("password").build();

        Modify modify = Modify.builder()
                              .memberId("modifiedMemberId")
                              .password("modifiedPassword")
                              .build();

        String content = new ObjectMapper().writeValueAsString(modify);

        ResultActions resultActions = mockMvc.perform(patch("/update/{id}", id)
                                             .contentType(APPLICATION_JSON)
                                             .content(content)
                                             .accept(APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                     .andDo(print());

        verify(memberService).update(eq(1L), any());
    }

    @Test
    public void deleteTest() throws Exception {
        Long id = 1L;

        ResultActions resultActions = mockMvc.perform(delete("/delete/{id}", id)
                                             .accept(APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                     .andDo(print());

        verify(memberService).deleteById(eq(1L));
    }
}