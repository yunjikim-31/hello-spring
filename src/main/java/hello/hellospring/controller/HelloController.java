package hello.hellospring.controller;

import org.springframework.boot.BootstrapRegistryInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    //    localhost:8080/hello-mvc 요청이 들어오면 아래의 함수를 실행하라
    //    Spring이 자동으로 src/main/resouces의 templates에서 hello-mvc라는 이름의 View를 찾아서 반환함
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    //  @ResponseBody: http의 body 부에 return "hello " + name 을 직접 넣어주겠다는 의미
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello nsNaeRuMi, 요청한 클라이언트에게 View 없이 그대로 내려감
    }

    // JSON 방식임, 키-값 쌍
    @GetMapping("hello-api")
    @ResponseBody
    // @ResponseBody 애노테이션이 안 붙어있으면 Spring은 요청이 들어왔을 때 viewResolver에게 던짐
    // 그러나 @ResponseBody 애노테이션이 있으면 http의 응답에 데이터를 객체로 그대로 넘거벼림
    // 그래서 JSON 방식으로 데이터를 만들어서 http 응답에 반환하겠다고 함

    public Hello helloApi(@RequestParam("name") String name) {
        // @RequestParam("실제 값") String 설정할 변수 이름
        Hello hello = new Hello();
        hello.setName(name); // 파라미터로 넘어온 name 변경
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
