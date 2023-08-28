package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class UserData implements Serializable {
  private static final long serialVersionUID = 1L;

  // Bank 테이블과 관련된 필드
  private int bno;
  private String bankName;

  // Member (User Data) 테이블과 관련된 필드
  private int udno;
  private String name;
  private String email;
  private String password;
  private char gender;
  private Date createdDate;
  private String photo;

  // 회원사이즈와 관련된 필드
  private int sizeNo;
  private String size;

  @Override
  public int hashCode() {
    return Objects.hash(bno, udno);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserData other = (UserData) obj;
    return bno == other.bno && udno == other.udno;
  }

  public int getBno() {
    return bno;
  }

  public void setBno(int bno) {
    this.bno = bno;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public int getUdno() {
    return udno;
  }

  public void setUdno(int udno) {
    this.udno = udno;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public char getGender() {
    return gender;
  }

  public void setGender(char gender) {
    this.gender = gender;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public int getSizeNo() {
    return sizeNo;
  }

  public void setSizeNo(int sizeNo) {
    this.sizeNo = sizeNo;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }
}