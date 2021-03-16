# 6week

## Facts (한 것)

- 코드숨 6주차 과제 진행 - 로그인 만들기
  - JWT
  - Interceptor

[ [week6\] 로그인 만들기 by rlawlstjd0077 · Pull Request #7 · CodeSoom/spring-week6-assignment-1목표 해야 할 일 로그인이 필요없는 API 로그인 - POST /session 회원 생성하기 - POST /userss 회원 수정하기 - POST /users/{id} 회원 삭제하기 - DELETE /users/{id} 고양이 장난감 목록 얻기 - GET /products 고양이 장github.com](https://github.com/CodeSoom/spring-week6-assignment-1/pull/7)

------

## Feelings (느낀점)

#### 얻은 것

- 새로운 기술을 도입하는 상황에서 해당 기술에 대해서 설명하는 글을 PR에 적어보았다!
  - 가장 먼저 느낀 것은 협업을 하는 상황에서 팀원에게 이런식의 설명은 반드시 필요할 것 같다는 생각이 들었다. 
  - 공유라는 행위의 중요성을 느꼈다.
  - 사실 '뭐 별거 있겠어' 라는 생각으로 적었는데 적고나니 이상하게 기분이 좋았다. 매우 건강하지는 느낌
  - 점점 과제를 할 수록 훌륭한 팀플레이어가 되는 것이 매우 중요하단 것을 깨닫고 그렇게 되고자 하는 목표가 생겼다!



![img](https://blog.kakaocdn.net/dn/d0Sl9E/btqZqpN3qjz/oebgKnYcVywtkoIkQNKeGk/img.png)



- JavaDoc에 대한 느낌적인 느낌
  - 솔직히 이전까지는 'JavaDoc'을 어떻게 좋게 작성해야 할지 감이 잘 오지가 않았다.
  - 그런데 피드백을 받고, 레퍼런스를 보고, 동기분들의 주석들도 보면서 점점 좋은 형태의 패턴이 뭔지를 알게 되는 것 같았다. 
    - 이것만으로도 충분히 고무적인 것 같다! 그러니 오히려 더 재밌어진다. 
    - 앞으로는 종립 트레이너님의 조언을 참고로 공식 JavaDoc을 계속 읽어보면서 나만의 패턴들을 정립해 나가봐야 겠다는 생각을 했다.



![img](https://blog.kakaocdn.net/dn/qbNTo/btqZqqlSp3I/paMuYxnOtqcbP6xoAu4mUk/img.png)

![img](https://blog.kakaocdn.net/dn/2rHqv/btqZvMPgjt0/pxd3RLjPV53eLg6cZKrLo0/img.png)



 

#### 아쉬운 점

- 100%를 쏟지 못한 것 같다 
  - 시간 때문에도 있겠지만 작업을 하면서 온전히 집중을 하지 못했다는 느낌이 강하다
  - 평소 다른 일정이 끼어 있는 날에도 언제언제 작업을 진행할지 정하지 않았는데 앞으로는 스케줄을 정해야 될 것 같다.
- 시도를 많이 해보지 못한 것 같다
  - 왜 이런 생각을 가지지 못했지? 라는 생각이 들었다.
  - '학습'이 목적이기에 여러가지 상황, 방법을 적용해보는 시도를 해보는 게 굉장히 중요할 것 같다.
  - 앞으로는 항상 생각하면서 가야겠다!



![img](https://blog.kakaocdn.net/dn/chnMrB/btqZtAIjBSk/NqAkMNDr1KsfN4wKkFwPxk/img.png)



------

## Finds (배운 것)

- [JWT란?](https://github.com/rlawlstjd0077/TIL/blob/master/Spring/CodeSoom/2021-03-02.md#jwt-json-web-token)
- Test
  - '기존' 이라는 단어의 문제 소지
  - Test Data가 많아지고 재사용이 가능하다면 Fixture 클래스로 분리를 고려하자
  - D-C-I Context 가 많다면 계층적으로 선언하는 것을 고려하자 



![img](https://blog.kakaocdn.net/dn/cJAnL1/btqZBdr1QuR/yGXfRi7Dnz8uniK4j9UGaK/img.png)



- JavaDoc
  - Method Summary 에는 리턴값을 중심으로 설명하면 표현력이 좋아진다.
  - '집합'과 '목록' 
- Interface를 적용한 DTO 변환 ([참고](https://github.com/CodeSoom/spring-week6-assignment-1/pull/14#issuecomment-790609410))
  - 시야를 굉장히 넓힐수 있을 만한 주제인 것 같다!
  - 앞으로 코드에서 활용해볼 수 있는 부분을 찾아보고 적용해보자!

 



![img](https://blog.kakaocdn.net/dn/4TMaH/btqZpV0LQgL/QvHBlXGLBLPtDrKDDtmEXk/img.png)



- DRY 원칙
- Lombok Test Coverage

 

------

## Future (계획, 행동)

- 최대한 시도를 많이 해보자.
  - 여러 상황, 방법들에 대해서 고려해보고 궁금하다면 시도해보자! 
- 회고때 적을 '배운 것'의 내용을 조금 더 자세하게 적을 수 있도록 미리미리 정리해두자!
  - PR에서 본 내용이라면 캡쳐를, 학습을 한 내용이라면 TIL에 정리하자.