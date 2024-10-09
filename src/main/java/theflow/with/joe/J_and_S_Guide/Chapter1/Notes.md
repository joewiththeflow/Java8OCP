## Virtual method invocation

- will use the subclass method if there is an overridden method in subclass
- only applies to methods, not to variables

## instanceof

- null (or pointer to null) instanceof Object : always false
- if the compiler knows there is no way for a reference to be an instanceof, it will throw a compiler error. However, the same is not true of interfaces.


## public boolean equals(Object obj)

- always an Object as the parameter
- StringBuilder does not override the default Object equals() method, but String does
- x.equals(null) : returns false, not a NullPointerException
- null.equals(null) : compiler error as equals is not a method on null

## public int hashCode()

- expected to override this method if you override equals()
- used when storing an object as a key in a map
- you could the hashCode() method of an existing class e.g. a String:
    ```
    String rank;
    ......
    public int hashCode() {
        return rank.hashCode();
    ```
- if you need to define your own
    - could just use a primitive or divide it to get a smaller int
    - don't need to use all instance varaibles
    - common not to include boolean and char variables in hash code
    
- hashCode() must return the same result consistently so you shouldn't use variables that may change
- if equals() returns true, hashCode for each object MUST return the same value
    - within the same point, there's also a statement that suggests that you MUST use a subset of the variables that equals() uses to be legal
- if equals() returns false, hashCode may/may not be the same

- common to multiply by a prime number when combining multiple fields as it helps make hashCodes more unique which is better for distibuting, e.g.:
  ```
  public int hashCode() {
      return keyFields + 7 * otherKeyField.hashCode();
  }
  ```

## public enum \<name of enum> {}

### Basic enum usage

- An enumeration is a fixed set of constants
- an enum is better than this as it provides type-safe checking 
    - unlike with numeric constants, where you could pass an invalid value and not find out until runtime, it is impossible to create an invalid enum type without introducing a compiler error
- behind the scenes, it is a type of class that mainly stores `static` members
- enum.values() : gives an array of all the values
- <enum constant>.name() : gives the name of the enum constant
- <enum constant>.ordinal () : corresponding int value in the order constants were declared
- For example:
```
public enum Season {
WINTER, SPRING, SUMMER, FALL
}

Season s = Season.SUMMER;
System.out.println(Season.SUMMER);      // SUMMER
System.out.println(s == Season.SUMMER); // true

for(Season season : Season.values()) {
    System.out.println(season.name() + " " + season.ordinal());
}

// above prints:
WINTER 0
SPRING 1
SUMMER 2
FALL 3

// remember an enum is a type and not an int:
if (Season.SUMMER == 2) {}      // DOES NOT COMPILE

// you can create an enum from a String, which is helpful when working with older code:
Season s1 = Season.valueOf("SUMMER");   // SUMMER
Season s2 = Season.valueOf("summer");   // exception
// Exception in thread "main" java.lang.IllegalArgumentException: No enum constant enums.Season.summer

// You CANNOT EXTEND an enum:
public enum ExtendedSeason extends Season { } // DOES NOT COMPILE
```

### enums in Switch statements

For example:

```
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
    
    // Note that you use the constant for the case value
    
        case Season.WINTER      // WOULD NOT COMPILE
        case 0:                 // WOULD NOT COMPILE - enum type is NOT an int
```

### enums with Constructors, Fields and Methods

Common to give state to each enum. For example:

```
public enum Season {
   WINTER("Low"), SPRING("Medium"), SUMMER("High"), FALL("Medium");     // SEMI-COLON!!!!!!
   private String expectedVisitors;
   private Season(String expectedVisitors) {
      this.expectedVisitors = expectedVisitors;
   }
   public void printExpectedVisitors() {
      System.out.println(expectedVisitors);
   }
```
Note the semi-colon in the enum constants declaration. This is REQUIRED IF there is anything in the enum besides the values.

MUST use a `private` constructor:
- can only be called from within the enum
- `public` would cause a compile error

You can call the enum method:
```
    Season.SUMMER.printExpectedVisitors();  // High
```
Note that we don't appear to call the constructor. However, the first time we ask for any of the enum values, Java constructs all the enum values. After this, Java returns the already constructed enum values.
Therefore the following code only calls the constructor once:
```
public enum OnlyOne {
   ONCE(true);
   private OnlyOne(boolean b) {
      System.out.println("constructing");
   }
   public static void main(String[] args) {
      OnlyOne firstCall = OnlyOne.ONCE;    // prints constructing
      OnlyOne secondCall = OnlyOne.ONCE;   // doesn't print anything
    } 
}
```
We could let each enum manage hours itself:
```
public enum Season {
   WINTER {
      public void printHours() { System.out.println("9am-3pm"); }
   }, SPRING {
      public void printHours() { System.out.println("9am-5pm"); }
   }, SUMMER {
      public void printHours() { System.out.println("9am-7pm"); }
   }, FALL {
      public void printHours() { System.out.println("9am-5pm"); }
   };
   public abstract void printHours();
}
```
Note the enum itself has an abstract class and therefore every enum is required to implement the method or else we would get a compiler error.

If we don't want every enum to have a method, we could create a default method implementation and only override for the special cases:
```
public enum Season3 {
   WINTER {
      public void printHours() { System.out.println("short hours"); }
   }, SUMMER {
      public void printHours() { System.out.println("long hours"); }
   }, SPRING, FALL;
   public void printHours() { System.out.println("default hours"); }
}
```
Note we still need the semi-colon after FALL as we have more than just values.

See [Card](Card.java) for more complex enum example.


## Nested Classes

- A `nested class` is a class defined within another class
- A nested class that is not static is called an `inner class`
- There are 4 types:
  - A `member inner class` is a class defined at the same level as instance variables. It is not static. Often referred to as just an `inner class`
  - A `local inner class` is defined within a method
  - An `anonymous inner class` is a speical case of a local inner class that does not have a name
  - A `static nested class` is a `static` class that is defined at the same level as static variables
- There are several benefits:
  - Encapsulate helper classes inside the containing class
  - Easy to create a class that will only be used in one place
  - Can make the code easier to read, although can also make code harder to read when used improperly

## Member Inner Class

Defined at the member level of a class.

Have the following properties:
- Can be declared public, private, protected or use default access
- Can extend any class and implement interfaces
- Can be abstract or final
- Cannot declare static fields or methods
- Can access members of the outer class including `private` members

An example can be found here: [Outer.java](InnerClass/Outer.java)

Within the Outer class, an instance of Inner can be created easily:
```
  public void callInner() {
      Inner inner = new Inner();
```

However, from outside the class, or from within a static context in the class e.g. the main() method, you need to make use of the `new` method:

```
  Outer outer2 = new Outer();
  // Very important syntax, need an instance of Outer to create inner as we are in a static context here
  Inner inner2 = outer2.new Inner();
  inner2.go();

  // Or
  Inner inner3 = new Outer().new Inner();
  inner3.go();
```

Compiling of `Outer.java` above actually creates 2 class files:
- Outer.class
- Outer$Inner.class

A more complex example can be found here: [A.java](InnerClass/A.java). This example shows how multiple nested classes can be used and how the same variable can be used in each:
```
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
```
Note the special syntax to access the x variable in the outer class e.g. `B.this.x` and `A.this.x`.

### Private Interface

The following looks weird but is legal: [CaseOfThePrivateInterface](InnerClass/CaseOfThePrivateInterface.java)
```
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
```
The rule that all methods in an interface must be `public` still applies. A class that implements the interface must define that method as `public`.

The interface itself does not have to be `public`. Just like any inner class, an inner interface can be `private`. This means the interface can only be referred to within the current outer class.

## Local Inner Class

A `local inner class` is a nested class within a method.
- does not exist until the method is invoked
- out of scope when the method returns
- can only create instances within the method and those instances can be returned by the method

Local inner classes have the following properties:
- No access specifier
- Cannot be declared `static` and cannot have `static` fields or methods
- They have access to all fields and methods of the enclosing class
- They do not have access to local variables of a method unless those variables are `final` or "effectively final"

An example can be found here which multiplies two numbers: [Outer.java](LocalInnerClass/Outer.java)
```
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
```
Note that the local variable `width` is marked `final`. As of Java 8, as long as the word final could be added, i.e. the variable is not changed within the method, then the code will compile.

Which of the following are "effectively final"?:
```
public void isItFinal() {
  int one = 20;
  int two = one;
  two++;
  int three;
  if (one == 4) three = 3;
  else three = 4;
  class Inner {}
  four = 5
}
```

- `one` is effectively final
- `two` is not effectively final as it is changed
- `three` is effectively final as it is only assigned once
- `four` is not effectively final even though the second assignment happens after the inner class

