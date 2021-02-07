## Facts (한 것)

- 코드숨 3주차 과제 진행 - 테스트 작성하기

[ [Week3\] Create Todo REST API with Spring Web by rlawlstjd0077 · Pull Request #2 · CodeSoom/spring-week3-assignment-12021-02-01 진행 내역 HelloController Test 작성 HelloController Web MVC Test 작성 질문 Spring Web MVC 테스트시에 WebMvcTest 의 용도(어떤 때에 사용이 되는지)가 정확히 어떻게 되는지 궁금합니다. 생각해보기론 Autgithub.com](https://github.com/CodeSoom/spring-week3-assignment-1/pull/2)

 

------

## Feelings (느낀점)

#### **Test Code**

- **D - C - I 패턴**을 적용해보면서 동시에 **Given - When - Then 템플릿**에 맞춰서 테스트 코드를 작성해보았는데 생각보다 둘의 조합이 아주 좋았던 것 같다. 
  - 무엇보다 Given - When - Then 템플릿은 만족도가 아주 좋았다.
  - 템플릿에 맞추어 테스트를 작성하다보면 절로 테스트 코드의 구조와 표현이 명확해지는 것을 느꼈다.

- '**테스트는 일종의 실험실**이다. 엄격하게 청결을 유지하는 실험실을 떠올려보라.' 라는 종립님의 피드백을 보았는데 많은 생각을 하게끔 만드는 것 같다. 
  - 함정은 희미한 느낌만 느껴지는 것 뿐이라는 ..
  - 지금으로써는 테스트 코드를 최대한 간결하고 명확하게 작성해야 된다는 것으로 이해를 하게 되었다. 
- 좋은 습관을 들이기 시작한 것 같아서 너무 기분이 좋다.
  - 이전에는 테스트 코드 라고 하면 막연한 귀찮음 vs 양심이 충돌을 하곤 했다. 물론 귀찮음이 항상 이겼다. 
  - 이번주 과제를 통해서 테스트 코드를 작성하는데 재미를 느꼈다.. 모든 테스트 케이스를 처리했을 때 초록 막대를 보는 것은 덤..
  - 거리낌 없이 테스트 코드를 작성할 수 있게 된 것이 제일 고무적인 것 같다.

 

#### **학습에 대한 의심**

- 학습에 대한 불신이 종종 생긴다. 
  - 과제를 진행하면서 트레이너님의 피드백, 혹은 개인적인 궁금증으로 여러 개념, 지식들에 대해서 알아보고 정리하는 식으로 학습을 하고 있다. 
  - 그런데 종종 돌이켜보면 이게 잘하고 있는 게 맞는지 하는 생각이 들때가 있다. 

#### **살아남는자가 강한 것**

- 1 ~ 2 주차는 열정에 불타는 느낌이었다면 3주차는 다소 힘을 뺀 느낌이다...
- 그러면서 문득 떠오른 말이 '**강한 자가 살아남는 것이 아닌 살아남는 자가 강한 것**' 이었다.
  - 핵심은 계속 앞으로 나아가는 것이기에 .. 

 

------

## Findings (배운점)

#### **Spring Test**

- [Spring Testing the Web Layer](https://github.com/rlawlstjd0077/TIL/blob/master/Spring/CodeSoom/2021-02-01.md#spring-testing-the-web-layer)

#### **Test Code**

- [Given을 더 명확하게 표현하고 싶다면 Before 메서드로 따로 분리하는 것도 고려해볼만 하다](https://github.com/rlawlstjd0077/TIL/blob/master/Spring/CodeSoom/2021-02-04.md#given을-더-명확하게-표현하고-싶다면-before-메서드로-따로-분리하는-것도-고려해볼만-하다)
- [Context가 단일 조건인 경우에는 Context 를 쓰지 않아도 괜찮다](https://github.com/rlawlstjd0077/TIL/blob/master/Spring/CodeSoom/2021-02-04.md#context가-단일-조건인-경우에는-context-를-쓰지-않아도-괜찮다)
- [AssertThrows 메서드를 사용하는 경우 예외 메시지를 명시해주어 이해를 도울 수 있다](https://github.com/rlawlstjd0077/TIL/blob/master/Spring/CodeSoom/2021-02-04.md#assertthrows-메서드를-사용하는-경우-예외-메시지를-명시해주어-이해를-도울-수-있다)
- [@Before, @After 실행 순서](https://github.com/rlawlstjd0077/TIL/blob/master/Spring/CodeSoom/2021-02-06.md#before-after-실행-순서)
- [Given-When-Then](https://github.com/rlawlstjd0077/TIL/blob/master/Spring/CodeSoom/2021-02-06.md#given-when-then)

------

## Future (계획, 행동)

- 조금더 움직이자. 움직인 만큼 얻는다. 