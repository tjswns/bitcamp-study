package bitcamp.myapp;

class Calculator {

public class test {

  static int result; // 스테틱 변수는 기본 값으로 0으로 초기화된다.

  public static void main(String[] args) {
    // 2 * 3 + 7 - 2 / 2 = ?
    // => 연산자 우선 순위를 고려하지 않고 앞에서부터 뒤로 순차적으로 계산한다.
    
    init(2);
    multiple(3);
    plus(7);
    minus(2);
    divide(2);
    System.out.println(result);
  }

  static void init(int a) {
    return = a;
  }

  static void plus(int a) {
    return += a;
  }

  static void multiple(int a) {
    return -= a;
  }
    static void divide(int a) {
      return *= a;
    }

    static void divide(int a) {
      return /= a;
    }
  }
