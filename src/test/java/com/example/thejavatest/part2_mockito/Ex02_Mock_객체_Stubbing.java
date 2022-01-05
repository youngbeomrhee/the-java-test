package com.example.thejavatest.part2_mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.example.thejavatest.domain.Member;
import com.example.thejavatest.member.MemberService;
import com.example.thejavatest.study.StudyRepository;
import com.example.thejavatest.study.StudyService;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class) // Mockito annotation 활용시 필요
public class Ex02_Mock_객체_Stubbing {

    @DisplayName("Mocking한 객체의")
    @Test
    void createStudyService(
        @Mock MemberService memberService,
        @Mock StudyRepository studyRepository
    ) {
        Long memberId = 1L;
        Optional<Member> optionalMember = memberService.findById(memberId);
        // 실제로는 비어있음
        assertTrue(optionalMember.isEmpty());
        // void 메서드인 경우, 아무 일도 일어나지 않음 (에러를 던지지 않음)
        memberService.validate(memberId);

//        StudyService studyService = new StudyService(memberService, studyRepository);
//        assertNotNull(studyService);
    }

    @DisplayName("Stubbing 하기 - 특정 개체 리턴하기")
    @Test
    void stubbingTest(
        @Mock MemberService memberService,
        @Mock StudyRepository studyRepository
    ) {
        // 특정한 매개변수를 받은 경우 특정한 값을 리턴하도록 만들 수 있다
        Long memberId = 1L;

        Member member = new Member();
        member.setId(memberId);
        member.setEmail("henry@email.com");

        // memberId로 조회해보면 비어있음
        assertTrue(memberService.findById(memberId).isEmpty());

        // Stubbing 하기
        when(memberService.findById(memberId))
            .thenReturn(Optional.of(member));

        Optional<Member> optionalMember = memberService.findById(memberId);
        // memberId로 조회하면 member가 리턴
        assertTrue(optionalMember.isPresent());
        assertEquals(optionalMember.get(), member);

        // 매개변수가 달라지면 Stubbing이 되지 않는다
        Optional<Member> optionalMember2 = memberService.findById(2L);
        assertFalse(optionalMember2.isPresent());
        assertTrue(optionalMember2.isEmpty());


        Long memberId2 = 2L;
        Member member2 = new Member();
        member2.setId(memberId2);
        member2.setEmail("henry2@email.com");

        // 파라미터를 any로 Stubbing 하기
        when(memberService.findById(any()))
            .thenReturn(Optional.of(member2));

        Optional<Member> optionalMember3 = memberService.findById(memberId2);
        assertTrue(optionalMember3.isPresent());
        assertFalse(optionalMember3.isEmpty());

        // 이전에 Stubbing했던 객체와는 다르다
        assertNotEquals(optionalMember, optionalMember3);

        // 이전에 Stubbing했던 객체를 다시 조회
        optionalMember = memberService.findById(memberId);
        assertEquals(optionalMember, optionalMember3);
    }


    @DisplayName("Stubbing 하기 - 예외를 던지도록")
    @Test
    void stubbingTest2(
        @Mock MemberService memberService,
        @Mock StudyRepository studyRepository
    ) {
        // 예외를 던지도록 Stubbing
        when(memberService.findById(-1L))
            .thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> memberService.findById(-1L));

        // void method인 경우
        doThrow(new IllegalArgumentException())
            .when(memberService)
            .validate(-1L);

        assertThrows(IllegalArgumentException.class, () -> memberService.validate(-1L));

        assertDoesNotThrow(() -> memberService.validate(2L));
    }

    @DisplayName("Stubbing 하기 - 반복하기")
    @Test
    void stubbingTest3(
        @Mock MemberService memberService,
        @Mock StudyRepository studyRepository
    ) {
        Member member = new Member();
        member.setId(1L);
        member.setEmail("henry@email.com");

        when(memberService.findById(any()))
            .thenReturn(Optional.of(member))
            .thenThrow(new RuntimeException())
            .thenReturn(Optional.empty());

        Optional<Member> byId = memberService.findById(1L);
        assertEquals(member, byId.get());

        assertThrows(RuntimeException.class, () -> memberService.findById(1L));

        assertEquals(Optional.empty(), memberService.findById(1L));
    }
}


