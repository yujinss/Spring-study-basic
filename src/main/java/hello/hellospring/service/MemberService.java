package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    //service 클래스는 비즈니스 용어를 쓰는게 좋다.
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원X
        validateDuplicateMember(member); //중복 회원 검증. 로직이 쭉나오는 경우는 메서드로 뽑는게 좋다.
        memberRepository.save(member);
        return member.getId();
        //꺼낼때는 get말고 orElseGet 씀.
    }

    private void validateDuplicateMember(Member member) {
        //null이 아니라 어떤 값이 있으면 이 로직이 동작. optional이기 때문에 가능한 것!
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
