## 10. 새로운 MVC 프레임워크 구현을 통한 점진적 개선

- `newInstance()` 로 Controller 인스턴스를 실행시킨다는 게 신기하군. 
- `invoke` 메서드를 통해서 method를 실행시키고 있다. 
- 아 HandlerAdapter 는 다른 Controller 인터페이스를 통일 하기 위함이구나 .. ! 

### 실제 Dispatcher Servlet

- service 
- GenericServlet 

## 11. 의존관계 주입을 위한 테스트하기 쉬운 코드 만들기

### 의존성

- 의존관계는 객체 혼자 모든 일을 처리하기 힘들기 때문에 본인이 해야 할 작업을 다른 객체에게 위임하면서 발생한다. 
- 인스턴스를 생성하는 책임과 사용하는 책임을 분리하자는 것이 DI의 핵심이다. 



* `findConcreteClass` 확인해보자. 
* 

