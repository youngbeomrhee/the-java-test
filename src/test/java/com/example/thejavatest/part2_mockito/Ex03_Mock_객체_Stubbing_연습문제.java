package com.example.thejavatest.part2_mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.thejavatest.domain.Member;
import com.example.thejavatest.domain.Study;
import com.example.thejavatest.member.MemberService;
import com.example.thejavatest.study.StudyRepository;
import com.example.thejavatest.study.StudyService;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Ex03_Mock_객체_Stubbing_연습문제 {

    @DisplayName("연습문제")
    @Test
    void testName(
        @Mock MemberService memberService,
        @Mock StudyRepository studyRepository
    ) {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("henry@email.com");

        Study study = new Study(10, "테스트");

        // TODO: memberService 객체에 findById 메소드를 1L 값으로 호출하면 Optional.of(member) 객체를 리턴하도록 Stubbing
        when(memberService.findById(1L))
            .thenReturn(Optional.of(member));

        // TODO: studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴하도록 Stubbing
        when(studyRepository.save(study))
            .thenReturn(study);
        Study study2 = studyService.createNewStudy(1L, study);

        assertEquals(member.getId(), study.getOwnerId());
        assertEquals(study, study2);
    }
}
