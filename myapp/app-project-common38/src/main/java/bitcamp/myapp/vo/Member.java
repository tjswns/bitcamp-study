package bitcamp.myapp.vo;

import java.io.Serializable;

public class Member implements Serializable, CsvObject, AutoIncrement {
  private static final long serialVersionUID = 1L;
  public static int userId = 1;


  public static final char MALE = 'M';
  public static final char FEMALE = 'W';

  public static final String M = "M";
  public static final String L = "L";
  public static final String XL = "XL";

  private int no;
  private String name;
  private String email;
  private String password;
  private String age;
  private char gender;
  private String top;
  private String pants;
  private String skirt;
  private String shoes;

  public Member() {}

  // 같은 기능을 수행하는 생성자가 위에 있다.
  // 다만 파라미터가 다를 뿐이다.
  // => "생성자 오버로딩(overloading)"
  public Member(int no) {
    this.no = no;
  }

  public static Member fromCsv(String csv) {

    String[] values = csv.split(",");
    Member member = new Member(Integer.parseInt(values[0]));
    member.setName(values[1]);
    member.setEmail(values[2]);
    member.setPassword(values[3]);
    member.setAge(values[4]);
    member.setGender(values[5].charAt(0));
    member.setTop(values[6]);
    member.setPants(values[7]);
    member.setShoes(values[8]);
    if (Member.userId <= member.getNo()) {
      Member.userId = member.getNo() + 1;
    }
    return member;
  }

  @Override
  public String toCsvString() {

    return String.format("%d,%s,%s,%s,%s,%c,%s,%s,%s\n", this.getNo(), this.getName(),
        this.getEmail(), this.getPassword(), this.getAge(), this.getGender(), this.getTop(),
        this.getPants(), this.getShoes());
  }

  @Override
  public void updateKey() {
    if (Member.userId <= this.no) {
      Member.userId = this.no + 1;
    }
  }

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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
