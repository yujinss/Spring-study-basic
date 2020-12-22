package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    //스프링컨테이너랑 통이 생기는데 memberController 객체를 생성해서 넣어두고 관리를 한다. -> 스프링 빈이 관리된다.
    private final MemberService memberService;
    //스프링 컨테이너에서 등록하고 받아써야 좋다.
    //우리가 new 하면 다른 여러 컨트롤러에서 갖다쓸 때 굳이 여러개 생성할 필요가 없으니까.

    @Autowired //MemberController가 생성될때 스프링 빈에 등록되어있는 MemberService를 가져다가 연결해줌 : 의존성주입
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }


}
