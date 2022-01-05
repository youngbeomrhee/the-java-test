package com.example.thejavatest.part2_mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.thejavatest.domain.Study;
import com.example.thejavatest.domain.StudyStatus;
import com.example.thejavatest.member.MemberService;
import com.example.thejavatest.study.StudyRepository;
import com.example.thejavatest.study.StudyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Ex06_Mockito_연습_문제 {

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
    @Test
    void openStudy() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "더 자바, 테스트");

        // TODO studyRepository Mock 객체의 save 메서드호출 시 study를 리턴하도록 만들기
        when(studyRepository.save(study)).thenReturn(study);
        assertNull(study.getOpenedDateTime());

        // When
        studyService.openStudy(study);

        // Then
        // TODO study의 status가 OPENED로 변경됐는지 확인
        assertEquals(study.getStatus(), StudyStatus.OPENED);
        // TODO study의 openedDateTime이 null이 아닌지 확인
        assertNotNull(study.getOpenedDateTime());
        // TODO memberService의 notify(study)가 호출됐는지 확인
        verify(memberService).notify(study);
    }


    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다. - BDD 스타일")
    @Test
    void openStudy2() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "더 자바, 테스트");

        // TODO studyRepository Mock 객체의 save 메서드호출 시 study를 리턴하도록 만들기
        given(studyRepository.save(study)).willReturn(study);
        assertNull(study.getOpenedDateTime());

        // When
        studyService.openStudy(study);

        // Then
        // TODO study의 status가 OPENED로 변경됐는지 확인
        assertEquals(study.getStatus(), StudyStatus.OPENED);
        // TODO study의 openedDateTime이 null이 아닌지 확인
        assertNotNull(study.getOpenedDateTime());
        // TODO memberService의 notify(study)가 호출됐는지 확인
        then(memberService).should().notify(study);
    }


}
