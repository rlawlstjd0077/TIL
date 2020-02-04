# 섹션3. 스프링 AOP

## AOP (Aspect Oriented Programming)

- 흩어진 코드들을 한 곳에 모으는 것
  - 흩어진 코드들 (흩어져 있는 중복된 코드들)
    - 뭔가를 수정해야 할 때 흩어져 있는 전부를 수정해야 하는 문제가 있음
  - 모아 놓은 코드들
    - 메소드에서 원래 해야하는 기능만 남겨두고 중복된 코드들은 따로 모아놓음

## 다양한 AOP 구현 방법

- 컴파일 시 (AspectJ)
  - [A.java](http://a.java)  ——- (AOP) ——→ A.class
- 클래스로더에 옵션을 붙여 바이트 코드를 조작 (AspectJ)
  - [A.java](http://a.java) → A.class ——- (AOP) —> 메모리
- 프록시 패턴 (스프링 AOP)
  - 디자인패턴중 하나를 사용해서 AOP와 같은 효과를 냄
    - https://refactoring.guru/design-patterns/proxy
  - 기존의 코드를 건드리지 않고 객체를 다른 객체로 바꾸는 방법

## 프록시 패턴

- AOP를 프록시 패턴으로 구현하는 방법

  - 아래의 그림을 참고
    - 클라이언트 (Payment를 사용하는 곳)에서는 아무 수정 없이 CreditCard로 변경만 해주면 원래에 기능을 유지한 채로 추가된 기능을 사용할 수 있음

  ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b9915dc6-e833-4f90-a005-c4dc4286c06b/Untitled.png](https://refactoring.guru/images/patterns/diagrams/proxy/live-example.png)

- 위 그림을 구현한 코드 참고

  - **"섹션3_예제코드" 폴더 참고**

- 스프링에서는 위 과정이 내부 매커니즘을 통해서 자동으로 이루어짐

  - 프록시가 자동으로 빈이 등록될 때 만들어 진다고 생각하면 될 듯
  - @Transactional 이 프록시 패턴으로 이루어짐
    - 내부의 매커니즘을 통해서 앞 뒤로 Transactional 기능을 수행하게 해줌
    - 비즈니스 로직에만 집중 할 수 있도록 숨겨놓음

## AOP 적용 예제

- **@LogExecutionTime 으로 메소드 처리 시간 로깅하기**

- @LogExecutionTime 애노테이션 (어디에 적용할지 표시 해두는 용도)

  ```java
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  public @interface LogExecutionTime {
  ]
  ```

- 실제 Aspect (@LogExecutionTime 애노테이션 달린곳에 적용)

  ```java
  @Component
  @Aspect
  public class LogAspect {
  	Logger logger = LoggerFactory.getLogger(LogAspect.class);
  
  	@Around("@annotation(LogExecutionTime)")
  	//ProceedingJoinPoint는 애노테이션이 붙어 있는 타겟 메소드를 의미
  	public Object logExecutionTime(ProceedingJoinPoint joinPoint) {
  		StopWatch stopWatch = new StopWatch();
  		stopWatch.start();
  
  		//타겟 메소드를 실행
  		Object proceed = joinPoint.proceed();
  		
  		stopWatch.stop();
  		logger.info(stopWatch.pretyPrint());
  		return proceed;
  	}
  }
  ```

  