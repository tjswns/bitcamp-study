// 메서드 chaining call
package com.eomcs.openapi.json.gson;

import com.eomcs.lang.reflect.Type;

public class Exam114 {
  public static void main(String[] args) {

    class Member{
    int no;
    String name;
    String email;
    String password;
    boolean working;
    // 1) 객체 준비
    Member m = new Member();
    public int getNo() {return no;}
    public void setNo(int no) {this.no = no;}
    public String getName() {return name;}
    public void setName(String name) {this.name =name;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email =email;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password =password;}
    public boolean isWorking() {return working;}
    public void setWorking(boolean working) {this.working = working;}

    // 2) JSON 처리 객체 준비
    Gson gson = new Gson();

    // 3) 객체의 값을 JSON 문자열로 얻기
    String jsonStr = gson.toJson(m);

    System.out.println(jsonStr);
  }
}

// JSON 객체 형식 - { 객체 정보 }
// => { "프로퍼티명" : 값, "프로퍼티명": 값, ...}
//
// 값:
// - 문자열 => "값"
// - 숫자 => 값
// - 논리 => true, false
//
// 프로퍼티명은 반드시 문자열로 표현해야 한다.


