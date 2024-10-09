package theflow.with.joe.J_and_S_Guide.Chapter1.LocalInnerClass;

public class Outer {
    private int length = 5;
    public void calculate() {
        final int width = 20;       // marked as final but would work if final wasn't present as variable is "effectively final"
        class Inner {
            public void multiply() {
                System.out.println(length * width);
            }
        }
        Inner inner = new Inner();
        inner.multiply();
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.calculate();
    }
}
