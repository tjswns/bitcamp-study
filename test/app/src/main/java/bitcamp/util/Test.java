package bitcamp.util;

public class Test {
  public static void main(String[] args) {
    ArrayList<String> names = new ArrayList<>();
    names.add(new String("홍길동"));
    names.add("임꺽정");
    names.add("유관순");
    names.add("안중근");

    // Object[] arr = names.toArray();
    String[] arr = names.toArray(new String[names.size()]);

    for (String item : arr) {
      System.out.println(item);
    }
  }
}
