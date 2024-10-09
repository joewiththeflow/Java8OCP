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