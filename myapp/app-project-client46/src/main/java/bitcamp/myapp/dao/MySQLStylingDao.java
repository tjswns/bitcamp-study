package bitcamp.myapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    try (Statement stmt = con.createStatement()) {

      stmt.executeUpdate(String.format(
          "insert into myapp_styling(style,brand,fit,password,category)"
              + " values('%s','%s','%s','%s',%d)",
          styling.getStyle(), styling.getBrand(), styling.getFit(), styling.getPassword(),
          this.category));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<Styling> list() {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select styling_no, style, brand, fit, view_count, created_date" + " from myapp_styling"
                + " where category=" + this.category + " order by styling_no desc")) {

      List<Styling> list = new ArrayList<>();

      while (rs.next()) {
        Styling styling = new Styling();
        styling.setNo(rs.getInt("acc_no"));
        styling.setStyle(rs.getString("style"));
        styling.setBrand(rs.getString("brand"));
        styling.setFit(rs.getString("fit"));
        styling.setViewCount(rs.getInt("view_count"));
        styling.setCreatedDate(rs.getTimestamp("created_date"));

        list.add(styling);
      }

      return list;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Styling findBy(int no) {
    try (Statement stmt = con.createStatement();
        ResultSet rs =
            stmt.executeQuery("select styling_no, style, brand, fit, view_count, created_date"
                + " from myapp_styling" + " where category=" + this.category + " and styling_no="
                + no + " order by styling_no desc")) {

      if (rs.next()) {
        Styling styling = new Styling();
        styling.setNo(rs.getInt("styling_no"));
        styling.setStyle(rs.getString("style"));
        styling.setBrand(rs.getString("brand"));
        styling.setFit(rs.getString("fit"));
        styling.setViewCount(rs.getInt("view_count"));
        styling.setCreatedDate(rs.getTimestamp("created_date"));

        stmt.executeUpdate(
            "update myapp_styling set" + " view_count=view_count + 1" + " where styling_no=" + no);

        return styling;
      }

      return null;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Styling styling) {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(String.format(
          "update myapp_styling set" + " style='%s'," + " brand='%s'" + " fit='%s'"
              + " where category=%d and styling_no=%d and password='%s'",
          styling.getStyle(), styling.getBrand(), styling.getFit(), this.category, styling.getNo(),
          styling.getPassword()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(Styling styling) {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(String.format(
          "delete from myapp_styling where category=%d and styling_no=%d and password='%s'",
          this.category, styling.getNo(), styling.getPassword()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
