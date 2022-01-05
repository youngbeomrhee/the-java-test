package com.example.thejavatest.part2_mockito;

import com.example.thejavatest.domain.Member;
import com.example.thejavatest.domain.Study;
import com.example.thejavatest.member.MemberService;
import com.example.thejavatest.study.StudyRepository;
import com.example.thejavatest.study.StudyService;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class) // Mockito annotation 활용시 필요
public class Ex01_Mock_객체_만들기 {

    @DisplayName("Mock 없이 구현")
    @Test
    void createStudyService() {
        // interface만 있고 구현체가 없으므로 인스턴스를 생성할 수 없다
        // 아래와 같이 모든 메서드를 override 해줘야 한다
        MemberService memberService = new MemberService() {
            @Override
            public Member findByIdOld(Long memberId) {
                return null;
            }

            @Override
            public Optional<Member> findById(Long memberId) {
                return Optional.empty();
            }

            @Override
            public void validate(Long memberId) {
            }

            @Override
            public void notify(Study study) {
            }

            @Override
            public void notify(Member member) {
            }
        };

        // studyRepository는 훨씬 더 많은 메서드가 있다
        StudyRepository studyRepository = new StudyRepository() {
            @Override
            public List<Study> findAll() {
                return null;
            }

            @Override
            public List<Study> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<Study> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public <S extends Study> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Study> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Study> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<Study> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Study getOne(Long aLong) {
                return null;
            }

            @Override
            public Study getById(Long aLong) {
                return null;
            }

            @Override
            public <S extends Study> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Study> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Study> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Study> S save(S entity) {
                return null;
            }

            @Override
            public Optional<Study> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(Study entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends Study> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Study> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Study> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Study> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Study> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Study, R> R findBy(Example<S> example,
                Function<FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };

        StudyService studyService = new StudyService(memberService, studyRepository);

        assertNotNull(studyService);
    }

    @DisplayName("Mockito 활용")
    @Test
    void mockitoInMethod() {
        // mock 메서드를 사용해서 특정 클래스를 mocking 할 수 있다
        MemberService memberService = mock(MemberService.class);
        StudyRepository studyRepository = mock(StudyRepository.class);

        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
    }

    // mocking하는 보다 간단한 방법 @Mock 어노테이션 활용
    // 단순히 @Mock 어노테이션만 붙이면 되는게 아니라 익스텐션을 등록해야 한다.
    // @ExtendWith(MockitoExtension.class) 상단에 추가
    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @DisplayName("Mockito annotation 활용")
    @Test
    void mockitoInClass() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
    }

    // 상단에 extension이 등록된 경우만 사용 가능
    @DisplayName("특정 메서드에서만 사용 - Mockito annotation을 파라미터에 사용")
    @Test
    void mockitoInjection(
        @Mock MemberService memberService,
        @Mock StudyRepository studyRepository
    ) {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
    }
}
