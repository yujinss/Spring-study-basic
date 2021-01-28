package hello.hellospring.controller;

public class MemberForm {
    private String name;//html 폼의 name값 => 변수이름같은데에 넣어줌

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
