package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    //스프링컨테이너랑 통이 생기는데 memberController 객체를 생성해서 넣어두고 관리를 한다. -> 스프링 빈이 관리된다.
    //스프링 컨테이너에서 등록하고 받아써야 좋다.
    //우리가 new 하면 다른 여러 컨트롤러에서 갖다쓸 때 굳이 여러개 생성할 필요가 없으니까.
    private MemberService memberService; //필드 주입 별로 안좋다. 중간에 바꿔치기 할 수 있는 방법이 없다.

    @Autowired //MemberController가 생성될때 스프링 빈에 등록되어있는 MemberService를 가져다가 연결해줌 : 의존성주입
    public MemberController(MemberService memberService){ //생성자 주입 -> 권장!
        this.memberService = memberService;
    }

    //setter 주입의 단점 : memberService를 중간에 바꿀 일이 없는데 member controller를 호출했을 때 public으로 열려있어야해서 노출된다.
/*    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }*/
}
