package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest { //갖다쓸게 아니니까 굳이 public 아니어도 됨. test case의 장점은 class 레벨에서 돌리거나
    //패키지 우클릭해서 전체 테스트를 다 돌려볼 수 있다.
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //메서드가 끝날때마다 호출되는 콜백 메서드
    public void afterEach(){
        repository.clearStore();
        //이렇게 하면 테스트가 끝날 때마다 저장소를 한번씩 지운다.
    }

    @Test
    public void save(){ //메소드 단위로 테스트 할 수 있다.
        Member member = new Member();
        member.setName("spring");

        repository.save(member); //저장할때 id 셋팅되니깐

        Member result = repository.findById(member.getId()).get(); //아이디 가지고 오는지 확인. optional에서 값을 꺼낼때는 get으로꺼낸다(좋은 방법은 아닌데 test code니깐..)
        Assertions.assertEquals(member,result);//junit이 제공하는 Assertions로 같은지 확인. system.print.ln으로 일일히 확인하는것 보다 훨씬 나음!!
        assertThat(member).isEqualTo(result);//요즘에는 junit보다 assertj가 제공하는걸 씀! 좀 더 편함. static import 하면 Assertions 안적어도 됨
        //실무에서는 이거를 빌드툴이랑 엮어서 빌드툴에서 빌드할 때 그냥 오류테스트 케이스 통과하지않으면 다음단계로 못넘어가게 막아버린다.

    }

    @Test
    public void findById(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
    //여기까지 작성하고 클래스 레벨로 테스트 돌리면 findById()가 실패한다. 아깐 성공했었는데?!
    //모든 테스트는 순서보장이 되지 않긴한데, 모든 메서드는 순서와 상관없이 따로 잘 동작해야한다.
    //순서에 의존적으로 설계하면 절대 안된다. spring1, spring2가 이미 저장됐기 때문에 이전에 저장된게 나와서 생긴문제.
    //그러므로 테스트가 하나끝나고 나면 공용 데이터를 깔끔하게 클리어 해줘야한다!

    //여러명이서 개발할 때는.. 소스코드가 몇만라인\,몇십만라인 넘어가면 테스트코드 없이 개발하기가 거의 불가능.
    //테스트코드 관련해 깊이있게 공부하기를 추천!
}
