# 1. Generic
- Java 5부터 추가 된 타입이다. 제네릭은 클래스, 인터페이스, 메소드를 정의할 때 타입을 파라미터로 사용할 수 있도록한다.
- 컴파일 시의 타입 체크를 해주는 기능이다.
## 1. 1.이점

### 1. 컴파일 시 강한 타입 체크를 할 수 있다.
### 2. 타입 변환을 제거한다.
    //비 제네릭 코드에서는 형변환 때문에 성능에 악영향을 끼칠 수 있다.
    List list = new ArrayList();
    list.add("hello");
    String str = (String) list.get(0); 

## 1. 2. 제네릭 타입

- 제네릭 타입은 타입을 파라미터로 가지는 클래스와 인터페이스를 말한다. 제네릭 타입은 클래스 또는 인터페이스 이름 뒤에 부호가 붙고 사이에 타입 파라미터가 위치한다.
##
    //일반적으로 대문자 알파벳 한 글자로 표현한다.
    public class 클래스명<T> { ... }
    public interface 인터페이스명<T> { ... }

    //제네릭을 이용한 클래스 예
    public class Box<T> {
        private T t;
        public T get() { return t; }
        public void set(T t) { this.t = t; }
    }

    //사용예
    Box<Sgtring> box = new Box<String>();
    box.set("hello");
    String str = box.get();

## 1. 3. 멀티 타입 파라미터 (class<K, V ,...>, interface<K, V , ...>)
- 제네릭 타입은 두 개 이상의 멀티 타입 파라미터를 사용할 수 있다. 

##
    public class Product<T, M> {
        private T kind;
        private M model;

        public T getKind() { return kind; }
        public M getModel() { return model; }

        public void setKind(T kind) { this.kind = kind; }
        public void setModel(M model) { this.model = model; }
    }
    //java7 부터는 제네릭 타입 파라미터의 중복 기술을 줄이기 위해서 다이아몬드 연산자 <>를 제공한다. 


## 1. 4. 제네릭 메소드 (<T, R> R method(T t)) 
- 제네릭 메소드는 매개 타입과 리턴 타입으로 타입 파라미터를 갖는 메소드를 말한다. 
##
    public <타입파라미터, ...> 리턴타입 메소드명(매개변수, ...) { ... }
    public <T> Box<T> boxing(T t) { ... }

- 제네릭 메소드는 두가지 방식으로 호출할 수 있다. 코드에서 타입 파라미터의 구체적인 타입을 명시적으로 지정해도 되고 컴파일러가 매개값의 타입을 보고 구체적인 타입을 추정하도록 할 수 있다.
##
    리턴타입 변수 = <구체적타입> 메소드명(매개값);
    리턴타입 변수 = 메소드명(매개값);

    //실제 사용 예
    Box<Integer> box = <Integer>boxing(100)l    //타입 파라미터를 명시적으로 Integer로 지정
    Box<Integer> box = boxing(100);             //타입 파라미터를 Integer로 추정
    

## 1. 5. 제한된 타입 파라미터 (<T extends 최상위 타입>)
- 타입 파라미터에 지정되는 구체적인 타입을 제한할 필요가 종종 있다. 예를 들어 숫자 연산 시에는 Number 타입 혹은 하위 클래스 타입 파라미터 (Byte, Short, Integer, Long, Double)의 인스턴스만 가져야 하는 것처럼 말이다.
- 인터페이스도 가능하지만 implements를 사용하진 않는다.
##

    public <T extends 상위타입> 리턴타입 메소드 (매개변수, ...) { ... }
    
    //사용 예
    public <T extends Number> int compare(T t1, T t2) {
        double v1 = t1.doubleValue();       //Number의 doubleValue() 메소드 사용
        double v2 = t2.doubleValue();       //Number의 doubleValue() 메소드 사용
        return Double.compare(v1, v2);
    }


## 1.6 와일드카드 타입 (<?>, <? extends ...>, <? super ...>)
- 코드에서 ?를 일반적으로 와일드카드 라고 부른다.  제네릭 타입을 매개갑이나 리턴 타입으로 사용할 때 구체적인 타입 대신에 와일드카드를 다음과 같이 세가지 형태로 사용이 가능하다.
##

1.  제네릭타입<?> (제한 없음): 타입 파라미터를 대체하는 구체적인 타입으로 모든 클래스나 인터페이스 타입이 올 수 있다.
2. 제네릭타입 <? extends 상위 타입> (상위 클래스 제한) : 타입 파라미터를 대치하는 구체적인 타입으로 상위 타입이나 하이 타입만 올 수 있다.
3. 제네릭타입 <? super 하위 타입> (하위 클래스 제한) : 타입 파라미터를 대치하는 구체적인 타입으로 하위 타입이나 상위 타입이 올 수 있다. 


### 예제
- 아래의 제네릭 타입의 Course 클래스가 있다고 하자. 
##

    public class Course<T> {
        private String name;
        private T[] students;

        public Course(String name, int capacity) {
            this.name = name;
            students = (T[]) (new Object[capacity]);
        }

        public String getName() {return name;}
        public T[] getStudents() {return students;}
        public void add(T t) {
            for(int i = 0l i < students.length; i++) {
                if(students[i] == null) {
                    students[i] = t;
                    break;
                }
            }
        }
    }

## 1.7 제네릭 타입의 상속과 구현 
- 제네릭 타입도 다른 타입과 마찬가지로 부모 클래스가 될 수 있다. 아래의 예처럼 말이다.
##
    public class ChildProduct<T, M> extends Product<T, M> { ... }
- 자식 제네릭 타입은 추가적으로 타입 파라미터를 가질 수 있다. 다음은 세 가지 타입 파라미터를 가진 자식 제네릭 타입을 선언한 것이다.
## 
    public class ChildProduct<T, M, C> extends Product<T, M> { ... }

### * 질문 
1. 강한 타입 체크가 뭘까?
2. 형변환을 하는 데에는 얼마나 많은 cost가 드는 걸까?
