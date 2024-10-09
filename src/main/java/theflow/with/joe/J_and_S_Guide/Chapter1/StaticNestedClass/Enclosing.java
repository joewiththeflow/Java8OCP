package theflow.with.joe.J_and_S_Guide.Chapter1.StaticNestedClass;

public class Enclosing {
    static class Nested {
        private int price = 6;
        int cost = 6;
        static int amount = 6;
    }

    public void accessPrice() {
        int price = new Nested().price;         // Still need an instance to access instance variable price
        int amount = Nested.amount;             // No need for instance as amount is static in Nested
    }

    public static void main(String[] args) {
        Nested nested = new Nested();
        System.out.println(nested.price);       // allowed to access private instance variable od static class
        System.out.println(Nested.amount);      // No need for instance as amount is static in Nested
    }
}

class OtherClass {
    public static void main(String[] args) {
        Enclosing.Nested nested = new Enclosing.Nested();
//        System.out.println(nested.price);     // can't access as it is private
        System.out.println(nested.cost);        // 6
        System.out.println(Enclosing.Nested.amount);    // amount is static so no need for instance of the static class
    }
}
