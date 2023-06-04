package test;

public class test31 {

  public static void main(String[] args) {

    int[] arr1 = new int[5];

    arr1[0] = 100;
    arr1[1] = 90;
    arr1[2] = 80;
    arr1[3] = 70;
    arr1[4] = 60;
    
    int[] arr2 = new int[]{100, 90, 80, 70, 60};

    int[] arr3 = {100, 90, 80, 70, 60};
    System.out.println(arr1[1]);
    System.out.println(arr2[2]);
    System.out.println(arr3[0]);
  }
}