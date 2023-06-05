package bitcamp.app;

import java.util.Scanner;

public class App05 {
  public static void main(String[] args) {
    System.out.println("회원별 옷 사이즈 관리 시스템");
    System.out.println("----------------------------------");
    Scanner scanner = new Scanner(System.in);

    final int SIZE = 100;

    int[] no = new int[SIZE];
    String[] name = new String[SIZE];
    int[] age = new int[SIZE];
    String[] top = new String[SIZE];
    int[] pants = new int[SIZE];
    int[] shoes = new int[SIZE];

    for (int i = 0; i < SIZE; i++) {
      System.out.print("번호? ");
      no[i] = scanner.nextInt();

      System.out.print("이름? ");
      name[i] = scanner.next();

      System.out.print("나이? ");
      age[i] = scanner.nextInt();

      System.out.print("상의? ");
      top[i] = scanner.next();

      System.out.print("바지? ");
      pants[i] = scanner.nextInt();

      System.out.print("신발? ");
      shoes[i] = scanner.nextInt();   
  }
  System.out.println("---------------------------------------");

    for (int i = 0; i < SIZE; i++) {
      System.out.printf("번호: %d\n", no[i]);
      System.out.printf("이름: %s\n", name[i]);
      System.out.printf("나이: %d\n", age[i]);
      System.out.printf("상의: %s\n", top[i]);
      System.out.printf("바지: %s\n", pants[i]);
      System.out.printf("신발: %s\n", shoes[i]);
    }
    scanner.close();
  }
}