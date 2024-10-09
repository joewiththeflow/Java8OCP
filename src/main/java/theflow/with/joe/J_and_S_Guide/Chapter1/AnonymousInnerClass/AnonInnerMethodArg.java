package theflow.with.joe.J_and_S_Guide.Chapter1.AnonymousInnerClass;

public class AnonInnerMethodArg {
    interface SaleTodayOnly {
        int dollarsOff();
    }

    public int pay() {
        return admission(10, new SaleTodayOnly() {
            @Override
            public int dollarsOff() {
                return 3;
            }
        });
    }

    public int admission(int basePrice, SaleTodayOnly sale) {
        return basePrice - sale.dollarsOff();
    }

    public static void main(String[] args) {
        System.out.println(new AnonInner().admission(10));
    }
}
