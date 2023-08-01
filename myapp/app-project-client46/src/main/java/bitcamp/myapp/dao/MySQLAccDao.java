package bitcamp.myapp.dao;

import java.sql.Connection;
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
    try (Statement stmt = con.createStatement()) {

      stmt.executeUpdate(String.format(
          "insert into myapp_acc(style,size,select,password,category)"
              + " values('%s','%s','%s','%s',%d)",
          acc.getStyle(), acc.getSize(), acc.getSelect(), acc.getPassword(), this.category));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<Acc> list() {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select acc_no, style, size, select, view_count, created_date" + " from myapp_acc"
                + " where category=" + this.category + " order by acc_no desc")) {

      List<Acc> list = new ArrayList<>();

      while (rs.next()) {
        Acc acc = new Acc();
        acc.setNo(rs.getInt("acc_no"));
        acc.setStyle(rs.getString("style"));
        acc.setSize(rs.getString("size"));
        acc.setSelect(rs.getString("select"));
        acc.setViewCount(rs.getInt("view_count"));
        acc.setCreatedDate(rs.getTimestamp("created_date"));

        list.add(acc);
      }

      return list;

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
        acc.setSelect(rs.getString("select"));
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
          acc.getStyle(), acc.getSize(), acc.getSelect(), this.category, acc.getNo(),
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
