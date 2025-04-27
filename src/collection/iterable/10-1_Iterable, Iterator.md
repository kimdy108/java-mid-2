# 10-1. Iterable, Iterator

- 순회란?
    - 자료 구조에 순회는 자료 구조에 들어있는 데이터를 차례대로 접근해서 처리하는 것
    - 다만 각 자료 구조마다 데이터를 접근하는 방식이 모두 다름
        - 이러한 이유로 자바에서 `Iterable`과 `Iterator`를 제공
- Iterable과 Iterator
    - `Iterable`
        - ‘반복 가능한’ 이라는 뜻
        - 주요 메서드 : 단순히 `Iterator`를 반환
    - `Iterator`
        - ‘반복자’ 라는 뜻
        - 주요 메서드
            - `hasNext()` : 다음 요소가 있는지 확인. 있으면 `true`, 없으면 `false`
            - `next()` : 다음 요소를 반환
    - 순회하는 방법
        - `hasNext()`로 다음 요소가 있는지 확인 후 `next()`로 반환
    - 두개 모두 인터페이스로 구현체가 필요
- 향상된 for 문
    - `for-each`문으로 불리는 향상된 `for`문은 자료 구조를 순회하는 것이 목적
    - 자바는 `Iterable` 인터페이스를 구현한 객체에 대해서 향상된 `for`문을 사용할 수 있게 해준다.
        - 이렇게 된다면 자바는 컴파일 시점에 `hasNext()`로 확인 후 `next()`로 표출하는 `while`문으로 바꿔준다.
        
        ```java
        // 향상된 for 문
        for (int value : myArray) {
        	System.out.println("value = " + value);
        }
        
        // 컴파일 시점에 while 문
        while (iterator.hasNext()) {
        	Integer value = iterator.next();
        	System.out.println("value = " + value);
        }
        ```
        
- 자바가 제공하는 Iterable, Iterator
    - `Collection` 프레임워크는 `Iterable`의 자식이다.
    - 즉 `Collection` 프레임워크에서 제공하는 `List`, `Set`, `Queue` 등은 `Iterable` 인터페이스를 제공하고 `Iterator` 도 모두 구현이 되어있다.
    - `Map`의 경우 조금 다르다. `Key` 뿐만 아니라 `Value`도 있어 바로 순회는 안된다.
        - 그러나 `Key`나 `Value`를 `keySet()`, `values()`를 통해 `Set`, `Collection`으로 반환하면 순회가 가능하다.
        - 물론 `Entry` 를 `Set` 구조로 반환하는 `entrySet()`도 순회가 가능하다.
    - 특징
        - `Iterator` , `Iterable` 은 인터페이스이다. 따라서 다형성을 적극 활용할 수 있다. → 실제 코드에서 `Iterable`이나 `Iterator`를 파라미터로 받으면 `List`, `Set`등을 받을 수 있다.
        - 메서드는 새로운 자료 구조가 추가되어도 해당 자료 구조가 `Iterator` , `Iterable` 만 구현하고 있다면 코드 변경 없이 사용할 수 있다.
            
            ```java
            private static void printAll(Iterator<Integer> iterator) {
            	System.out.println("iterator = " + iterator.getClass());
            	while (iterator.hasNext()) {
            		System.out.println(iterator.next());
            	}
            }
            
            private static void foreach(Iterable<Integer> iterable) {
            	System.out.println("iterable = " + iterable.getClass());
            	for (Integer i : iterable) {
            		System.out.println(i);
            	}
            }
            ```
            
    - 참고
        - `Itorator` 디자인 패턴은 객체 지향 프로그래밍에서 컬렉션의 요소들을 순회할 때 사용되는 디자인 패턴이다.
        - 이 패턴은 컬렉션의 내부 표현 방식을 노출시키지 않으면서도 그 안의 각 요소에 순차적으로 접근할 수 있게 해준다.
        - 또한 컬렉션의 구현과는 독립적으로 요소들을 탐색할 수 있는 방법을 제공하며, 이로 인해 코드의 복잡성을 줄이고 재사용성을 높일 수 있다.