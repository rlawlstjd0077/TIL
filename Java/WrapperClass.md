	# Wrapper Class



## 1. 목적

- 일반적으로 숫자를 다룰때 원시 타입을 많이 사용하지만 원시 타입 역시도 객체로 다뤄져야 하는 경우가 있다. 
  - 매개변수로 객체가 요구 될 때
  - 원시 타입이 아닌 객체로 저장해야 할 때
  - 객체간의 비교가 필요할 때



## 2. 형태

### 특징 

- 모든 Wrapper Class 들 (Integer, Long, Byte, Double, Float, Short) 은 모두 추상 클래스인 **Number Class** 의 자식 클래스 이다.
- Number Class 의 형태 ( Number 클래스는 java.lang 패키지의 일부이다. )

![Number Class](/img/NumberClass.jpg)

- 래퍼 클래스 객체는 각각의 원시 데이터 타입을 감싸거나 포함하고 있다.

- 원시 데이터 타입을 객체로 변환하는 과정을 **boxing** 이라고 한다. (반대는 **unboxing** 이겠죠?)

- 기본적으로 오버라이드 된 equls 메소드가 구현되어 있어서 객체의 주소가 아닌 원시 타입의 값을 비교할 수 있다.

  ​

### 사용법

- 원시 타입 -> Wrapper

```
public class WrapperExample1{  
	public static void main(String args[]){  
		//Converting int into Integer  
		int a=20;  
		Integer i=Integer.valueOf(a);//converting int into Integer  
		Integer j=a;//autoboxing, now compiler will write Integer.valueOf(a) internally  
  
		System.out.println(a+" "+i+" "+j);  
}}  
```



- Wrapper -> 원시 타입

	public class WrapperExample2{    
		public static void main(String args[]){    
			//Converting Integer to int    
			Integer a=new Integer(3);    
			int i=a.intValue();//converting Integer to int  
			int j=a;//unboxing, now compiler will write a.intValue() internally    
	    
			System.out.println(a+" "+i+" "+j);    
	}}  


### AutoBoxing, UnAutoBoxing

- JDK 1.5 버전 이후에는 자동으로 Boxing과 UnBoxing을 처리하도록 AutoBoxing과 AutoUnBoxing을 제공한다.
- 위의 코드를 참고하자.



