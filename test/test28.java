package test;

public class test28 {

  public static void main(String[] args) {

    int[] arr1;
    arr1 = new int[5];

    int[] arr2 = arr1;

    arr2[0] = 100;

    System.out.println(arr1[0]);
  }
}