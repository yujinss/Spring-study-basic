package hello.hellospring.domain;

public class Member {
    private Long id; //시스템에 저장을 할 때 등록되는 번호
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
