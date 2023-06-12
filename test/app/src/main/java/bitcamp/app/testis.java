package bitcamp.app;

import bitcamp.util.Calculator01;

public class testis {

public static void main(String[] args) {
  // 2 * 3 + 7 - 2 / 2 = ?
  // 3 - 1 * 7 + 15 / 3 = ?
  // => 위의 계산을 동시에 수행하기

  // 두 개의 계산 결과를 저장할 수 있는 result 변수를 준비한다.
  Calculator01 c1 = new  Calculator01();
  Calculator01 c2 = new  Calculator01();

  c1.init(2);
  c2.init(3);
  c1.multiple(3);
  c2.minus(1);
  c1.plus(7);
  c2.multiple(7);
  c1.minus(2);
  c2.plus(15);
  c1.divide(2);
  c2.divide(3);


  System.out.println(c1.getResult());
  System.out.println(c2.getResult());

  }
}