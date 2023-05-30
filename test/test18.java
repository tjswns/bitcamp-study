package test;

public class test18 {

  public static void main(String[] args) {
    int i = 2;
    int result = i++ + i++ * i++;

    System.out.printf("%d, %d\n", i, result);
  }
}