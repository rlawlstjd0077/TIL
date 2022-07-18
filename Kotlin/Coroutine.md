# 코루틴


- Thread 에 비해서 가볍다. Thread 단위로 동시성 프로그래밍을 했을 때의 단점 (Context Switching 비용, 메모리 비용) 을 보완한다. 
	- 코루틴이라고 하는 루틴들이 스레드 내에서 동시성 프로그래밍을 할 수 있기 때문임.
	- 물론 다른 스레드에서 
- Kotlin 의 코루틴은 suspend 키워드로 마킹된 함수를 CPS로 변환하고 이를 Coroutine Builder 를 통해서 적절한 스레드 상에서 시나리오에 따라 동작하도록 구성됩니다. 
	 - 코루틴은 중단 지점까지 비선점형으로 동작하기 때문에 실행 스케쥴이 OS에 의해 온전히 제어되는 스레드와는 다른 관점에서 보아야 합니다.

## Coroutine Scope 
- runBlocking
	- blocking 스레드를 Block 함
- coroutineScope
	- suspend 함수로 async 용도로 사용함
- 특정 스코프에서 

- Thread 의 C.S 비용은 얼마나 될까? 
	- thread switching 중에는 가상 메모리 공간이 동일하게 남고 반면 process switch의 경우에는 그러지 못합니다
	- 두 타입 모두 컨텍스트 스위칭 수행을 운영체제 커널에게 제어권을 넘겨줍니다. 컨텍스트 스위칭을 하는 과정에서 os kernel을 들어갔다 나오는 것과 레지스터를 전환하는 비용은 가장 큰 고정 값입니다.
	- 결론: 커널을 갔다와야 하기 때문에 비용이 발생함 
- Thread 내부에서 실행되는 것으로 보이는데 코루틴 단위로 여러 Core 에서 골고루 실행 되지는 않을 것 같은데 ?

- Web Flux 에서 사용하는 Request 처리 Thread 와 Global Scope 의 스레드는 다른 스레드 인가 ?

- Coroutine 에서 어떻게 스레드를 배분배서 지정할 수 있는지? data analytics 쪽에서 coroutine 은 어떻게 쓰고 있는지? metric 에서는 ? 
- Coroutine 사용시에 Thread Pool 관리는 어떻게 하는지 ? 