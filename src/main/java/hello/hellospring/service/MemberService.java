package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    // ()는 생성자를 호출할 때 필요한 값들
    // 직접 new로 생성하는게 아니고 외부에서 생성하도록 변경하므로 memberRepository 를 외부에서 넣어줌
    // 이걸 Dependency injection, 의존성 주입이라고 함
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
        회원 가입
    */

    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원X
        // 바로 직접 꺼내는 걸 권장하지 않기 때문에 Optional을 통해 감싼 걸 가져와야 함
        // Optional을 지웠지만 어차피 result가 반환됐기 때문에 ifPresent를 사용함
        validateDuplicateMember(member); // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
        전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
