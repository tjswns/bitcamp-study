package bitcamp.myapp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import bitcamp.myapp.vo.Acc;
import bitcamp.myapp.vo.Member;
import bitcamp.util.DataSource;

public class MySQLAccDao implements AccDao {

  DataSource ds;
  int category;

  public MySQLAccDao(DataSource ds, int category) {
    this.ds = ds;
    this.category = category;
  }

  @Override
  public void insert(Acc acc) {
    try (PreparedStatement stmt = ds.getConnection(false)
        .prepareStatement("insert into myapp_acc(style, choose, size, writer, password, category)"
            + " values(?,?,?,?,sha1(?),?)")) {

      stmt.setString(1, acc.getStyle());
      stmt.setString(2, acc.getChoose());
      stmt.setString(3, acc.getSize());
      stmt.setInt(4, acc.getWriter().getNo());
      stmt.setString(5, acc.getPassword());
      stmt.setInt(6, this.category);

      stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<Acc> list() {
    try (PreparedStatement stmt = ds.getConnection(false).prepareStatement(
        "select acc.acc_no, acc.style, acc.choose, acc.size, acc.view_count, acc.created_date,"
            + "m.member_no, m.name"
            + " from myapp_acc acc inner join myapp_member m on acc.writer=m.member_no"
            + " where category=?" + " order by acc_no desc")) {

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

          Member writer = new Member();
          writer.setNo(rs.getInt("member_no"));
          writer.setName(rs.getString("name"));
          acc.setWriter(writer);

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
    try (PreparedStatement stmt = ds.getConnection(false).prepareStatement(
        "select acc.acc_no, acc.style, acc.choose, acc.size, acc.view_count, acc.created_date,"
            + "m.member_no, m.name"
            + " from myapp_acc acc inner join myapp_member m on acc.writer=m.member_no"
            + " where category=?" + " and acc_no=?")) {

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

          Member writer = new Member();
          writer.setNo(rs.getInt("member_no"));
          writer.setName(rs.getString("name"));
          acc.setWriter(writer);

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
    try (PreparedStatement stmt =
        ds.getConnection(false).prepareStatement("update myapp_acc set" + " style=?," + " choose=?"
            + " 'size'=?" + " where category=? and acc_no=? and writer=?")) {
      stmt.setString(1, acc.getStyle());
      stmt.setString(2, acc.getChoose());
      stmt.setString(3, acc.getSize());
      stmt.setInt(4, this.category);
      stmt.setInt(5, acc.getNo());
      stmt.setInt(6, acc.getWriter().getNo());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(Acc acc) {
    try (PreparedStatement stmt = ds.getConnection(false).prepareStatement(
        "delete from myapp_acc" + " where category=? and acc_no=? and writer=?")) {

      stmt.setInt(1, this.category);
      stmt.setInt(2, acc.getNo());
      stmt.setInt(3, acc.getWriter().getNo());

      return stmt.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
