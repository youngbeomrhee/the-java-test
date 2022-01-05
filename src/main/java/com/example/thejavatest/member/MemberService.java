package com.example.thejavatest.member;

import com.example.thejavatest.domain.Study;
import java.util.Optional;
import com.example.thejavatest.domain.Member;

public interface MemberService {

    Member findByIdOld(Long memberId);

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Study study);

    void notify(Member member);
}
