## ETC

- Java의 발전 과정
  - Java5, Java8에서 큰 변화가 있었다.
  - Java는 멀티코어를 고려한 언어는 아니다.
- Thread에서 공유변수를 다루는 것이 제일 큰 난제
  - 무어 법칙(**대량 생산한 칩상의 트랜지스터 수는 약 18개월마다 2배씩 증가한다**)이 깨지면서 단일 코어로는 컴퓨터 성능 향상이 힘들어 멀티 코어 개념이 들어왔고
- 멀티코어를 다루기 위해서는 불변 자료형을 사용하는 함수형 프로그래밍이 훨씬 유리하기 때문에 각광을 받고 있는 추세
  - 자바에서도 적용을 하고 있지만은 한계가 있다.
  - 스칼라는 둘 모두를 지원하는 멀티 패러다임으로 등장을 하였고 JVM 에 대한 기존의 서드파티나 라이브러리 들을 활용하기 위해서 JVM 위에서 돌아가고 있다.
- Java는 객체 지향 4대 원칙을 지키며 확장을 했고 Spring은 DIP 하나를 가지고 많은 것을 하고 이를 편하게 할 수 있는 확장 기능이 있다.
- Java는 지역 변수는 초기화 해주지 않는다. 속성들은 자동 초기화 해줄뿐
- 필드 선언에는 Break Point는 걸리지 않는다.
- 모든 객체가 같은 값을 가지고 있다면 그 속성은 클래스 속성으로 만들어줄 수 있다. - static 영역으로 넣어주면 됨

## 기초

- 10 x 3 대신 10 * 3 ?
  - ASCII (Americal Standard Code ) - 7비트로 저렴하게 사용하려 했지만 그 이후에 이걸로는 안된다는 문제가 생김
- 멀티 코어를 받아들이기 위한 변화가 Java 1.8 부터 시작되었다.
  - 다만 객체지향을 함수형 언어로 가는데에는 한계가 있는 것은 분명함
  - 그렇기 때문에 함수형 언어가 주목받는 이유

## Java

### Call By **

- Call By Value 이다.
  - value란? 객체에 대한 포인터 값(레퍼런스) 또는 primitive 타입의 값
  - 매개변수로 레퍼런스가 전달되는 경우 지역변수에 할당이 되기 때문에 레퍼런스를 바꿔봐야 원래 인스턴스에는 영향이 없다.

### SOLID

자바(OOP 4대 특성을 구현한 제품) = 주방도구

- ex)무를 자를때 가위가 가능하긴 하지만 가위보다는 칼이 적합하듯

OOD( 객체지향 설계 ) 5원칙: 주방 도구에 대한 사용 설명서

- 개념에 대한 이해의 깊이는 다를 수 있음

- SRP(Single Responsibility Principle): 단일 책임 원칙

  - 어떤 소프트웨어 엔티티를 변경해야 하는 이유는 오직 하나뿐이어야 한다.
  - 예) 남자라는 클래스가 있고 남자는 남자친구, 아들, 직장상사, 소대장의 역할을 다 하고 있는 것
    - 여자친구가 사라진 경우 어머니한테도, 상사, 소대장한테 영향이 간다.
    - 그리하여 남자라는 클래가 아니라 남자친구, 아들, 사원, 소대원 클래스를 각각 나눠라
    - 남자친구만 상처를 입으면 된다.
  - **변화의 시기와 때와 이유가 다르다면 분리를 해내야 한다. -** 토비의 스프링 전체를 관통하는 중요한 개념
  - 단일 책임 원칙은 클래스 이외에 메소드, 속성도 포함해서 고려해야 한다.

- OCP - 개방 폐쇄 원칙

  - 소프트웨어 엔티티는 확장에 대해서는 개방 되어야지만 변경에 대해서는 폐쇄 되어야 한다.
    - "자신의 확장에는 개방되어 있고 주변의 변화에 대해서는 폐쇄돼 있어야 한다"
  - 운전자는 마티즈, 소나타를 운전한다는 것을 모른다. 단지 자동차를 가지고 타는 것 뿐이기 때문에 외부의 변화에 대해서는 영향을 받지 않는다.

  ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/740f39f0-1102-4b71-adf1-e001fa558d53/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/740f39f0-1102-4b71-adf1-e001fa558d53/Untitled.png)

  - JDBC 인터페이스

  ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/68862de5-d386-4368-ba8c-8f157a9cc987/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/68862de5-d386-4368-ba8c-8f157a9cc987/Untitled.png)

  - 실생황

  ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/703d1c7f-0e7c-48b5-9c92-ae672b931447/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/703d1c7f-0e7c-48b5-9c92-ae672b931447/Untitled.png)

- LSP - 리스코프 치환 원칙

  - 서브타입은 언제나 자신의 기반 타입으로 교체할 수 있어야 한다.
  - 객체지향에서 상속은 조직도, 계층도가 아닌 분류도가 되어야 한다.
    - 펭귄 = 동물, 딸≠ 할아버지
  - 주요 개념
    - 하위 클래스 is a kind of 상위 클래스 - 하위 분류는 상위 분류의 한 종류이다.
    - 구현 클래스 is able to 인터페이스 - 구현 분류는 인터페이스의 기능을 수행할 수 있다.
  - 상속이 적절하게 이루어졌다면 리스코프 치환 원칙을 만족하고 있다고 볼 수 있다.

- ISP - 인터페이스 분리 원칙

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

- 객체 지향과 SOLID

  - 관심사의 분리인 SoC(Seperation Of Concers) 가 핵심임
  - SoC를 적용하다 보면 SOLID에 도달하게 되고 스프링또한 Soc를 극한 까지 SOLID를 적용하고 있다.

- **"응집도는 높이고 결합도는 낮추라"는 고전적인 원칙에 기반해서 객체지향 관점에서 재정립 된 것**

Design Pattern: 레시피

## OOP

- 데이터를 객체로 취급하고 객체들의 상호작용을 통해서 프로그램이 동작하는 것
- 객체지향은 3개 (클래스 = 객체, 메소드, 속성) 으로만 이루어져 있다.
- 실세계의 일들을 객체를 사용해서 모델링 하는 것
- 기존의 문제점
  - 절차만을 가지는 프로그래밍으로 프로그램을 만들순 있으나 양이 커지면 관리하기가 어려워진다
  - 구조 프로그래밍을 통해서 함수로 잘라서 만들기 시작했으나 문제가 여전했다.
    - 구조를 나눴지만 구조 역시도 너무 많다.
  - 그럼 어떻게 묶을 것인가?에 대한 고민이 필요하다.
  - **즉, 어떤 문제가 있었고 새로운 방식이 필요해서 객체지향이 등장한 것**

### 캡슐화

- 데이터를 위주로 데이터와 함수를 캡슐이라는 개념으로 수납하는 방식
  - 구조화된 데이터를 사용하는 함수 모듈의 독립성을 침해하는 문제를 해결하기 위해서
  - 함수는 외부의 수정에 절대 영향을 받으면 안됨
- 데이터 구조에 따른 코드의 수정 범위를 캡슐 범위로 한정할 수 있다는 장점을 가짐
- 어떠한 객체가 특정한 목적을 달성하기 위해서 변수, 함수를 하나로 묶는 것
- 목적이 명확히 정해져 있는 클래스를 만들고 그에 맞는 변수, 함수들을 만듬

### 추상화

### 상속

### 다형성

## JVM

- JVM은 스택 기반의 해석 머신임 (JVM 표준 명세서)
- JDK는  JVM용 소프트 웨어 개발도구
- JRE는 JVM용 OS
- JVM은 가상의 컴퓨터

### 자바 프로그램 실행 과정 (추상적)

1. 텍스트 편집기로 작성된 소스 파일을 작성하여 JAVA 번역기 (JavaC.exe) 로 목적 파일(.class)을 만든다.
2. 각 운영체제에는 실행을 위한 JRE를 설치하고 JAVA 실행기 (Java.exe)를 통해서 목적 파일을 실행한다.

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/61eabd58-439a-4975-81a3-5af7774e8d50/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/61eabd58-439a-4975-81a3-5af7774e8d50/Untitled.png)

### Java 프로그램이 메모리를 사용하는 방식

- 프로그램이 메모리를 사용하는 방식

  - 옛날 부터 사용되던 방식

  ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a575e5d5-c8e0-41ed-8abd-9b6f68f3feae/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a575e5d5-c8e0-41ed-8abd-9b6f68f3feae/Untitled.png)

- 객체 지향 프로그램의 메모리 사용 방식

  - 코드 실행 영역은 개발자가 신경 쓰지 않아도 되고, 데이터 저장 영역을 크게 **세가지로 분리 한다는 것이 핵심**

  ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/79e0db86-bb93-4d00-af84-8ed40c87ee3f/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/79e0db86-bb93-4d00-af84-8ed40c87ee3f/Untitled.png)

  ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8b723e56-bf85-4f75-9112-1696deba19d7/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8b723e56-bf85-4f75-9112-1696deba19d7/Untitled.png)

### 절차적/구조적 VS 객체지향

- 그림에도 보이듯이 절차적/구조적 프로그래밍의 유산이 객체지향(Java) 언어에서도 남아있다.
- 새로운 기술은 이전의 기술을 토대로 발전이 되기 때문에

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5b7d23b5-3095-4415-9506-640c0dc80e9c/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5b7d23b5-3095-4415-9506-640c0dc80e9c/Untitled.png)

### 간단한 어플리케이션을 실행했을 때 일어나는 일

```java
public class Start {
	public static void main(String[] args) {
		System.out.println("Hello!");
	}//얘를 만나면 main() 스택 프레임에서 사라진다.
}
```

1. 모든 Java 프로그램이 구동될 때 항상 로딩되는 java.lang  패키지가 스태틱 영역에 로딩된다.
   - 이미지
2. 프로그램 내에서 사용되는 클래스, 패키지들을 전부 메모리에 업로드한다.
   - 이미지
3. main() 스택 프레임을 스택 영역에 올린다.
   - 이미지
4. main() 함수가 가지고 있는 args 변수 공간을 main 스택 프레임 안에 잡는다.
   - Class를 제외한 나머지 문법(메소드, if, for 등등)에서 중괄호 블럭이 시작되는 순간 스택 프레임에 올라가고 종료되었을 때 사라짐
   - 이미지
5. 그 다음 System.out.println 이 실행되는데 데이터 영역에는 변화가 없고 코드 실행 영역에서 실행되면서 모니터에 String이 찍힌다.
   - 이미지

### 간단한 어플리케이션을 실행했을 때 일어나는 일2

```java
public class Start2 {
	public static void main(String[] args) {
		int i;
		i = 10;
		double d = 20.0;
	}
}
```

1. (이전의 과정은 위와 동일) 스택 프레임에 i 라는 변수 공간이 생김
   - 이미지
2. i에 10이라는 값이 할당된다.
   - 이미지

### 간단한 어플리케이션을 실행했을 때 일어나는 일3

```java
public class Start3 {
	public static void main(String[] args) {
		int i = 10;
		int k = 20;
		
		if (i == 10) {
			int m = k + 5;
			k = m;
		} else {
			int p = k + 10;
			k = p;
		}
	}
}
```

1. (이전의 과정은 위와 동일) 스택 프레임에 i , k 변수 공간이 생김
   - 이미지
2. if 문을 만나고 i == 10 이 true이기 때문에 if(true) 스택 프레임이 생긴다.
3. if(true) 스택 프레임에 m 변수 공간이 생기고 값이 설정된다.
   - 이미지

### 간단한 어플리케이션을 실행했을 때 일어나는 일4

```java
public class Start4 {
	public static void main(String[] args) {
		int k = 5;
		int m;

		m = square(k);
	}

	private static int squre(int k) {
		int result;
		k = 25;
		result = k;
		return result;
	}
}
```

1. (이전의 과정은 위와 동일) main 스택 프레임에 m, k 변수 공간이 생기고 k는 바로 할당
   - 이미지
2. square 함수가 호출되면 static 이기 때문에 square 스택 프레임이 별도로 생성되고 인자인 k가 5로 할당됨
   - 그리고 void가 아닌 모든 메소드는 자동적으로 반환값을 저장하는 공간을 미리 만듬
   - 이미지
3. 이후에 쭉 실행됨

### 간단한 어플리케이션을 실행했을 때 일어나는 일5

```java
public class Start5 {
	static int share;
	
	public static void main(String args[]) {
		share = 55;
		int k = fun(5, 7);
		System.out.println(share);
	}

	private static int fun(int m, int p) {
		share = m + p;
		return m - p;
	}
}
```

1. 정적(클래스) 멤버인 전역 변수 share가 스태틱 영역에 올라간다.

   - 이미지

     ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d781b459-2723-4304-83a0-caedcab7281d/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d781b459-2723-4304-83a0-caedcab7281d/Untitled.png)

2. ㅁㄴㅇㅁㄴ

   - 이미지

### 간단한 어플리케이션을 실행했을 때 일어나는 일6

```java
public class Start6 extends Thread {
	static int share;
	
	public static void main(String args[]) {
		Start6 t1 = new Start6();
		Start6 t2 = new Start6();
		
		t1.start();
		t2.start();
	}

	public void run() {
		for(int count = 0; count < 10; count++) {
			System.out.println(share++);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
			}
	}
}
```

### 자바 프로그램 실행 과정

1. 프로그램이 실행되면 JVM은 OS로부터 프로그램이 필요로 하는 메모리를 할당받는다.
   1. 할당 받은 메모리는 JVM 내부에서 여러 영역으로 나누어 관리함
      1. 그렇기 때문에 메모리를 할당한 후에 OS 시스템 콜을 하는 일이 없어짐
2. 자바 컴파일러 (javac)가 자바 소스코드 (.java)를 읽어들여 자바 바이트코드 (.class)로 변환시킨다.
   1. 자바 바이트코드는 특정 컴퓨터 아키텍쳐에 종속적이지 않은 **중간표현형**으로 형태임
   2. 그렇기 때문에 어떤 JVM지원 플랫폼 어디서나 실행할 수 있음
3. JVM 내부의 클래스로더를 통해서 class 파일들을 로딩한다.
   1. 클래스 로더에서는 제일 처음 로딩(.class 파일을 읽고 내용에 따라 적절한 바이너리 데이터를 "메소드" 영역에 저장) 을 하는데 로딩을 끝난 후에는 클래스 타입의 Class 객체를 생성하여 "힙" 영역에 저장함
   2. 링크 (레퍼런스를 연결하는 과정)
   3. 초기화 (Statis 변수의 값을 할당)
4. 로딩된 class 파일은 실행엔진을 통해서 해석된다.
   1. 인터프리터를 통해서 바이트 코드를 한줄한줄 읽으며 네이티브 코드로 바꿔서 기계가 이해할 수 있게끔 하여 실행함
   2. 이때 JIT 컴파일러를 통해서 미리 중복되는 반복 코드들을 네이티브 코드로 변환하여 인터프리터가 바로 사용하여 성능을 향상 시킴
5. 해석된 바이트 코드는 Runtime Data Areas에 배치되어 실질적인 수행이 이루어지게 된다.
6. 이후 JVM은 프로그램이 실행되면서 필요에 따라 Threa Synchronization과 GC 같은 관리작업을 수행한다.

### 메모리 구조

- Method Area
  - static 변수, 코드에서 사용되는 .Class 정보들이 저장되는 곳
  - 클래스로더로 읽어들인 데이터들이 저장되는 곳
- 스택 영역 (LIFO)
  - 임시로 사용되는 지역변수, 매개변수들이 저장되는 공간
  - **참조변수의 레퍼런스는 스택 영역에 저장이 되고 그 레퍼런스의 실제 메모리는 모두 힙 영역에 저장된다.**
- 힙 영역
  - new 연산을 통해서 메모리에 할당된 객체들이 저장되며 힙영역은 가비지 컬렉션 대상임
  - 힙 영역에 저장되는 객체들은 언제 어떻게 사용될지 모르는 불확실성을 가지기에 힙 영역에 저장해 놓고 이를 필요한 스택 영역에서 참조하여 사용하게 된다.
  - 객체들을 참조하는 곳이 사라지면 (null이 되는 등) 가비지 대상이 되어 회수 되어 버림

## Garbage Collection

- 자바는 가비지 수집이라는 프로세스를 이용해서 힙 메모리를 자동 관리하는 방식으로 해결한다.
- JVM이 더 많은 메모리를 할당해야 할 때 불필요한 메모리를 회수하거나 재사용하는 불확정적 프로세스이다.
- 시스템에 있는 모든 객체의 수명을 정확히 몰라도 런타임이 대신ㄴ 객체를 추적하며 쓸모 없는 객체를 알아서 제거하는 것
  - 알고리즘은 반드시 모든 가비지를 수집해야 된다.
  - 살아 있는 객체는 절대 수집하며 안된다.

### 마크 앤 스위프

- 가비지 수집의 가장 기초적인 알고리즘으로 할당되었지만 아직 회수되지 않은 객체를 가리키는 포인터를 포함한 할당 리스트를 사용함
  1. 할당 리스트를 순회하면서 마크비트를 지운다.
  2. GC 루트부터 살아 있는 객체를 찾는다.
  3. 이렇게 찾은 객체마다 마크 비트를 세팅한다.
  4. 할당 리스트를 순회하면서 마크 비트가 세팅

### 멀티 스레드 / 멀티 프로세스의 이해

- 멀티 스레드의 메모리 모델은 스택 영역을 스레드 개수만큼 분할해서 쓰는 것이다.

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9975556c-25a0-4699-9ade-4fd934af406b/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9975556c-25a0-4699-9ade-4fd934af406b/Untitled.png)

- 멀티 프로세스는 다수의 데이터 저장 영역, 즉 다수의 메모리를 갖는 구조다.
  - 예) Servlet은 요청당 스레드 한개, CGI는 요청당 프로세스 한개의 동작

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/179c8a5a-85fa-424f-92b7-a18b54077afa/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/179c8a5a-85fa-424f-92b7-a18b54077afa/Untitled.png)

# 자바와 객체지향

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

- 상속 (

  Inheritance

  ) - 다만 단어에 오해의 소지가 있음

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

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/fe681479-67bd-4bda-9be9-19aacc2ff63d/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/fe681479-67bd-4bda-9be9-19aacc2ff63d/Untitled.png)

## 자바와 객체지향 - 추상화

### 추상화의 대가는

### 추상화란

- Abstraction
- 추상화란 구체적인 것을 분해해서 관심 영역에 대한 특성만을 가지고 재조합 하는 것

### 모델링

- DNA 모델을 실제 DNA와 동일한 모양으로 만들 수 있는가?
- 태양계 모델
- 사실 그대로 옮길 수도 없고, 그럴 필요도 없다. 시물레이션일 뿐
- 구체적인 것을 분해해서 관심 영역(애플리케이션 경계, Application Boundary)에 있는 특성만을 가지고 재조합하는 것

### 추상화의 안경을 쓰고 객체들을 통해서 클래스를 모델링

- 사람자체를 표현하긴 힘들다. 필요한 속성만을 가지고 표현할 뿐

### 어플리케이션 경계/도메인/Context에 따라 달라지는 모델링

- 코딩은 기술이지미나 모델링은 예술이다.

### 객체를 생성하는 어플리케이션을 실행했을 때 일어나는 일6

```java
public class MouseDriver {
	public static void main(String[] args) {
		Mouse mickey = new Mouse();
		
		mickey.name = "미키";
		mickey.age = 85;
		mickey.countOfTail = 1;
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

5. ```
   .
   ```

   은 참조 연산자로 객체를 찾아간다. 찾아가서 name, age, 등 속성을 찾아서 값을 넣는다.

   - String도 객체지만 편의상. .

6. 

### 추상화와 T 메모리

- 추상화는 Java에서 `class` 키워드로 표현한다.

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/04a66d66-46b2-4bb8-b23c-ea997f126924/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/04a66d66-46b2-4bb8-b23c-ea997f126924/Untitled.png)

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/983348ef-ae89-4ceb-8c5b-668aac930ce3/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/983348ef-ae89-4ceb-8c5b-668aac930ce3/Untitled.png)