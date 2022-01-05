package com.example.thejavatest.part2_mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
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
import org.mockito.BDDMockito;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Ex05_BDD_스타일_Mockito_API {

    @DisplayName("BDD 스타일")
    @Test
    void testName(
        @Mock MemberService memberService,
        @Mock StudyRepository studyRepository
    ) {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("henry@email.com");

        Study study = new Study(10, "테스트");

        // when -> given
        // thenReturn -> willReturn
        // when(memberService.findById(1L)).thenReturn(Optional.of(member));
        // when(studyRepository.save(study)).thenReturn(study);
        given(memberService.findById(1L)).willReturn(Optional.of(member));
        given(studyRepository.save(study)).willReturn(study);

        // When
        studyService.createNewStudy(1L, study);

        // then
        assertEquals(member.getId(), study.getOwnerId());
        // verify(memberService, times(1)).notify(member);
        then(memberService).should(times(1)).notify(study);
        then(memberService).should(times(1)).notify(member);
        // verifyNoMoreInteractions(memberService);
        then(memberService).shouldHaveNoMoreInteractions();

    }
}
