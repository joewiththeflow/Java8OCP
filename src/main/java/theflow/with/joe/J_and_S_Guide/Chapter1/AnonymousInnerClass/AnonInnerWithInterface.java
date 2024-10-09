package theflow.with.joe.J_and_S_Guide.Chapter1.AnonymousInnerClass;

public class AnonInnerWithInterface {
    interface SaleTodayOnly {
        int dollarsOff();
    }
    public int admission(int basePrice) {
        SaleTodayOnly sale = new SaleTodayOnly() {
            public int dollarsOff() { return 3; }           // Note that this has to be "public" to compile
        };
        return basePrice - sale.dollarsOff();
    }

    public static void main(String[] args) {
        System.out.println(new AnonInner().admission(10));
    }
}
