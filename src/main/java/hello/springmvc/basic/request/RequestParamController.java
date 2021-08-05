package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={},age={}", username,age);

        response.getWriter().write("ok");
    }


    /**
     * @ResponseBody : View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
     * 클래스 상단에 @RestController를 넣는 것과 같은 효과
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ){
        log.info("username={},age={}", memberAge,memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ){
        log.info("username={},age={}", username,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    /**
     * String , int , Integer 등의 단순 타입이면 @RequestParam 도 생략 가능
     */
    public String requestParamV4(String username, int age){
        log.info("username={},age={}", username,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    /**
     * @RequestParam.required
     * 파라미터 필수 여부
     * 기본값이 파라미터 필수( true )이다.
     */
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age){
        log.info("username={},age={}", username,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    /**
     * 파라미터에 값이 없는 경우 defaultValue 를 사용하면 기본 값을 적용할 수 있다.
     * 이미 기본 값이 있기 때문에 required 는 의미가 없다.
     * defaultValue 는 빈 문자의 경우에도 설정한 기본 값이 적용된다.
     * /request-param?username=
     */
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age){
        log.info("username={},age={}", username,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    /**
     * 파라미터의 값이 1개가 확실하다면 Map 을 사용해도 되지만, 그렇지 않다면 MultiValueMap을 사용하자.
     */
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){
        log.info("username={},age={}", paramMap.get("username"),paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    /**
     * 스프링MVC는 @ModelAttribute 가 있으면 다음을 실행한다.
     * HelloData 객체를 생성한다.
     * 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다. 그리고 해당 프로퍼티의 setter를 호출해서 파라미터의 값을 입력(바인딩) 한다.
     * 예) 파라미터 이름이 username 이면 setUsername() 메서드를 찾아서 호출하면서 값을 입력한다.
     */
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    /**
     * @ModelAttribute 는 생략할 수 있다.
     * 그런데 @RequestParam 도 생략할 수 있으니 혼란이 발생할 수 있다.
     *
     * 스프링은 해당 생략시 다음과 같은 규칙을 적용한다.
     * String , int , Integer 같은 단순 타입 = @RequestParam
     * 나머지 = @ModelAttribute (argument resolver 로 지정해둔 타입 외)
     */
    public String modelAttributeV2(HelloData helloData){
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        return "ok";
    }


}
