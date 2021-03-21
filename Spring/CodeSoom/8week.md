# 8week

## Facts (한 것)

- 코드숨 8주차 과제 진행 - 문서화 및 배포하기

[
[week8\] 더 나아가기 by rlawlstjd0077 · Pull Request #7 · CodeSoom/spring-week8-assignment-1해야 할 일 배포 준비 Javadoc 작성하기 API 문서 만들기 Docker image 만들기 트레이너님 안녕하세요 :) 이번 한주도 잘 부탁드립니다. 🙏😁github.com](https://github.com/CodeSoom/spring-week8-assignment-1/pull/7)

#### Javadoc

- 이전 과제에서도 늘 작성해오던 javadoc이지만 매주 작성할 때마다 항상 다르다.
  - 어렵지만 어떻게 하면 조금 더 좋은 주석을 쓸 수 있을까 생각하게 되고 재밌다. 

![img](https://blog.kakaocdn.net/dn/bpGaPg/btq0JwRw8AW/elOs2iD7Y7DD74KWthEMHk/img.png)

#### Spring REST Doc

- Spring REST Doc을 이용해서 REST 문서를 작성해보았다.
  - 테스트는 MockMVC를 사용했는데 Test Case 와 매칭되어 REST Doc을 Generation 하는게 꽤나 메리트 있다고 느꼈다.
  - 물론 다른 방법을 시도해보진 않았지만 나중에 시도해보고 비교를 해봐도 좋은 기회가 될 것 같다 !

![img](https://blog.kakaocdn.net/dn/VPQjt/btq0A2c7yBF/FwMNnuYEicfeKLrOwYDVaK/img.png)

#### Docker Image 배포

- 만든 '고양이 장난감 가게 서버' 를 Docker Image로 패키징 하여 Docker Hub에 올려보았다!
  - 처음 접해보는 패러다임 이라서 굉장히 낯설었다.
  - 추후에 개인 프로젝트를 진행할 때 생길 수 있는 의존성들을 Docker로 해결해보면 정말 좋겠다는 생각이 들었다.

![img](https://blog.kakaocdn.net/dn/EPwqB/btq0HagVB73/bZzHXvAc1DLDcJpV7uVfJK/img.png)

------

## Feelings (느낀점)

#### **Git에 대한 고찰**

- 종립 트레이너님께도 들었지만 Git 히스토리를 꼼꼼하게 관리 한다는 것을 듣고 놀랐다.
  - 이제껏 사용해오던 Git이 참된 Git이 아니었구나 .. 싶었다.

![img](https://blog.kakaocdn.net/dn/bnk3Bz/btq0JxbRpKW/LFtOhNkkSbjdqcrxuAvDy0/img.png)

- 과제 진행중에 `git rebase -i` 를 사용해볼 기회가 있었는데 미숙한 탓에 엉뚱한 실수를 저질렀다.
  - 물론 .. `rebase` 에 익숙치 않았기에 귀중한 경험이었다고 생각했다.
  - 이번 일을 계기로 Git 에 대해서 많은 시도를 해보고 학습이 필요하겠다는 생각이 들었다.

![img](https://blog.kakaocdn.net/dn/7jViD/btq0DsoRe7F/c2f8bqCv6NK10XgLvkrJ41/img.png)

- 덤으로 git commit hook 이란 녀석도 알게 되었다.

#### **Burn out ..** 

- 이라기 보다는 무기력증이 갑자기 찾아 왔는데 매우 당황스러웠다.
- 원인을 찾고 싶었지만, 이내 나의 무지함을 깨닫곤 패닉 몬스터가 나타났다. 
  - "학습하기에도 모자란 시간에 뭐하고 있나" 라고 외쳤다.

![img](https://blog.kakaocdn.net/dn/B9fKi/btq0HaVx4Fq/5qce3CJaa5JR5yri1ApVY0/img.png)출처: https://www.youtube.com/watch?v=arj7oStGLkU

- 덕분에 방향키를 다시 잡을 수 있었다. (학습할 거리가 산 더미다 ... ) 

![img](https://blog.kakaocdn.net/dn/bvKoyx/btq0BE4bC1J/zgfkw19YkkpzGYFpakJNC0/img.png)출처: https://www.youtube.com/watch?v=arj7oStGLkU

#### **의식적인 학습**

> 가능한 한 뛰어난 스승에게 정말 제대로 전력을 다해서 배워야 한다. 이것을 의식적인 훈련이라고 한다.

- 라는 이야기를 책에서 보았는데 '현재 저렇게 하고 있는가? 라고 물어본 다면 '그렇지 않다'.
- 부끄러웠다. 나태해지지 말아야겠다.

------

## Finds (배운 것)

- [Spring REST Doc Snippet Customize 방법](https://github.com/CodeSoom/spring-week8-assignment-1/pull/7#issuecomment-802644221)
- Interface의 활용
  - Interface 사용을 체화시키자 .. 

![img](https://blog.kakaocdn.net/dn/bmy9Ss/btq0EECCc9j/0Z80p6gbbE437DYgV6qQsk/img.png)

- Javadoc Message '여부' 에 대한 문제 

![img](https://blog.kakaocdn.net/dn/n8GqB/btq0zY3zZpl/RjDJutnBz25VEbs4mgmjA1/img.png)

![img](https://blog.kakaocdn.net/dn/rxY9q/btq0AHmQTx4/KUFhaO6fTtQEDPvLkHMUMk/img.png)제 PR까지 와서 의견을 달아주신 'Las' 님도 감사합니다 :) 

- Commit Message '수정'에 대한 문제

![img](https://blog.kakaocdn.net/dn/ctGcL9/btq0ApzR3wx/zj0sH1Nv6qQKCBaJx5HkJk/img.png)

- [Spring REST Doc 웹 브라우저에서 HTML 문서로 제공하기 ](https://github.com/rlawlstjd0077/TIL/blob/master/Spring/CodeSoom/2021-03-19.md#html-문서로-serving-하기) 
- [Javadoc  태그 사용 이유](https://github.com/CodeSoom/spring-week8-assignment-1/pull/12#discussion_r596819545) 
- [Snippets 중복 제거 방법](https://github.com/rlawlstjd0077/TIL/blob/master/Spring/CodeSoom/2021-03-18.md#snippets-중복-제거-방법)
- [MarkDown 링크 삽입 팁](https://github.com/rlawlstjd0077/TIL/blob/master/Spring/CodeSoom/2021-03-15.md#markdown-link-팁)



------

## Future (계획, 행동)

- 앞으로 진행할 프로젝트 과제에서는 Git을 최대한 다방면으로 사용할 수 있도록 시도해보자.