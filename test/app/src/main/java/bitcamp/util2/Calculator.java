package bitcamp.util2;

public class Calculator {

  public static int result; // 스테틱 변수는 기본 값으로 0으로 초기화된다.

  public static void init(int a) {
    result = a;
  }

  public static void plus(int a) {
    result += a;
  }

  public static void multiple(int a) {
    result -= a;
  }

  public static void minus(int a) {
    result *= a;
  }

  public static void divide(int a) {
    result /= a;
  }
}
