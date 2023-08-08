package bitcamp.myapp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import bitcamp.myapp.vo.Member;
import bitcamp.util.DataSource;

public class MySQLMemberDao implements MemberDao {

  DataSource ds;

  public MySQLMemberDao(DataSource ds) {
    this.ds = ds;
  }

  @Override
  public void insert(Member member) {
    try (PreparedStatement stmt = ds.getConnection(false).prepareStatement(
        "insert into myapp_member(name,email,password,age,gender,top,pants,shoes) values(?,?,sha1(?),?,?,?,?,?)")) {

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, member.getAge());
      stmt.setString(5, String.valueOf(member.getGender()));
      stmt.setString(6, member.getTop());
      stmt.setString(7, member.getPants());
      stmt.setString(8, member.getShoes());

      stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Member> list() {
    try (PreparedStatement stmt = ds.getConnection(false).prepareStatement(
        "select member_no, name, email, age, gender, top, pants, shoes from myapp_member order by name asc");

        ResultSet rs = stmt.executeQuery()) {

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
    try (PreparedStatement stmt = ds.getConnection(false).prepareStatement(
        "select member_no, name, email, age, gender, top, pants, shoes, created_date from myapp_member where member_no=?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Member m = new Member();
          m.setNo(rs.getInt("member_no"));
          m.setName(rs.getString("name"));
          m.setEmail(rs.getString("email"));
          m.setAge(rs.getString("age"));
          m.setGender(rs.getString("gender").charAt(0));
          m.setTop(rs.getString("top"));
          m.setPants(rs.getString("pants"));
          m.setShoes(rs.getString("shoes"));
          m.setCreatedDate(rs.getDate("created_date"));
          return m;
        }

        return null;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Member findByEmailAndPassword(Member param) {
    try (PreparedStatement stmt = ds.getConnection(false).prepareStatement(
        "select member_no, name, email, age, gender, top, pants, shoes, created_date from myapp_member where email=? and password=sha1(?)")) {

      stmt.setString(1, param.getEmail());
      stmt.setString(2, param.getPassword());

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Member m = new Member();
          m.setNo(rs.getInt("member_no"));
          m.setName(rs.getString("name"));
          m.setEmail(rs.getString("email"));
          m.setAge(rs.getString("age"));
          m.setGender(rs.getString("gender").charAt(0));
          m.setTop(rs.getString("top"));
          m.setPants(rs.getString("pants"));
          m.setShoes(rs.getString("shoes"));
          m.setCreatedDate(rs.getDate("created_date"));
          return m;
        }
        return null;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Member member) {
    try (
        PreparedStatement stmt = ds.getConnection(false)
            .prepareStatement("update myapp_member set" + " name=?," + " email=?,"
                + " password=sha1(?)," + " age=?," + " gender=?," + " top=?," + " pants=?,"
                + " shoes=?" + " where member_no=?")) {
      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, member.getAge());
      stmt.setString(5, String.valueOf(member.getGender()));
      stmt.setString(6, member.getTop());
      stmt.setString(7, member.getPants());
      stmt.setString(8, member.getShoes());
      stmt.setInt(9, member.getNo());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (PreparedStatement stmt = ds.getConnection(false).prepareStatement(

        "delete from myapp_member where member_no=?")) {
      stmt.setInt(1, no);

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
