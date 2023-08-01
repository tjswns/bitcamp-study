package bitcamp.myapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bitcamp.myapp.vo.Member;

public class MySQLMemberDao implements MemberDao {

  Connection con;

  public MySQLMemberDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Member member) {
    try (Statement stmt = con.createStatement()) {

      stmt.executeUpdate(String.format(
          "insert into myapp_member(name,email,password,age,gender,top,pants,shoes) values('%s','%s','%s','%s','%c','%s','%s','%s')",
          member.getName(), member.getEmail(), member.getPassword(), member.getAge(),
          member.getGender(), member.getTop(), member.getPants(), member.getShoes()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Member> list() {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select member_no, name, email, age, gender, top, pants, shoes from myapp_member order by name asc")) {

      List<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member m = new Member();
        m.setNo(rs.getInt("member_no"));
        m.setName(rs.getString("name"));
        m.setEmail(rs.getString("email"));
        m.setAge(rs.getString("age"));
        m.setGender(rs.getString("gender").charAt(0));
        m.setTop(rs.getString("top"));
        m.setPants(rs.getString("pants"));
        m.setShoes(rs.getString("shoes"));
        list.add(m);
      }

      return list;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Member findBy(int no) {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select member_no, name, email, age, gender, top, pants, shoes from myapp_member where member_no="
                + no)) {

      if (rs.next()) {
        Member m = new Member();
        m.setNo(rs.getInt("member_no"));
        m.setName(rs.getString("name"));
        m.setEmail(rs.getString("email"));
        m.setGender(rs.getString("gender").charAt(0));
        m.setTop(rs.getString("top"));
        m.setPants(rs.getString("pants"));
        m.setShoes(rs.getString("shoes"));
        return m;
      }

      return null;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Member member) {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(String.format(
          "update myapp_member set" + " name='%s'," + " email='%s'," + " password='%s',"
              + " age='%s'," + " gender='%c'" + " top='%s'," + " pants='%s'," + " shoes='%s',"
              + " where member_no=%d",
          member.getName(), member.getEmail(), member.getPassword(), member.getAge(),
          member.getGender(), member.getTop(), member.getPants(), member.getShoes(),
          member.getNo()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(String.format("delete from myapp_member where member_no=%d", no));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
