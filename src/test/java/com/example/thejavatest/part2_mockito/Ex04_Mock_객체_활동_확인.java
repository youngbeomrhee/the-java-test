package com.example.thejavatest.part2_mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.example.thejavatest.domain.Member;
import com.example.thejavatest.domain.Study;
import com.example.thejavatest.member.MemberService;
import com.example.thejavatest.study.StudyRepository;
import com.example.thejavatest.study.StudyService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Ex04_Mock_객체_활동_확인 {

    @DisplayName("객체 활동 확인")
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

        when(memberService.findById(1L))
            .thenReturn(Optional.of(member));

        when(studyRepository.save(study))
            .thenReturn(study);
        Study study2 = studyService.createNewStudy(1L, study);

        assertEquals(member.getId(), study.getOwnerId());
        assertEquals(study, study2);

        // memberService내의 notify 메서드가 1번씩 호출됐는지 테스트 (호출되는 순서는 상관X)
        verify(memberService, times(1)).notify(member);
        // 해당 mock에 더이상의 추가 interaction이 없는지 테스트
//        verifyNoMoreInteractions(memberService);
        verify(memberService, times(1)).notify(study);
        verifyNoMoreInteractions(memberService);
        // 전혀 호출되고 있지 않는 경우도 테스트 가능
        verify(memberService, never()).validate(any());

        // 호출순서를 테스트 notify(study) -> notify(member)
        InOrder inOrder = inOrder(memberService);
        inOrder.verify(memberService).notify(study);
        inOrder.verify(memberService).notify(member);
    }
}
