package theflow.with.joe.J_and_S_Guide.Chapter1.InnerClass;

public class CaseOfThePrivateInterface {
    private interface Secret {
        public void shh();
    }
    class DontTell implements Secret {
        public void shh() { }
    }

    // Note that you can access Secret interface definition from within static context
    public static void main(String[] args) {
        Secret anonymousSecret = new Secret() {
            @Override
            public void shh() {
                System.out.println("hello");    // hello
            }
        };
        anonymousSecret.shh();
    }
}

//class MadeUp implements Secret {} // can't access private interface Secret definition from outside the class
