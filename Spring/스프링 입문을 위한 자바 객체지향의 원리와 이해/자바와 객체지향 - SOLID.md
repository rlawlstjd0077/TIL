# 자바와 객체지향 - SOLID

출처: 스프링 입문을 위한 자바 객체 지향의 원리와 이해(김종민)

## Java, DP, OOD 비유

- 자바
  - OOP 4대 특성을 구현한 제품 = 주방도구
  - ex)무를 자를때 가위가 가능하긴 하지만 가위보다는 칼이 적합하듯
- Design Pattern
  - 자바를 통해서 만들 수 있는 = 레시피
- OOD(객체지향 설계) 5원칙
  - 주방 도구에 대한 사용 설명서
  - 개념에 대한 이해의 깊이는 다를 수 있음

## SOLID 5원칙

### SRP(Single Responsibility Principle): 단일 책임 원칙

- 어떤 소프트웨어 엔티티를 변경해야 하는 이유는 오직 하나뿐이어야 한다.
- 예) 남자라는 클래스가 있고 남자는 남자친구, 아들, 직장상사, 소대장의 역할을 다 하고 있는 것
  - 여자친구가 사라진 경우 어머니한테도, 상사, 소대장한테 영향이 간다.
  - 그리하여 남자라는 클래가 아니라 남자친구, 아들, 사원, 소대원 클래스를 각각 나눠라
  - 남자친구만 상처를 입으면 된다.
- **변화의 시기와 때와 이유가 다르다면 분리를 해내야 한다. -** 토비의 스프링 전체를 관통하는 중요한 개념
- 단일 책임 원칙은 클래스 이외에 메소드, 속성도 포함해서 고려해야 한다.

### OCP - 개방 폐쇄 원칙

- 소프트웨어 엔티티는 확장에 대해서는 개방 되어야지만 변경에 대해서는 폐쇄 되어야 한다.
  - "자신의 확장에는 개방되어 있고 주변의 변화에 대해서는 폐쇄돼 있어야 한다"
- 운전자는 마티즈, 소나타를 운전한다는 것을 모른다. 단지 자동차를 가지고 타는 것 뿐이기 때문에 외부의 변화에 대해서는 영향을 받지 않는다.

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/740f39f0-1102-4b71-adf1-e001fa558d53/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/740f39f0-1102-4b71-adf1-e001fa558d53/Untitled.png)

- JDBC 인터페이스

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/68862de5-d386-4368-ba8c-8f157a9cc987/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/68862de5-d386-4368-ba8c-8f157a9cc987/Untitled.png)

- 실생활

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/703d1c7f-0e7c-48b5-9c92-ae672b931447/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/703d1c7f-0e7c-48b5-9c92-ae672b931447/Untitled.png)

### LSP - 리스코프 치환 원칙

- 서브타입은 언제나 자신의 기반 타입으로 교체할 수 있어야 한다.
- 객체지향에서 상속은 조직도, 계층도가 아닌 분류도가 되어야 한다.
  - 펭귄 = 동물, 딸≠ 할아버지
- 주요 개념
  - 하위 클래스 is a kind of 상위 클래스 - 하위 분류는 상위 분류의 한 종류이다.
  - 구현 클래스 is able to 인터페이스 - 구현 분류는 인터페이스의 기능을 수행할 수 있다.
- 상속이 적절하게 이루어졌다면 리스코프 치환 원칙을 만족하고 있다고 볼 수 있다.

### ISP - 인터페이스 분리 원칙

- 클라이언트는 자신이 사용하지 않는 메소드 (소프트웨어 엔티티)에 의존 관계를 맺으면 안된다.ㅓ
- 남자라는 클래스를 사용할 때 각각의 사용처에서는 인터페이스를 만들어서 접근을 해야만 한다는 것.

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/eb8d95be-ce06-45b0-a305-d1fe2abddbaf/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/eb8d95be-ce06-45b0-a305-d1fe2abddbaf/Untitled.png)

- DIP - 의존 역전 원칙 (이것만 이해해도 스프링 절반은 정복한 것이나 마찬가지다..)

  - 로버트 마틴의 한마디들

    - 고차원 모듈은 저차원 모듈에 의존하면 안된다. 이 두 모듈 모두 다른 추상화된 것에 의존해야 한다.
    - 추상화된 것은 구체적인 것에 의존하면 안된다. 구체적인 것이 추상화된 것에 의존해야 한다.
    - 자주 변경되는 구체(Concret)에 의존하지 말라

  - DI는 DIP 를 적용한 구현방법중 하나임

  - 의존 역전 원칙을 의역하면 **"자신보다 변하기 쉬운 것에 의존하지 마라"**임

    - 상위 클래스 일수록 인터페이스, 추상클래스 일수록 변하지 않을 가능성이 크기 때문에 하위가 아닌 이들을 통해서 의존하라는 것이 의존 역전 원칙임

    ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d27215c9-3b8f-4872-9534-29544e5ed423/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d27215c9-3b8f-4872-9534-29544e5ed423/Untitled.png)

### 객체 지향과 SOLID

- 관심사의 분리인 SoC(Seperation Of Concers) 가 핵심임
- SoC를 적용하다 보면 SOLID에 도달하게 되고 스프링또한 Soc를 극한 까지 SOLID를 적용하고 있다.
- **"응집도는 높이고 결합도는 낮추라"는 고전적인 원칙에 기반해서 객체지향 관점에서 재정립 된 것**