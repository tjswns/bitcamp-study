package bitcamp.app12.util4;

import java.util.Scanner;

public class Prompt02 {

  static Scanner scanner = new Scanner(System.in);

  public static String inputString(String title) {
    System.out.print(title);
    return scanner.nextLine();
  }

  public static int inputInt(String title) {
    return Integer.parseInt(inputString(title));
  }

  public static void close() {
    scanner.close();
  }

}
