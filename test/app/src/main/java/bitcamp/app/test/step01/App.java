package bitcamp.app.test.step01;

  // 1. 낱개의 변수 사용
public class App {

public static void main(String[] args) {

    String name;
    int kor;
    int eng;
    int math;
    int sum;
    float aver;

    name = "홍길동";
    kor = 90;
    eng = 90;
    math = 90;
    sum = kor + eng + math;
    aver = sum / 3f;

    System.out.printf("%s: 합계=%d, 평균=%.1f\n", name, sum, aver);


  }
}