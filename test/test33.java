package test;


public class test33 {

  public static void main(String[] args) {

    byte b =100;
    short s = 32767;
    int i = 98765678;
    long l = 18_2345_3456_4567_5678L;

    float f;
    double d;

    f = b; // byte(1) ==> float(4). 값을 그대로 저장.
    System.out.println(f);

    f = s; // short(2) ==> float(4). 값을 그대로 저장. 
    System.out.println(f);

    f = i; // int(4) ==> float(4). 
    // 유효자릿수를 넘는 정수는 짤린다.
    System.out.println(f);

    f = l; // long(8) ==> float(4)
    // 유효자릿수를 넘는 정수는 짤린다.
    System.out.println(f);

    d = i; // int(4) ==> double(8)
    // 유효자릿수 범위의 정수 값이므로 int(32비트) 값을 그대로 저장할 수 있다.
    System.out.println(d);       

    d = l; 
    // 유효 범위를 넘어가는 정수인 경우 짤린다.
    // 주의! 컴파일 오류가 발생하지 않는다.
    System.out.println(d);     

  }
}