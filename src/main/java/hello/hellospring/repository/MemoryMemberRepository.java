package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>(); // 실무에서는 동시성문제때문에 CuncurrencyMap을 쓴다
    private static long sequence = 0L;// key값을 생성해주는 애. 얘도 실무에서는 long보단 동시성문제땜에 어텀 long

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //id 세팅
        store.put(member.getId(),member);//store에 저장
        return member; //저장된 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //Optional로 감싸면 null일때 클라이언트가 따로작업할수있게 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //자바 람다식 문법
                .filter(member->member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() { //자바 실무시에는 List를 많이쓴다. loop 돌리기도 편하고 해서.
        return new ArrayList<>(store.values()); //멤버들을 쭉 반환
    }

    public void clearStore(){
        store.clear();
    }
}
