package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")//map 어플리케이션에서 /hello 라고 들어오면 이 메서드를 호출함
    public String hello(Model model){ //spring이 model 만들어서 넘겨줌
        model.addAttribute("data","hello!!");
        return "hello";// resources/templates에 있는 이 html로 가서 랜더링.실행시켜라. => 타임리프 템플릿이 엔진 처리
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody//http의 body부에 이 데이터를 직접 넣어준다
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //뷰 없이 이 문자 그대로 내려감.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name); //value를 key에 set해준다.
        return hello; //객체 반환
    }

    static class Hello {
        private String name; //json에서 key

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
