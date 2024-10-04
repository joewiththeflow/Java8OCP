package theflow.with.joe.J_and_S_Guide.Chapter1;

public class Instanceof {
    public static void main(String[] args) {
        class HeavyAnimal { }
        class Hippo extends HeavyAnimal { }
        class Elephant extends HeavyAnimal { }

        HeavyAnimal hippo = new Hippo();
        boolean b1 = hippo instanceof Hippo;        // true
        boolean b2 = hippo instanceof HeavyAnimal;  // true
        boolean b3 = hippo instanceof Elephant;     // false

        boolean b4 = hippo instanceof Object;     // true

        Hippo nullHippo = null;
        boolean b5 = nullHippo instanceof Object;     // false
        boolean b6 = null instanceof Object;        // false

//        boolean b7 = null instance of null;     // DOES NOT COMPILE


    }
}
