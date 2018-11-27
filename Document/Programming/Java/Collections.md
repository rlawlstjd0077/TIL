# 컬렉션 프레임웍 (Collection Framework)
##  컬렉션 프레임웍의 핵심 인터페이스

- 컬렉션 프레임웍에서는 컬렉션을 크게 3가지 타입이 존재한다고 인식하고 각 컬렉션을 다루는데 필요한 기능을 가진 3개의 인터페이스를 정의하였다. 
- 인터페이스 List 와 Set의 공통된 부분을 다시 뽑아서 새로운 인터페이스인 Collection을 추가로 정의 하였다.  

##  Vector 클래스 보다는 ArrayList를 되도록이면 사용해야 하는 이유

- Vector 와 HashTable과 같은 컬렉션은 동기화를 자체적으로 동기화 처리가 되어 있는데, 멀티쓰레드 프로그래밍이 아닌 경우에는 불필요한 기능이 되어 성능을 떨어뜨리는 요인이 된다. 
- 그래서 새로 추가된 ArrayList와 HashMap과 같은 컬렉션은 동기화를 자체적으로 처리하지 않고 필요한 경우에만 Collections 클래스의 동기화 메서드를 이용해서 동기화 처리가 가능 하도록 변경하였다.

## Map.Entry 인터페이스란?
- Map.Entry 인터페이스는 Map 인터페이스의 내부 인터페이스이다. 내부 클래스와 같이 인터페이스도 인터페이스 안에 인터페이스를 정의하는 것이 가능하다. 

 ## Iterator 란?
- 컬렉션에 저장된 요소를 접근하는데 사용되는 인터페이스이다. Iterator의 성능을 향상시킨 ListIterator도 존재한다. 컬렉션 인터페이스 내에 iterator()라는 Iterator 인터페이스를 반환하는 메서드가 정의 되어 있다.

 ### Map은 Iterator를 사용하지 못하나?
- Map 인터페이스를 구현한 컬렉션 클래스는 키와 값 쌍으로 데이터를 저장하고 있기 때문에 iterator()를 직접 호출할 수는 없고 그 대신 keySet()이나 entrySet()과 같은 메서드를 통해서 Set으로 얻어온 뒤 사용이 가능하다.

 > Source Code

        Map map = new HashMap();
        map.put("rlawlstjd007", "김진성");
        map.put("beinone", "조성빈");
        map.put("hengole", "김기황");
    
        Iterator keySetIt = map.keySet().iterator();
        Iterator entrySetIt = map.entrySet().iterator();
    
        System.out.println("===== Key Set 실행결과 =====");
        while (keySetIt.hasNext()){
            System.out.println(keySetIt.next());
        }
    
        System.out.println("===== Entry Set 실행결과 =====");
        while (entrySetIt.hasNext()){
            System.out.println(entrySetIt.next());
        }
