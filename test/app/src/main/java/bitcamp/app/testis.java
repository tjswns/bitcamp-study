package bitcamp.app;

// 소스 코드에서 Calculator 클래스는 test 패키지에 소속된 클래스를 가리킨다

import bitcamp.util.Calculator;

public class testis {

public static void main(String[] args) {

  Calculator.init(2);
  Calculator.multiple(3);
  Calculator.plus(7);
  Calculator.minus(2);
  Calculator.divide(2);
System.out.println(Calculator.result);
  }

}