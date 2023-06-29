package bitcamp.app03;

public class App03 {
  public static void main(String[] args) {
    System.out.println("회원별 옷 사이즈 관리 시스템");
    System.out.println("----------------------------------");

    int no = 100;
    String name = "김선준";
    int age = 27;
    String top = "XL";
    String pants = "30";
    String shoes = "270";

    System.out.printf("번호: %d\n", no);
    System.out.printf("이름: %s\n", name);
    System.out.printf("나이: %d\n", age);
    System.out.printf("상의: %s\n", top);
    System.out.printf("하의: %s\n", pants);
    System.out.printf("신발: %s\n", shoes);
  }
}