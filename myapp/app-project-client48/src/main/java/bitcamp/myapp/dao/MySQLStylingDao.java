package bitcamp.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.Styling;

public class MySQLStylingDao implements StylingDao {

  Connection con;
  int category;

  public MySQLStylingDao(Connection con, int category) {
    this.con = con;
    this.category = category;
  }

  @Override
  public void insert(Styling styling) {
    try (PreparedStatement stmt = con
        .prepareStatement("insert into myapp_styling(style, brand, fit, writer, password, category)"
            + " values(?,?,?,sha(?),?)")) {

      stmt.setString(1, styling.getStyle());
      stmt.setString(2, styling.getBrand());
      stmt.setString(3, styling.getFit());
      stmt.setInt(4, styling.getWriter().getNo());
      stmt.setString(5, styling.getPassword());
      stmt.setInt(6, this.category);

      stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<Styling> list() {
    try (PreparedStatement stmt = con.prepareStatement(
        "select styling.styling_no, styling.style, styling.brand, styling.fit, styling.view_count, styling.created_date,"
            + "m.member_no, m.name"
            + " from myapp_styling styling inner join myapp_member m on styling.writer=m.member_no"
            + " where category=?" + " order by styling_no desc")) {

      stmt.setInt(1, this.category);

      try (ResultSet rs = stmt.executeQuery()) {
        List<Styling> list = new ArrayList<>();
        while (rs.next()) {
          Styling styling = new Styling();
          styling.setNo(rs.getInt("styling_no"));
          styling.setStyle(rs.getString("style"));
          styling.setBrand(rs.getString("brand"));
          styling.setFit(rs.getString("fit"));
          styling.setViewCount(rs.getInt("view_count"));
          styling.setCreatedDate(rs.getTimestamp("created_date"));

          Member writer = new Member();
          writer.setNo(rs.getInt("member_no"));
          writer.setName(rs.getString("name"));
          styling.setWriter(writer);

          list.add(styling);
        }
        return list;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Styling findBy(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "select styling.styling_no, styling.style, styling.brand, styling.fit, styling.view_count, styling.created_date,"
            + "m.member_no, m.name"
            + " from myapp_styling styling inner join myapp_member m on styling.writer=m.member_no"
            + " where category=?" + " and styling_no=?")) {

      stmt.setInt(1, this.category);
      stmt.setInt(2, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Styling styling = new Styling();
          styling.setNo(rs.getInt("styling_no"));
          styling.setStyle(rs.getString("style"));
          styling.setBrand(rs.getString("brand"));
          styling.setFit(rs.getString("fit"));
          styling.setViewCount(rs.getInt("view_count"));
          styling.setCreatedDate(rs.getTimestamp("created_date"));

          Member writer = new Member();
          writer.setNo(rs.getInt("member_no"));
          writer.setName(rs.getString("name"));
          styling.setWriter(writer);

          stmt.executeUpdate("update myapp_styling set" + " view_count=view_count + 1"
              + " where styling_no=" + no);

          return styling;
        }

        return null;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Styling styling) {
    try (PreparedStatement stmt = con.prepareStatement("update myapp_styling set" + " style=?,"
        + " brand=?" + " fit=?'" + " where category=? and styling_no=? and password=sha1(?)")) {

      stmt.setString(1, styling.getStyle());
      stmt.setString(2, styling.getBrand());
      stmt.setString(3, styling.getFit());
      stmt.setInt(4, this.category);
      stmt.setInt(5, styling.getNo());
      stmt.setString(6, styling.getPassword());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(Styling styling) {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from myapp_styling where category=? and styling_no=? and password=sha1(?)")) {

      stmt.setInt(1, this.category);
      stmt.setInt(2, styling.getNo());
      stmt.setString(3, styling.getPassword());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
