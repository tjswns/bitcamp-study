package test;

public class test555 {
  public static void main(String[] args) {

    int a = 100, b = 200;

    swap(a, b);
    System.out.printf("%d, %d\n", a, b);
  }
    static void swap(int a, int b) {
      int temp = a;
      a = b;
      b = temp;
      System.out.printf("swap(): %d, %d\n", a, b);

    }
  
}
