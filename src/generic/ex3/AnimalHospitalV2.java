package generic.ex3;

public class AnimalHospitalV2<T> {
    private T animal;

    public void set(T animal) {
        this.animal = animal;
    }

    public void checkup() {
        // 컴파일 오류 -> T의 타입을 메서드를 정의하는 시점에는 알 수 없음! -> Object의 기능만 사용 가능
        // System.out.println("동물 이름: " + animal.getName());
        // animal.sound();

        animal.toString();
        animal.equals(null);
    }

    public T getBigger(T target) {
        // 컴파일 오류 -> 위와 동일
        // return animal.getSize() > target.getSize() ? animal : target;
        return null;
    }
}
