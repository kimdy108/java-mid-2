# 10-2. Comparable, Comparator

- Arrays.sort()
    - 배열에 들어있는 데이터를 순서대로 정렬하는 방법 → 예) `[3, 2, 1]`을 `[1, 2, 3]`
        - 순서
            - 제일 처음에 있는 숫자인 3을 2 와 비교하여 자리 교체 → 1과 비교하여 자리 교체
            - 이후 다음에 있는 2가 1과 비교하여 자리 교체
- 현재의 정렬 방식
    - 기본형 배열의 경우 듀얼 피벗 퀵소트(Dual-Pivot QuickSort)를 사용하고, 객체 배열의 경우 팀소트(TimeSort)를 사용
    - 평균 O(n log n)의 성능을 제공
- Comparator
    - 두 값을 비교할 때 비교 기준을 직접 제공할 수 있는 방식
        
        ```java
        // 첫 번째 인수가 더 작으면 음수 (-1)
        // 두개가 같으면 0
        // 두 번째 인수가 더 크면 양수 (1)
        public interface Comparator<T> {
        	int compare(T o1, T o2);
        }
        ```
        
    - `Arrays.sort()` 를 사용할 때 비교자(`Comparator` )를 넘겨주면 알고리즘에서 어떤 값이 더 큰지 두 값을 비교할 때 비교자를 사용함
        
        ```java
        // 비교자
        Arrays.sort(array, new AscComparator())
        // 비교자의 반대로 정렬
        Arrays.sort(array, new AscComparator().reversed())
        ```
        
- Comparable
    - 자바가 기본적으로 제공하는 `Integer` , `String` 같은 객체가 아닌 직접 만든 객체를 정렬하려면 `Comparable`을 사용해야 한다.
    - 새로 만든 객체에 `Comparable`을 상속 (`implement`) 하여 구현해야한다. → 이때 객체의 어떤 값을 기준으로 할지 정해야 한다. (이걸 클래스의 기본 정렬 방식을 구현한 것 이라고 한다.)
        - `Comparable`을 통해 구현한 순서를 자연 순서(Natural Ordering)라 한다.
    - 이후 `Arrays.sort()` 를 사용하면 해당 객체에 구현한 `Comparable`을 통해 기본 정렬 방식으로 정렬된다.
    - 해당 객체의 기본 정렬이 아닌 다른 정렬을 사용하려면 `Comparator`를 사용하여 다른 구현체를 하나 더 생성 후 `Arrays.sort()`의 인수로 넘겨주면 된다. (`reversed()`도 사용 가능)
    - 주의
        - 만약 `Comparable`도 구현하지 않고, `Comparator`도 제공하지 않으면 런타임 오류가 발생한다.
        - `Comparable` 를 이용하여 기본 정렬을 구현하고 `Comparator`를 이용하여 별도로 정렬을 구현한 경우 `Comparator` 가 우선권을 갖는다.
        - 또한 `Comparator`가 있는 경우 `Comparable` 를 구현하지 않아도 런타임 에러가 발생하지 않는다.
        - 자바가 제공하는 `Integer` , `String` 같은 기본 객체들은 대부분 `Comparable` 을 구현되어있다.
- List에서의 정렬
    - `List`는 순서가 보장되어있는 자료구조이다. → 정렬을 사용할 수 있다. (반대로 `Set`은 순서가 보장되어있지 않아서 사용할 수 없다.)
    - `List` 객체에서 `.sort()` 를 사용하여 구현되어있는 기본 정렬을 사용 할 수 있다. → 물론 리스트 정렬도 `Comparator`를 이용하여 다른 구현체가 있는 경우 해당 정렬을 사용 할 수 있다. (`reversed()`도 가능)
- 이진 탐색 트리 (TreeSet)
    - TreeSet 과 같은 이진탐색트리는 데이터를 저장할 때부터 정렬을 하여 저장해야한다.
        - 즉 `Comparable` 또는 `Comparator` 가 필수이다.
    - `TreeSet` 을 생성할 때 별도의 비교자를 제공하지 않으면 객체가 구현한 `Comparable` 을 사용한다.
        - 인수로 `Comparator`를 이용한 별도의 비교자를 인수로 제공하면 해당 방식으로 정렬한다.
    - 즉 `Comparable` 또는 `Comparator` 가 구현되지 않으면 런타임 에러가 발생한다.