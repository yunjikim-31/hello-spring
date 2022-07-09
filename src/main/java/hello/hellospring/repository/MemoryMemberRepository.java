package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    // 메모리 구현체니까 어딘가에 저장해야 하므로 Map<> 사용함

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        // 먼저 Member의 ID 값을 셋팅 이후 store에 저장
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member; // 저장된 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        // Null이 반환 될 가능성이 있을 경우, Optional로 감싸서 반환
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // 루프를 돌면서 (.stream) 이름이 같은 경우에만 (.equals(name)) 한 개 찾아지면 (.findAny) Optional 로 반환
    }

    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
