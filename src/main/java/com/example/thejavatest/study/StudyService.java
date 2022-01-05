package com.example.thejavatest.study;

import com.example.thejavatest.domain.Study;
import com.example.thejavatest.domain.Member;
import com.example.thejavatest.member.MemberService;
import java.util.Optional;

public class StudyService {

    private final MemberService memberService;

    private final StudyRepository repository;

    public StudyService(MemberService memberService, StudyRepository repository) {
        // 둘 다 null이면 안되므로 검사로직 추가
        assert memberService != null;
        assert repository != null;
        this.memberService = memberService;
        this.repository = repository;
    }

    public Study createNewStudy1(Long memberId, Study study) {
        Member member = memberService.findByIdOld(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member doesn't exist for id: '" + memberId + "'");
        }
        study.setOwnerId(memberId);
        return repository.save(study);
    }

    public Study createNewStudy2(Long memberId, Study study) {
        Optional<Member> member = memberService.findById(memberId);
        if (member.isPresent()) {
            study.setOwnerId(memberId);
        } else {
            throw new IllegalArgumentException("Member doesn't exist for id: '" + memberId + "'");
        }
        Study newstudy = repository.save(study);
        return repository.save(study);
    }
    /*
    public Study createNewStudy3(Long memberId, Study study) {
        Optional<Member> member = memberService.findById(memberId);
        study.setOwnerId(member.orElseThrow(()->new IllegalArgumentException("Member doesn't exist for id: '" + memberId + "'")));
        return repository.save(study);
    }
    */

    public Study createNewStudy(Long memberId, Study study) {
        Optional<Member> member = memberService.findById(memberId);
        if (member.isPresent()) {
            study.setOwnerId(memberId);
        } else {
            throw new IllegalArgumentException("Member doesn't exist for id: '" + memberId + "'");
        }
        Study newStudy = repository.save(study);
        memberService.notify(newStudy);
//        memberService.notify(member.get());
        return newStudy;
    }

    public Study openStudy(Study study) {
        study.open();
        Study openedStudy = repository.save(study);
        memberService.notify(openedStudy);
        return openedStudy;
    }
}
