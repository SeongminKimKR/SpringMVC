package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
