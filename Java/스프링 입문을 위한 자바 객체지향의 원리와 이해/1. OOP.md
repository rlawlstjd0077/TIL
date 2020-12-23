## 자바와 객체지향

------

### 객체

- 큰 그림
  - 세상에 존재하는 모든 것은 사물, 즉 Object
  - 각각의 사물은 고유하다. (유일 무이)
    - 사물은 속성을 가짐
    - 사물은 행위를 함 (무생물은 의인화가 필요)
- 객체들은 분류(Class)를 가진다.
  - 직립보행을 하며 말을 하는 존재들을 (사람) 이라고 분류

### 객체지향의 4대 특성 (캡! 상추다)

- 캡슐화 (Encapsulation)
  - 정보 은닉
- 상속 (Inheritance) - 다만 단어에 오해의 소지가 있음
  - 재사용 (ReUse) + 확장
  - 상속 보다는 확장으로 쓰는게 의미로써는 더 비슷함
- 추상화(Abstraction)
  - 모델링
- 다형성 (Polymophism)
  - 사용을 편리하게 해주는 것
  - 오버로딩, 오버라이딩

### 클래스 VS 객체 = 붕어빵틀 VS 붕어빵

- 클래스와 객체 사이의 필요충분조건 - `클래스 객체참조변수명 = new 클래스()`

- ```
  붕어빵틀 붕어빵 = new 붕어빵틀()
  ```

   \- 클래스가 객체의 설계도 라는 잘못된 개념에서 비래(장님이 코끼리는 만지는 격)

  - 붕어빵틀을 하나 제작하여 붕어빵틀 역할을 하는 붕어빵이라 이름 지었다?
  - 붕어빵틀은 붕어빵의 클래스가 아닌 붕어빵의 팩토리 (제작소) 였던 것임

- ```
  펭귄 뽀로로 = new 펭귄()
  ```

  - 펭귄이 하나 태어나 펭귄 역할을 하는 뽀로로라 이름지었다.

- 소스를 작성할 때는 기계가 아니라 사람이 읽을 수 있게 만드는 것이다.

## 자바와 객체지향 - 추상화 (=모델링)

------

### 추상화란

- Abstraction
- 추상화란 구체적인 것을 분해해서 관심 영역에 대한 특성만을 가지고 재조합 하는 것

### 모델링

- DNA 모델을 실제 DNA와 동일한 모양으로 만들 수 있는가?
  - 예) 태양계 모델
- 사실 그대로 옮길 수도 없고, 그럴 필요도 없다. 시물레이션일 뿐
- 구체적인 것을 분해해서 관심 영역(애플리케이션 경계, Application Boundary)에 있는 특성만을 가지고 재조합하는 것

### 추상화의 안경을 쓰고 객체들을 통해서 클래스를 모델링

- 사람자체를 표현하긴 힘들다. 필요한 속성만을 가지고 표현할 뿐

### 객체를 생성하는 어플리케이션을 실행했을 때 일어나는 일

```java
public class MouseDriver {
	public static void main(String[] args) {
		Mouse mickey = new Mouse();
		
		mickey.name = "미키";
		mickey.age = 85;
		mickey.countOfTail = 1;
    }
}
```

1. main 메소드가 있음을 JRE 에서 아는 순간 MouseDriver의 main을 구동하기 위해서 사용하는 java.lang, Mouse Driver, Mouse 클래스를 스태틱 영역에 올린다.
   - 이미지
2. mickey 라는 참조변수를 만든다.
   - 이미지
3. 값을 가질 수 있는 객체 (클래스 인스턴스) 를 힙 영역에 생성된다.
   - 참고로 생성된 객체는 UML 표기법으로 콜론을 붙여서 표현함
   - 이미지
4. Mouse 객체는 메모리에 존재하기 때문에 micky 라는 참조변수에는 100 이라는 주솟값이 할당되는 것이다.
   - 이미지
5. `.`은 참조 연산자로 객체를 찾아간다. 찾아가서 name, age, 등 속성을 찾아서 값을 넣는다.
   - String도 객체지만 편의상. .

### 정적 멤버

- 정적 속성은 해당 클래스의 모든 객체가 같은 값을 가질 때 사용하는 것이 기본적이다.
  - 정당한 논리를 가지고 써야 함
  - 예) 사람 클래스의 인구, 고양이 클래스의 다리 개수, 승용차 클래스의 바퀴 개수

### 알면 좋은

- 객체의 동의어
  - 인스턴스
  - 클래스의 인스턴스
  - Object
- 정적 멤버 동의어
  - 클래스멤버 (Static 멤버)
- 객체 멤버 동의어
  - 인스턴스 멤버
- 함수와 메서드의 차이

### 추상화와 T 메모리

- 추상화는 Java에서 `class` 키워드로 표현한다.

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/04a66d66-46b2-4bb8-b23c-ea997f126924/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/04a66d66-46b2-4bb8-b23c-ea997f126924/Untitled.png)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/983348ef-ae89-4ceb-8c5b-668aac930ce3/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/983348ef-ae89-4ceb-8c5b-668aac930ce3/Untitled.png)

## 자바와 객체지향 - 상속

------

- 상위 클래스는 풍성할수록 좋은가? 빈약할수록 좋은가?
  - 재사용할 수 있으면 있을 수록 좋기 때문에 풍성할 수록 좋음
- 인터페이스는 풍성할수록 좋은가? 빈약할수록 좋은가?
  - 최소화된 단위로 빈약할수록 좋음

### 상속? inheritance?

- Java에서 말하는 상속은 계층이나 조직이 아니다.

### 상속이 아니라 확장

- 자바 키워드에는 interitance가 없다.
  - 제임스 고슬링은 알고 있었음
  - Java에서 상속을 위한 키워드는 `extend`이다.
- 확장을 통해서 함수나 속성을 재사용할 수 있다는 것이 강력한 장점

### 상속의 올바른 예시도

- 분류도

  - 고래는 포유류를 확장하는 개념이다.

    ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/bde20e98-88ee-4591-ac7a-689c8324cede/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/bde20e98-88ee-4591-ac7a-689c8324cede/Untitled.png)

    ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e2fdcfc2-c28c-4784-babf-7c5c6e97b94d/Screen_Shot_2020-12-21_at_3.26.11_PM.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e2fdcfc2-c28c-4784-babf-7c5c6e97b94d/Screen_Shot_2020-12-21_at_3.26.11_PM.png)

### 상속 관계

- "하위 클래스는 상위 클래스다" 를 만족해야 한다.
- 이것이 바로 LSP(리스코프 치환 원칙) 이다.

### 객체지향 상속의 힘

- 하위 클래스의 객체를 만들어서 상위 클래스에 역할을 맡길수 있다는 것
  - 프로그래머의 삶을 윤택하게 만들어주는 강력한 기능중 하나

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3b093d2c-d2cf-491d-9352-5a6d02d91a8b/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3b093d2c-d2cf-491d-9352-5a6d02d91a8b/Untitled.png)

### 다중 상속

- Java에서 다중상속을 지원하지 않는 이유는 다이아몬드 상속 때문임
- 그러나 interface를 이용하면 다중 상속이 가능하다.   객체를 생성하는 어플리케이션을 실행했을 때 일어나는 일6

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/143f5418-5526-4bae-ba5b-3ba300b0e6fe/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/143f5418-5526-4bae-ba5b-3ba300b0e6fe/Untitled.png)

### 상속을 사용하는 어플리케이션을 실행했을 때 일어나는 일

```java
public class Driver {
	public static void main(String[] args) {
		Penguin pororo = new Penguin();
		
		pororo.name = "뽀로로";
		pororo.habitat = "남극";
		
		pororo.showName();
		pororo.showHabitat();

		Animal pingu = new Penguin();
		pingu.showName();
```

1. new Penguin(); 시에 펭귄 객체와 상위 클래스인 Animal 객체가 함께 생성이 된다.
   - 물론 Animal이 상속하고 있는 Object 객체도 실제로는 만들어진다.
   - 이미지
2. pororo 는 객체 참조 변수 레퍼런스 변수

### 상속은 is a 관계를 만족해야 한다?

- 포유류 is a 동물 (50점)
  - a 는 하나의 동물이며 이때는 객체가 된다.
  - 포유류는 객체가 아니라 분류이다.
- 포유류 is a kind of 동물 (100점)
  - 분류 is a 분류 (X) 위와 같은 설명임
  - 객체 is a 분류 (O)
  - 하위 분유 is a kind of 상위 분류 (OK)

### 정리

- 상속
  - extends (확장) 이라는 의미를 가진다.
  - 재사용의 목적을 가지고 있다.
- 인터페이스
  - implements 구현의 목적을 가진다.
  - 구현강제, 표준 준수의 목적을 가진다.
  - JPA 라는 표준 스펙을 쓰면 하위 구현체를 어떤 것을 쓰든 문제가 없듯이

## 자바와 객체지향 - 캡슐화

------

- 정보은닉을 위함이며 정보은닉의 목적은 변화에 유연하게 대처하기 위함이다.

### 변화에 대처

- 값만 오픈 했을 때 추후에 생기는 여러가지 변경사항에 대응하기가 어렵다.
- 예) 주민등록번호 get/set 없이 보여주는 경우
  1. 주민등록번호 뒤 7자리는 * 으로 보이게
     - get 메소드를 변환해주면 구현 가능
  2. 본인이 접근한 경우에는 다 보이게
     - if 문 추가
  3. **프로그램이 폐기되기 전까지는 변화에 유연한 대처**

## 자바와 객체지향 - 다형성

------

### 다양한 적용 범위

- 상속에서의 다형성
- 인터페이스와 그 구현체의 다형성

### 4대 특성에서의 다형성

- 오버라이딩 (재정의)

- 오버로딩 (

  - 다형성인지도 이견이 있음

- 라이더가 될 것인가? 로더가 될 것인가?

  - ride: 올라타다.
  - load: 적재하다.

  ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/eaa39508-d3dd-4504-a9e0-21ac0efc1c0a/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/eaa39508-d3dd-4504-a9e0-21ac0efc1c0a/Untitled.png)

  라이딩은 위에서 보이지 않기 때문에 재정의 로딩은 중복 정의

### 다형성과 T 메모리

- 오버로딩은 중복인 이름으로 하위 클래스에 새로운 메서드가 생긴다.
- 오버라이딩의 경우는 재정의된 메서드가 상위 하위에 걸쳐서 생성이 된것이기 때문에 Animal Type의 참조변수를 선언해Penguin 에서 재정의된 메서드가 실행되게 된다.

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1e7b8eba-f36c-4de8-b038-a0bb47442b80/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1e7b8eba-f36c-4de8-b038-a0bb47442b80/Untitled.png)

### 다형성의 목적

- 오버로딩이 없다고 생각하면 아주 끔찍
- 코딩의 편의
  - 오버로딩 - 하나의 메소드명으로 다ㅏ양한 인자를 취하는 다수의 메소드 구현이 가능하다.
    - 메소드 오버로딩은 컴파일러가 이름을 구분해주기 때문에 객체지향과 별 상관이 없다.
  - 오버라이딩 - 하위 클래스가 상위 클래스의 기능을 재정의(메소드 재정의) 하는 것
    - 상속과 연관이 있음