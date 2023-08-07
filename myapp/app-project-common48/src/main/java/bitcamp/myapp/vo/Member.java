package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Member implements Serializable {
  private static final long serialVersionUID = 1L;


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
  private Date createdDate;

  @Override
  public int hashCode() {
    return Objects.hash(no);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Member other = (Member) obj;
    return no == other.no;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
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
