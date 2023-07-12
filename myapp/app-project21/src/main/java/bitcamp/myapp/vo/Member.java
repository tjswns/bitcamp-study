package bitcamp.myapp.vo;

public class Member {

  private static int userId = 1;


  public static final char MALE = 'M';
  public static final char FEMALE = 'W';

  public static final String M = "M";
  public static final String L = "L";
  public static final String XL = "XL";

  private int no;
  private String name;
  private String age;
  private char gender;
  private String top;
  private String pants;
  private String skirt;
  private String shoes;

  public Member() {
    this.no = userId++;
  }

  // 같은 기능을 수행하는 생성자가 위에 있다.
  // 다만 파라미터가 다를 뿐이다.
  // => "생성자 오버로딩(overloading)"
  public Member(int no) {
    this.no = no;
  }
  // Object의 equals()는 Member 인스턴스를 비교하는데 적합하지 않다.
  // 왜? Object의 equals()는 단순히 인스턴스 주소가 같은지 비교하기 때문이다.
  // 우리가 원하는 것은 인스턴스 주소가 다르더라도
  // 두 인스턴스 안에 저장된 변수들의 값이 같다면
  // 두 인스턴스는 같은 것으로 처리하는 것이다.
  // 그렇게 하기 위해 수퍼 클래스의 equals()를 재정의 한다.
  // => 이것을 "오버라이딩(overriding)"이라 부른다.

  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (this.getClass() != obj.getClass()) {
      return false;
    }

    Member m = (Member) obj;

    if (this.getNo() != m.getNo()) {
      return false;
    }
    return true;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public char getGender() {
    return gender;
  }

  public void setGender(char gender) {
    this.gender = gender;
  }

  public String getTop() {
    return top;
  }

  public void setTop(String top) {
    this.top = top;
  }

  public String getPants() {
    return pants;
  }

  public void setPants(String pants) {
    this.pants = pants;
  }

  public String getSkirt() {
    return skirt;
  }

  public void setSkirt(String skirt) {
    this.skirt = skirt;
  }

  public String getShoes() {
    return shoes;
  }

  public void setShoes(String shoes) {
    this.shoes = shoes;
  }

  public static char getMale() {
    return MALE;
  }

  public static char getFemale() {
    return FEMALE;
  }


}
