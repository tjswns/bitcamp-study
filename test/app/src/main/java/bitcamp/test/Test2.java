package bitcamp.test;

import bitcamp.myapp.vo.Member;

public class Test2 {
  public static void main(String[] args) {
    Member m1 = new Member();
    m1.setNo(1);
    m1.setName("홍길동");
    m1.setEmail("hong@test.com");
    m1.setPassword("111");
    m1.setGender('W');

    Member m2 = new Member();
    m2.setNo(1);
    m2.setName("유관순");
    m2.setEmail("yoo@test.com");
    m2.setPassword("222");
    m2.setGender('W');

    System.out.println(m1 == m2);
    System.out.println(m1.equals(m2));
    // equals()는 슈퍼클래스 Object의 메서드이다.
    // Object의 equals()는 두인스턴스가 같은지 비교한다.


    Object obj1 = m1;
    Object obj2 = m2;

    System.out.println(obj1.equals(obj2));
  }
}
