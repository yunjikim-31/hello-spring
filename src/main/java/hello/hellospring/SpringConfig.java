package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Member;

@Configuration
public class SpringConfig {

    // 멤버서비스를 스프링빈에 등록
    @Bean
    public MemberService memberService() {
        // 생성자인 MemberService에 memberRepository()를 넣음
        return new MemberService(memberRepository());
    }

    // 멤버리포지토리를 스프링빈에 넣음
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
