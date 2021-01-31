# 2week

## Facts (한 것)

- 코드숨 2주차 과제 진행

[ [Week2\] Create Todo REST API with Spring Web by rlawlstjd0077 · Pull Request #5 · CodeSoom/spring-week2-assignment-12021-01-25 진행내역 TDD 방식으로 Task Controller 구현 (GET, POST, PUT, PATCH, DELETE) MockMvc 활용github.com](https://github.com/CodeSoom/spring-week2-assignment-1/pull/5)

------

## Feelings (느낀점)

### **Spring Framework**

- 1주차 과제와 비교를 해보았을 때 여태껏 뭘 한거지? 라는 생각이 들었습니다. 
  - 그만큼 Spring Framework 을 통해서 1주차 과제에서 씨름하고 고민하던 문제들이 아주 간단하게 해결할 수 있었습니다.
  - 그렇지만 동일한 어플리케이션을 두가지 방법을 구현을 해보면서 장단점을 좀더 확실하게 느낄 수가 있었던 것 같습니다.
- 가장 크게 느꼈던 것은 Spring은 개발자로 하여금 '본질'에 집중할 수 있게 해주는 장점을 가지고 있는 것 같습니다. 
  - HTTP Handler는 말할 것도 없고 .. IoC가 뭐가 좋은 걸까? 라는 생각을 했는데 돌이켜 보면 객체(빈)들의 생성, 의존성 관리를 신경쓰지 않아도 되는 것이 얼마나 편리한지 몸소 느끼게 되었습니다. 

### **TDD를 시도해보다**

- 2주차 과제를 시작하면서 가장 먼저 시도 했던 것이 Controller (HTTP 요청 처리 로직)에 대한 Test Code를 TDD 방식으로 작성하면서 구현을 했던 것이었습니다.
- 기능 단위로 커밋을 하며 Test Code 작성 -> 로직 구현 방식으로 진행을 했는데 흥미로운 감정을 느꼈습니다.
  - 알수 없는 편안함이 온몸을 감쌌고 ... 마치 견고한 탑을 빈틈 없이 쌓는 느낌이 들었습니다. 
  - 확실히 어떤 기능을 어떻게 구현을 해야 하는지 테스트 코드로 명확하게 인지할 수 있어서 헤매는 시간을 많이 줄여줬던 것 같습니다.
- TDD와는 관련이 없지만 Spring에서 제공해주는 Web MVC 관련 Test 기능은 정말 강력한 것 같습니다 .. 

### **D-C-I 패턴을 Test Code에 적용해보다**

- [종립님 블로그](https://johngrib.github.io/wiki/junit5-nested/) 를 통해서 작성한 Test Code를 Describe - Context - It 패턴으로 바꿔보았는데 정말 흥미로웠습니다.
- 이전 까지는 테스트 코드를 작성할 때 항상 어딘가 숨겨져 있을 테스트 케이스에 대해서 불안해 하곤 했는데 계층 적인 구조로 테스트 코드를 작성하고 확인해볼 수 있으니 빈틈을 찾기가 훨씬 쉬웠습니다.
- 무엇보다 아래 처럼 어썸한 테스트 결과를 눈으로 보는 순간 정말 짜릿함을 느낍니다. 편 ㅡㅡ 안
  - 블로그에서도 장점중에 하나로 소개하는데 왜인지는 모르겠지만 재미가 느껴집니다..
  - 꼭 회사에서도 도입해보고 동료들에게 공유해주고 싶은 마음이 **격하게** 들었습니다.. 



![img](https://blog.kakaocdn.net/dn/lviMR/btqVbW4c5Xg/BKEpC0c4dXDdNkkGrY64E0/img.png)



- 칭찬은 덤입니다 :) ㅎㅎㅎㅎ 



![img](https://blog.kakaocdn.net/dn/Nojde/btqVddEolmw/hQo8XS1RujnwasvFyow1Hk/img.png)



### **무엇을 중점적으로 배울지?**

- 1주차 과제를 마무리하면서 윤석님께 받았던 피드백이었습니다.



![img](https://blog.kakaocdn.net/dn/GINyK/btqVbvMEVtY/GnEqdwHSRqboyR6gzSzihK/img.png)



- 그래 다음주 과제를 하게 되면 무엇을 중점적으로 학습할지 꼭 정해야 겠어 .. !
  - 라고는 다짐했지만 실상은 그러지 못했던 것 같습니다 .. 
- 막 정신없이 이것저것 학습을 하다보니 집중적으로 학습한 것이 없었던 것 같습니다.. 

-  

------

## Findings (배운것)

(모아보니 생각보다 많군 .. )

### **Spring**

- [스프링 필드 인젝션이 좋지 않은 이유](https://github.com/rlawlstjd0077/TIL/blob/8f17a5ee9d/Spring/CodeSoom/2020-01-27.md#스프링-필드-인젝션이-좋지-않은-이유)
- [Sprinb Web MVC Exception Handling 방법](https://github.com/rlawlstjd0077/TIL/blob/8f17a5ee9d/Spring/CodeSoom/2020-01-27.md#spring-mvc-exception-handling)
- [스프링 웹 프레젠테이션 계층 기술](https://github.com/rlawlstjd0077/TIL/blob/8f17a5ee9d/Spring/CodeSoom/2020-01-28.md#스프링-웹-프레젠테이션-계층-기술)
- [Spring Boot Interface DI 시 구현 클래스 지정 방법](https://github.com/rlawlstjd0077/TIL/blob/8f17a5ee9d/Spring/CodeSoom/2020-01-29.md#spring-boot-interface-di-시-구현-클래스-지정-방법)
- [Spring Bean Scope ](https://github.com/rlawlstjd0077/TIL/blob/8f17a5ee9d/Spring/CodeSoom/2020-01-30.md#spring-bean-scope)

### **Java**

- [Java hashCode() 메소드를 선언해야 하는 이유](https://github.com/rlawlstjd0077/TIL/blob/8f17a5ee9d/Spring/CodeSoom/2020-01-30.md#hashcode)
- [Null 서바이벌 가이드](https://github.com/rlawlstjd0ㅁ077/TIL/blob/8f17a5ee9d/Spring/CodeSoom/2020-01-27.md#null-서바이벌-가이드)

### **Test**

- [JUnit5 Describe - Context - It 패턴 적용 방법](https://github.com/rlawlstjd0077/TIL/blob/8f17a5ee9d/Spring/CodeSoom/2021-01-26.md#test-describe---context---it-패턴)
- [JUnit @Before, @BeforeEach의 차이점](https://github.com/rlawlstjd0077/TIL/blob/8f17a5ee9d/Spring/CodeSoom/2021-01-26.md#junit-before-beforeeach-의-차이점)

 

------

## Future (계획, 행동)

### **중점적으로 학습할 것들을 선언하자**

- 사실 이번주차에는 주도적으로 무엇을 학습할 것인지를 정하지는 않았었습니다.
- 이제 부터는 학습하고자 하는 주제를 몇가지 정하고 목표로 삼아 과제를 진행하면서 학습을 진행할 것 입니다.

### **그날그날 꼭 느낀점은 적자**

- 이번주차에는 Daily Note에 배운점만 적었는데 돌아보면 그때그때 느낀점들이 배운점 못지 않게 중요하구나 라는 것을 깨달았습니다.
  - 느낀점은 곧 내가 무엇을 모르는지를 파악할 수 있는 중요한 단서인 것 같습니다.
- 주간 회고 말고도 일간 회고에서도 꼭 느낀점을 돌아보고 기록할 것 입니다.