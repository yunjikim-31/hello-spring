package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name); // findById가 없으면 Null로 반환하는데, 옵셔널 써서 반환함
    List<Member> findAll();
}
