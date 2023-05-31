package test;

public class test41 {

  public static void main(String[] args) {

    int a = 100;

    switch (a) {
      case 1:

      int b;
      {

        int c;
      }
      break;
      case 2:

      int c;

      break;

      case 3:
        for (int i = 0; i < 100; i++) {
          // for의 바깥 블럭인 switch 문에 이미 b 변수가 있다. 
          //          int b; // 컴파일 오류!

        }
        break;
        default:
    }
  }
}