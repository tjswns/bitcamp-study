package bitcamp.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bitcamp.myapp.vo.Acc;

public class MySQLAccDao implements AccDao {

  Connection con;
  int category;

  public MySQLAccDao(Connection con, int category) {
    this.con = con;
    this.category = category;
  }

  @Override
  public void insert(Acc acc) {
    try (PreparedStatement stmt =
        con.prepareStatement("insert into myapp_acc(style,size,select,password,category)"
            + " values(?,?,?,sha1(?),?)")) {

      stmt.setString(1, acc.getStyle());
      stmt.setString(2, acc.getSize());
      stmt.setString(3, acc.getChoose());
      stmt.setString(4, acc.getPassword());
      stmt.setInt(5, this.category);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<Acc> list() {
    try (PreparedStatement stmt =
        con.prepareStatement("select myapp_acc, style, size, select, view_count, created_date"
            + " from myapp_acc" + " where category=?" + " order by acc_no desc")) {

      stmt.setInt(1, this.category);

      try (ResultSet rs = stmt.executeQuery()) {
        List<Acc> list = new ArrayList<>();
        while (rs.next()) {
          Acc acc = new Acc();
          acc.setNo(rs.getInt("acc_no"));
          acc.setStyle(rs.getString("style"));
          acc.setSize(rs.getString("size"));
          acc.setChoose(rs.getString("select"));
          acc.setViewCount(rs.getInt("view_count"));
          acc.setCreatedDate(rs.getTimestamp("created_date"));
          list.add(acc);
        }
        return list;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Acc findBy(int no) {
    try (Statement stmt = con.createStatement();
        ResultSet rs =
            stmt.executeQuery("select acc_no, style, size, select, view_count, created_date"
                + " from myapp_acc" + " where category=" + this.category + " and acc_no=" + no
                + " order by acc_no desc")) {

      if (rs.next()) {
        Acc acc = new Acc();
        acc.setNo(rs.getInt("acc_no"));
        acc.setStyle(rs.getString("style"));
        acc.setSize(rs.getString("size"));
        acc.setChoose(rs.getString("select"));
        acc.setViewCount(rs.getInt("view_count"));
        acc.setCreatedDate(rs.getTimestamp("created_date"));

        stmt.executeUpdate(
            "update myapp_acc set" + " view_count=view_count + 1" + " where acc_no=" + no);

        return acc;
      }

      return null;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Acc acc) {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(String.format(
          "update myapp_acc set" + " style='%s'," + " size='%s'" + " select='%s'"
              + " where category=%d and board_no=%d and password='%s'",
          acc.getStyle(), acc.getSize(), acc.getChoose(), this.category, acc.getNo(),
          acc.getPassword()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(Acc acc) {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(
          String.format("delete from myapp_acc where category=%d and acc_no=%d and password='%s'",
              this.category, acc.getNo(), acc.getPassword()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
