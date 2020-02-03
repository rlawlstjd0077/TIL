# 섹션2. 스프링 IoC

## Inversion of Control

- 제어권이 역전된 것 (내가 생성하던 의존성을 외부에서 주니 이것을 역전 되었다고 이야기함)

  - 제어권의 차이

    - 일반적인 (의존성에 대한) 제어권: "내가 사용할 의존성은 내가 만든다."

      ```java
      class OwnwerController {
      	private OwnwerRepository repository = new OwnwerRepository();
      }
      ```

    - IoC: "내가 사용할 의존성을 누군가 알아서 주겠지"

      - 내가 사용할 의존성의 타입 (또는 인터페이스)만 맞으면 어떤 것이든 상관이 없음

      ```java
      class OwnwerController {
      	private OwnwerRepository repository;
      
      	public OwnwerController(OwnwerRepository repository) {
      		this.repository = repository;
      	}
      }
      ```

  - 의존성 주입 (dependency injection) 은 IoC 한가지 종류임

- Bean이란?

  - 스프링이 관리하는 객체를 Bean이라 부름
  - IoC 컨테이너가 Bean들의 의존성을 관리함

## IoC 컨테이너

- 빈 (bean)을 만들고 의존성을 엮어 주고 제공 해줌
  - 모든 객체가 Bean으로 등록되어 있지는 않음
  - Bean을 등록 하는 방법은 여러가지가 있음
- ApplicationContext (BeanFactory)
- 의존성 주입은 빈(bean) 끼리만 가능함
  - Ioc컨테이너 안에 있는 빈 들만 가능
- 그러나 컨테이너를 직접 쓸 일은 많지 않다.
- 싱글톤 Scope을 편하게 사용 할 수 있게 해줌

## 스프링 Bean

- 스프링 Ioc 컨테이너가 관리하는 객체
  - applicationContext가 만들어서 그 안에 담고 있는 객체
- 등록 하는 방법
  - Component Scanning
    - @Component
      - @Repository
        - JPA가 제공해주는 기능에 의해서 Bean으로 등록 (아주 복잡한 과정...)
      - @Service
      - @Controller
      - 등등, 정의할 수도 있음
  - Bean으로 직접 등록하는 방법
  - lifecycle callback?
- 꺼내서 사용하는 방법
  - @Autowired 사용
    - IoC 컨테이너에 들어 있는 빈을 주입 받아서 사용할 수 있음
  - ApplicationContext에서 getBean()으로 직접 꺼내서 사용

## 의존성 주입 (Dependency Injection)

- @Autowired
  - 의존관계 설정 방법 (빈을 주입 받는 방법)
  - 필드, setter, 생성자에서도 사용이 가능
    - 스프링 프레임워크 에서는 생성자를 권장 (필수적으로  사용해야 하는 레퍼런스를 없이는 인스턴스를 만들지 못하도록 강제할 수 있기 때문)
      - 단, 상호참조의 문제가 있는 경우 필드, setter 를 사용
  - 스프링 4.3 부터 어떠한 클래스의 생성자가 하나뿐이고 주입받는 파라미터가 빈으로 등록되어 있는 경우 빈을 자동으로 주입 해주도록 추가됨 (Autowired 생략이 가능)