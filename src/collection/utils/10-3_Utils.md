# 10-3. Utils

- 정렬 _ Collections
    - `max` : 정렬 기준으로 최대 값을 찾아서 반환한다.
    - `min` : 정렬 기준으로 최소 값을 찾아서 반환한다.
    - `shuffle` : 컬렉션을 랜덤하게 섞는다.
    - `sort` : 정렬 기준으로 컬렉션을 정렬한다.
    - `reverse` : 정렬 기준의 반대로 컬렉션을 정렬한다. (컬렉션에 들어있는 결과를 반대로 정렬한다.)
- 가변 컬렉션 & 불변 컬렉션
    - 불변 컬렉션
        - `List.of(1,2,3)`
        - `Set.of(1,2,3)`
        - `Map.of(1,"one",2,"two")`
        - 특징 : 불변 컬렉션으로 `.add()` 등의 메서드를 사용하면 `UnsupportedOperationException` 에러가 발생한다.
    - 컬렉션 전환
        
        ```java
        // 불변 -> 가변
        List<Integer> list = List.of(1, 2, 3);
        ArrayList<Integer> mutableList = new ArrayList<>(list);
        
        // 가변 -> 불변
        List<Integer> unmodifiableList = Collections.unmodifiableList(mutableList);
        ```
        
- 빈 리스트 생성
    - 가변 리스트
        
        ```java
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new LinkedList<>();
        ```
        
    - 불변 리스트
        
        ```java
        List<Integer> list3 = Collections.emptyList(); //자바5
        List<Integer> list4 = List.of(); //자바9 -> 이 방식을 권장
        ```
        
- Arrays.asList()
    - `Arrays.asList()`를 이용해서 리스트를 생성할 수 있다. 다만 이 방식은 자바 1.2 부터 있던 방식으로 자바 9 이상에서는 `List.of()` 를 권장한다.
    - `Arrays.asList()` 는 크기 변경은 안되고 값 변경만 된다.
        - `.set()` 은 사용 가능하고 .`add()` 와 `.remove()` 는 사용할 수 없다.
    - 즉, 자바 9 이전버전에서는 해당 방식을 사용해야 한다.
- 멀티 쓰레드 동기화
    - `Collections.synchronizedList(list);`
        - `Collections.synchronizedList` 를 사용하면 일반 리스트를 멀티스레드 상황에서 동기화 문제가 발생하지 않는 안전한 리스트로 만들 수 있다.
            - 예를 들어 1번 쓰레드에서는 `.add()`를 하고 2번쓰레드에서 `.remove()` 를 하더라도 문제가 생기는 것을 방지해준다.
        - 동기화 작업으로 인해 일반 리스트보다 성능은 더 느리다.