package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// 다른데에서 갖다쓸게 아니라서 public 지움
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트 메소드 끝나면 afterEach()가 실행됨, 그래서 실행 순서가 상관이 없어짐
    // 서로 의존관계 없애기 위해서 하나의 테스트가 끝날 때마다 저장소, 공용 데이터 지워줘야 함
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spirng");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // 검증, new에서 저장한거랑 db에서 꺼낸거랑 데이터가 완전히 똑같으면 참
        assertThat(member).isEqualTo(result);
        // member는 가져온 result 와 똑같다.
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        // result와 member1의 메모리 주소 비교
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        // 스프링 안에 이미 해당 정보가 저장되었으므로 findByName()이 에러가 뜸
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // 컬렉션인 List 안에 Member를 넣어놨는데, Member 안에 member1, member2가 있으므로
        // size()는 2가 나와야 함
        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
