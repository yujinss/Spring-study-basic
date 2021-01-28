package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    //데이터베이스 커넥션을 획득할 때 사용하는 객체
    DataSource dataSource; //스프링이 db 연결정보 객체를 만들어줌.(application.properties보고)
    // 자바코드로 직접 스프링 빈 등록하기. 장점 : 상황에 따라 구현 클래스 변경 시 간단
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){

        //return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
