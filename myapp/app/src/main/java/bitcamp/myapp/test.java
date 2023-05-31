package bitcamp.myapp;

public class test {

  public static void main(String[] args) {
    int[] no = new int[5];

    int[] no2;
    no2 = no;

    
    no2[2] = 31;

    System.out.println(no[2]);
  }

}