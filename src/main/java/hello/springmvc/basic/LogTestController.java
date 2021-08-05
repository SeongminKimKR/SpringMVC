package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Line 21 코드를 자동으로 생성해주는 롬복 기능
 */
@Slf4j
/**
 * @Controller 는 반환 값이 String 이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 랜더링 된다.
 * @RestController 는 반환 값으로 뷰를 찾는 것이 아니라, HTTP 메시지 바디에 바로 입력한다.
 * 따라서 실행 결과로 ok 메세지를 받을 수 있다.
 */
@RestController
public class LogTestController {
    //getClass() == LogTestController.class
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        /**
         * { }를 여러개 붙이면 여러 파라미터를 붙일 수 있다.
         * 로그를 등급별로 나눌수 있다.
         * LEVEL: TRACE > DEBUG > INFO > WARN > ERROR
         * 개발 서버는 debug 출력
         * 운영 서버는 info 출력
         * log.debug("data="+data) 실행은 되지만 불필요한 연산이 이루어진다.
         * 콘솔에만 출력하는 것이 아니라, 파일이나 네트워크 등, 로그를 별도의 위치에 남길 수 있다.
         * 특히 파일로 남길 때는 일별, 특정 용량에 따라 로그를 분할하는 것도 가능하다.
         * */

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log ={}", name);
        log.warn(" warn log ={}", name);
        log.error(" error log ={}", name);

        return "ok";
    }
}
