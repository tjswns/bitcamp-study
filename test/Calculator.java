package test;

class Calculator {
static int result; // 스테틱 변수는 기본 값으로 0으로 초기화된다.
public static void main(String[] args) {
  Calculator.init(2);
  Calculator.multiple(3);
  Calculator.plus(7);
  Calculator.minus(2);
  Calculator.divide(2);
System.out.println(Calculator.result);
}
}