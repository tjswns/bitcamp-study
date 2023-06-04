package test;

class MyClass {

  int a = 11;
  static int b = 22;

  static void m1(int v1) {
    int v2;
    v2 = 200;

    System.out.println(v1);
    System.out.println(v2);
  }
}

public class test37 {

  public static void main(String[] args) {

    MyClass obj1 = new MyClass();
    System.out.println(obj1.a);

    System.out.println(MyClass.b);

    MyClass.m1(100);
  }
}