package generic.ex4;

import generic.animal.Animal;

public class ComplexBox <T extends Animal> {
    private T animal;

    public void set(T animal) {
        this.animal = animal;
    }

    // 모호할 땐 이름을 바꾸는게 좋음 (T -> Z)
//    public <T> T printAndReturn(T t) {
//        System.out.println("animal.className:" + animal.getClass().getName());
//        System.out.println("t.className: " + t.getClass().getName());
//
//        return t;
//    }

    public <Z> Z printAndReturn(Z z) {
        System.out.println("animal.className:" + animal.getClass().getName());
        System.out.println("t.className: " + z.getClass().getName());

        return z;
    }
}
