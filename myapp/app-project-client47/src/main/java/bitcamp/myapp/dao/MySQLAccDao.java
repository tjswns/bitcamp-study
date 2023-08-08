package bitcamp.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        con.prepareStatement("insert into myapp_acc(style,choose,size,password,category)"
            + " values(?,?,?,sha1(?),?)")) {

      stmt.setString(1, acc.getStyle());
      stmt.setString(3, acc.getChoose());
      stmt.setString(2, acc.getSize());
      stmt.setString(4, acc.getPassword());
      stmt.setInt(5, this.category);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<Acc> list() {
    try (PreparedStatement stmt =
        con.prepareStatement("select acc_no, style, choose, size, view_count, created_date"
            + " from myapp_acc" + " where category=?" + " order by acc_no desc")) {

      stmt.setInt(1, this.category);

      try (ResultSet rs = stmt.executeQuery()) {
        List<Acc> list = new ArrayList<>();
        while (rs.next()) {
          Acc acc = new Acc();
          acc.setNo(rs.getInt("acc_no"));
          acc.setStyle(rs.getString("style"));
          acc.setChoose(rs.getString("choose"));
          acc.setSize(rs.getString("size"));
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
    try (PreparedStatement stmt = con.prepareStatement(
        "select acc_no, style, choose, size, view_count, created_date" + " from myapp_acc"
            + " where category=?" + " and acc_no=?" + " order by acc_no desc")) {

      stmt.setInt(1, this.category);
      stmt.setInt(2, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Acc acc = new Acc();
          acc.setNo(rs.getInt("acc_no"));
          acc.setStyle(rs.getString("style"));
          acc.setChoose(rs.getString("choose"));
          acc.setSize(rs.getString("size"));
          acc.setViewCount(rs.getInt("view_count"));
          acc.setCreatedDate(rs.getTimestamp("created_date"));

          stmt.executeUpdate(
              "update myapp_acc set" + " view_count=view_count + 1" + " where acc_no=" + no);

          return acc;
        }

        return null;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Acc acc) {
    try (PreparedStatement stmt = con.prepareStatement("update myapp_acc set" + " style=?,"
        + " choose=?" + " size=?" + " where category=? and board_no=? and password=sha1(?)")) {
      stmt.setString(1, acc.getStyle());
      stmt.setString(3, acc.getChoose());
      stmt.setString(2, acc.getSize());
      stmt.setInt(4, this.category);
      stmt.setInt(5, acc.getNo());
      stmt.setString(6, acc.getPassword());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(Acc acc) {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from myapp_acc" + " where category=? and acc_no=? and password=sha1(?)")) {

      stmt.setInt(1, this.category);
      stmt.setInt(2, acc.getNo());
      stmt.setString(3, acc.getPassword());

      return stmt.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}