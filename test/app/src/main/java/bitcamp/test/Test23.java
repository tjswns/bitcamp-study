package bitcamp.test;

import bitcamp.test.p1.A;

public class Test23 extends A {
  public static void main(String[] args) {
    A obj = new A();

    // obj.v3 = 300; // 접근 불가! <== 상속 받은 멤버가 아니다.

    obj.v4 = 400;
    // obj.m(); // 접근 불가! <== 상속 받은 멤버가 아니다!

    m2();

    Test23 obj2 = new Test23();
    obj2.m3();
    // obj2.v1 = 100; // 접근 불가!
    // obj2.v2 = 200; // 접근 불가!
    obj2.v3 = 300; // 상속 받아서 만든 필드 자식 클래스가 접근 가능!
    obj2.v4 = 400;
    obj2.m(); // 자식 클래스가 상속 받아서 사용하는 멤버!
  }

  static void m2() {

  }

  void m3() {

  }
}
