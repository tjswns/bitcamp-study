package bitcamp.test;

class A {

  int v1;
  int v2;
  {
    System.out.println("111");
  }
  {
    System.out.println("222");
  }
  {
    System.out.println("333");
  }
}


public class Exam01 {

  public static void main(String[] args) {
    new A();
    new A();
    new A();
  }
}

