# Spring MVC 학습
- 출처: 인프런 김영한님 강의 '스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술' 
- https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-mvc-1/questions?s=index.html&page=1&type=question
- Spring MVC의 구조를 이해하고 어떤 기능이 있는지 살펴본다.

## 학습 목차

- [로깅](#로깅)
- [요청 매핑](#요청-매핑)
- [응답 매핑](#응답-매핑)
- [HTTP 메시지 컨버터](#HTTP-메시지-컨버터)
- [요청 매핑 핸들러 어댑터](#요청-매핑-핸들러-어댑터)
---
## 로깅
 - 로깅 라이브러리
    - 스프링 부트 라이브러리를 사용하면 스프링 부트 로깅 라이브러리(spring-boot-start-logging)가 함께 포함된다.
    - 로깅 라이브러리는 인터페이스로 SLF4J가 있고 사용할 구현체는 Logback이다.

 - 로그 호출
    - 변수 선언
      ```
      private Logger log = LoggerFactory.getLogger(getClass());
      private static final Logger log = LoggerFactory.getLogger(Xxx.class)
      ```
    - 이것을 자동으로 선언하려면 롬복 어노테이션 '@Slf4j'를 사용하면 된다.
    
 - 로그 레벨
 
    ![log-level](./image/chapter1/log-level.PNG)
    - TRACE > DEBUG > INFO > WARN > ERROR
    - 개발 서버는 debug를 출력하는 것이, 운영서버는 info를 출력하는 것이 보편적이다.
    
 - 올바른 로그 사용법
    ```
    1. log.debug("data="+data)
    2. log.debug("data={}", data)
    ```
   - '1'은 문자 더하기 연산이 일어나서 불필요한 연산이 일어나게 된다.
   
   - 사실 로그를 호출하는 함수는 (String format, Object org)2개의 파라미터로 구성되어 있고 해당 로그가 출력 가능한 레벨이여야 로그가 출력된다.
 
 - 로그 사용시 장점
    
    - 로그 레벨에 따라 출력하고 싶은 로그를 출력 시킬 수 있다.
    - 콘솔 뿐만 아니라 파일이나 네트워크 등 로그를 별도 위치에 남길 수 있다.
    - 파일로 남길 때는 일별, 특정 용량에 따라 로그를 분할 하는 것이 가능하다.
    - 성능이 System.out 보다 좋다.
---
## 요청 매핑
 ### 어노테이션 기반 요청 메시지 처리
 - 스프링 컨테이너는 @Controller의 클래스를 찾아 스프링 빈에 등록하고 @RequestMapping 어노테이션을 찾아 해당 url을 WAS에 저장시킴으로써 처리할 준비를 마친다.
 - 기본적으로 DispatcherServlet 클래스를 사용하여 클라이언트와 HTTP 통신을 하게 된다.
 ### 요청 어노테이션
 - @RequestMapping
     ```
        1. @RequestMapping("/mapping-get-v1")
        2. @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
     ```
   - '1.'과 같이 url 경로만 지정해주거나 '2.'와 같이 HTTP 메서드 타입을 지정할 수 있다.
   - 메서드 타입을 지정하면 해당 url의 다른 메서드 타입의 요청이 들어오면 405 Method Not Allowed 에러를 반환한다.
 - HTTP 메서드 축약
    ```
     @GetMapping
     @PostMapping
     @PutMapping
     @DeleteMapping
     @PatchMapping
    ```
   - @RequstMapping에서 특정 메서드 타입을 지정하는 것 보다 축약된 메서드를 사용하는 것이 직관적이다.
   
 - 경로 변수 사용
      ```
        @GetMapping("/mapping/{userId}")
        public String mappingPath(@PathVariable String userId, @PathVariable LongorderId)
        
        2.
        @GetMapping("/mapping/users/{userId}/orders/{orderId}")
        public String mappingPath(@PathVariable String userId, @PathVariable Long orderId)
      ```
   - 
 