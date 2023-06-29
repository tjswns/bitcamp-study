package bitcamp.app04;

import java.util.Scanner;

public class App04 {
  public static void main(String[] args) {
    System.out.println("회원별 옷 사이즈 관리 시스템");
    System.out.println("----------------------------------");

    Scanner scanner = new Scanner(System.in);

    System.out.print("번호? ");
    int no = scanner.nextInt();

    System.out.print("이름? ");
    String name = scanner.next();

    System.out.print("나이? ");
    int age = scanner.nextInt();

    System.out.print("상의? ");
    String top = scanner.next();

    System.out.print("바지? ");
    int pants = scanner.nextInt();

    System.out.print("신발? ");
    int shoes = scanner.nextInt();

    System.out.println("---------------------------------------");

    System.out.printf("번호: %d\n", no);
    System.out.printf("이름: %s\n", name);
    System.out.printf("나이: %d\n", age);
    System.out.printf("상의: %s\n", top);
    System.out.printf("하의: %d\n", pants);
    System.out.printf("신발: %d\n", shoes);

    scanner.close();
  }
}