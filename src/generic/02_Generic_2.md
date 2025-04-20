# 2. Generic_2

- 타입 매개변수 제한
    - 제네릭 타입으로 `<T>` 를 사용하면 자바 컴파일러는 `T` 에 어떤 값이 들어올지 예측 할 수 없다.
    - 즉, 내가 직접 생성한 메서드가 아닌 모든 타입의 부모인 `Object` 의 메서드만 호출 할 수 있다.
    - 이럴때는 `<T>` 에서 `T` 를 특정 타입으로 제한할 수 있다.
    
    ```java
    // T 의 타입 제한
    public class AnimalHospital <T extends Animal> { ... }
    ```
    
    - 위 코드에서 `<T extends Animal>` 은 `T` 를 `Animal` 과 그 자식 클래스로만 제한하는 것이다.
        - 즉, Animal 에서 지정한 메서드를 호출 할 수 있다.
- 제네릭 메서드
    - 제네릭 메서드는 특정 메서드에서만 사용하는 제네릭을 말한다.
    - 제네릭 타입
        - 정의: `GenericClass<T>`
        - 타입 인자 전달: 객체를 생성하는 시점 → `new GenericClass<String>`
    - 제네릭 메서드
        - 정의: `<T> T genericMethod(T t)`
        - 타입 인자 전달: 메서드를 호출하는 시점 → `GenericMethod.<Integer>genericMethod(i)`
    - 제네릭 메서드는 클래스 전체가 아니라 특정 메서드 단위로 제네릭을 도입할 때 사용 → 메서드의 반환 타입 왼쪽에 다이아몬드를 사용해서 `<T>` 와 같이 타입 매개변수를 선언함.
    - 즉, 제네릭 메서드는 메서드를 호출하는 시점에 타입 인자를 전달해서 타입을 지정하는 것 → 타입을 지정하면서 메서드를 호출함!
    - 제네릭 메서드는 인스턴스 메서드와 static 메서드에 모두 적용
    
    ```java
    class Box<T> { //제네릭 타입
    	static <V> V staticMethod2(V t) {} //static 메서드에 제네릭 메서드 도입
    	<Z> Z instanceMethod2(Z z) {} //인스턴스 메서드에 제네릭 메서드 도입 가능
    }
    ```
    
    - 제네릭 타입은 static 메서드에 타입 매개변수를 사용할 수 없음 → 제네릭 타입은 객체를 생성하는 시점에 타입이 정해지는데 static 은 인스턴스 단위가 아닌 클래스 단위이므로 제네릭 타입과는 무관
        - 따라서 static에 제네릭을 사용하려면 제네릭 타입이 아닌 제네릭 메서드를 사용해야함.
- 제네릭 메서드 활용
    - 우선순위
        - 제네릭 타입보다 제네릭 메서드의 우선순위가 더 높은 순위를 가짐. → 즉 제네릭의 이름이 동일하다면 제네릭 메서드의 이름을 따른다
            - 그러나 프로그래밍에서 이렇게 모호한것은 좋지 않으니 이름 변경을 추천한다.
- 와일드카드
    - 와일드카드는 컴퓨터 프로그래밍에서 `*` , `?` 와 같이 하나 이상의 문자들을 상징하는 특수 문자를 뜻한다. → 쉽게 이야기해서 여러 타입이 들어올 수 있다는 뜻
    - 와일드카드는 `?` 를 사용해서 정의 → `<?>` 는 `<? extends Object>` 와 같은 의미
    - 와일드카드는 제네릭 타입이나, 제네릭 메서드를 선언하는 것이 아님 → 이미 만들어진 제네릭 타입을 활용할 때 사용
    - 제네릭 타입이나 제네릭 메서드를 정의하는게 꼭 필요한 상황이 아니라면, 더 단순한 와일드카드 사용을 권장
    - 와일드카드는 상한과 하한을 둘 수 있다.
        - 상한 : `<? extends Animal>`  → `Animal` 과 그 자식만 이용 가능
        - 하한 : `<? super Animal>` → `Animal` 부터 그 부모까지 이용 가능 (`Object` 포함)
- 타입 이레이저
    - 제네릭은 자바 컴파일 단계에서만 사용되고, 컴파일 이후에는 제네릭 정보가 삭제된다.
    - 즉, `.java` 파일에는 제네릭이 남아있지만 `.class` 파일에는 제네릭이 존재하지 않는다.
    - 상한없이 `<T>` 로 선언되면 타입은 모두 `Object` 로 변경된다.
    - 상한이 있다면 해당 상한된 타입으로 변경됨. → `<T extends Animal>` 이면 `Animal` 로 변경된다.
    - 한계
        - 컴파일 이후에는 제네릭의 타입 정보가 존재하지 않는다 → 즉 런타임에 타입을 활용하는 코드는 작성 될 수 없다.
        
        ```java
        // 소스코드
        class EraserBox<T> {
        	public boolean instanceCheck(Object param) {
        		return param instanceof T; // 오류
        	}
        	
        	public T create() {
        		return new T(); // 오류
        	}
        }
        ```
        
        ```java
        // 런타임
        class EraserBox {
        	public boolean instanceCheck(Object param) {
        		return param instanceof Object; // 오류
        	}
        	
        	public T create() {
        		return new Object(); // 오류
        	}
        }
        ```
        
        - 소스코드와 달리 런타임 시에는 `T` 가 모두 `Object`로 변환된다. → 즉 타입 매개변수에는 `instance of` 와 `new` 를 허용하지 않는다.
            - `instance of` 는 `Object`와 비교하므로 항상 참이 된다
            - `new T` 는 항상 `new Object`가 된다.