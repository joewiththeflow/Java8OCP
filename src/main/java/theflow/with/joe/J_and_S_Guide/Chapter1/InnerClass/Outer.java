package theflow.with.joe.J_and_S_Guide.Chapter1.InnerClass;

public class Outer {
    private String greeting = "Hi";

    protected class Inner {
        public int repeat = 3;
        public void go() {
            for (int i = 0; i < repeat; i++) {
                System.out.println(greeting);   // can access the private variable of the outer class
            }
        }
    }

    public void callInner() {
        Inner inner = new Inner();      // can create an instance of the class as we are inside instance of Outer
        inner.go();
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.callInner();

        Outer outer2 = new Outer();
        // Very important syntax, need an instance of Outer to create inner as we are in a static context here
        Inner inner2 = outer2.new Inner();
        inner2.go();

        // Or
        Inner inner3 = new Outer().new Inner();
        inner3.go();
    }

    // Produces:
//    Hi
//    Hi
//    Hi
//    Hi
//    Hi
//    Hi
//    Hi
//    Hi
//    Hi
}
