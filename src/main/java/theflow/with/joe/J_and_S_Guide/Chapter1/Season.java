package theflow.with.joe.J_and_S_Guide.Chapter1;

public enum Season {
    WINTER, SPRING, SUMMER, FALL
}

enum Season2 {
    WINTER("Low"), SPRING("Medium"), SUMMER("High"), FALL("Medium");     // SEMI-COLON!!!!!!
    private String expectedVisitors;

    private Season2(String expectedVisitors) {
        this.expectedVisitors = expectedVisitors;
    }

    public void printExpectedVisitors() {
        System.out.println(expectedVisitors);
    }
}

class testEnum {
    public static void main(String[] args) {
        Season s = Season.SUMMER;
        System.out.println(Season.SUMMER);      // SUMMER
        System.out.println(s == Season.SUMMER); // true

        for(Season season : Season.values()) {
            System.out.println(season.name() + " " + season.ordinal());
        }

        Season s1 = Season.valueOf("SUMMER");   // SUMMER
//        Season s2 = Season.valueOf("summer");   // exception

        Season summer = Season.SUMMER;
        switch (summer) {
            case WINTER:
                System.out.println("Get out the sled!");
                break;
            case SUMMER:
                System.out.println("Time for the pool!");
                break;
            default:
                System.out.println("Is it summer yet?");
        }

        Season2.SUMMER.printExpectedVisitors();     // High
    }
}


