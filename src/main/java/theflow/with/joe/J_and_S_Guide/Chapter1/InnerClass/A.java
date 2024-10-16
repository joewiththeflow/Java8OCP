package theflow.with.joe.J_and_S_Guide.Chapter1.InnerClass;

public class A {
    private int x = 10;
    class B {
        private int x = 20;
        class C {
            private int x = 30;
            public void allTheX() {
                System.out.println(x);          // 30
                System.out.println(this.x);     // 30
                System.out.println(B.this.x);   // 20
                System.out.println(A.this.x);   // 10
            }
        }
    }

    public static void main(String[] args) {
        A a = new A();
        B b = a.new B();      // You could say B rather than A.B
        A.B.C c = b.new C();    // You could say B.C rather than A.B.C, but not just C
        c.allTheX();
    }
}
