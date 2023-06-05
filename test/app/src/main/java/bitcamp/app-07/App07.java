package bitcamp.app;

import java.util.Scanner;

public class App06 {
  public static void main(String[] args) {
    System.out.println("회원별 옷 사이즈 관리 시스템");
    System.out.println("----------------------------------");
    Scanner scanner = new Scanner(System.in);

    final int MAX_SIZE = 100;
    int userId = 1;
    int length = 0;

    int[] no = new int[MAX_SIZE];
    String[] name = new String[MAX_SIZE];
    int[] age = new int[MAX_SIZE];
    String[] top = new String[MAX_SIZE];
    String[] pants = new String[MAX_SIZE];
    String[] shoes = new String[MAX_SIZE];

    printTitle();

    