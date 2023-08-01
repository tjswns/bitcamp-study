package bitcamp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

// - DB 커넥션을 관리하고 제공하는 역할
// - 풀링 기법을 이용하여 스레드 재활용
// -GOF의 FLyweight 패턴


public class DataSource {

  // DBMS에 연결할 때 사용할
  String jdbcUrl;
  String username;
  String password;

  // 커넥션 목록을 유지하기 위한 객체 : 커넥션 풀
  List<Connection> connectionPool = new ArrayList<>();


  // 현재 스레드에 값을 넣고 꺼내는 일을 하는객체
  ThreadLocal<Connection> connectionBox = new ThreadLocal<>();

  public DataSource(String jdbcUrl, String username, String password) {

    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  public Connection getConnection(boolean autoCommit) throws Exception {
    Connection con = this.getConnection();
    con.setAutoCommit(autoCommit);
    return con;
  }

  public Connection getConnection() throws Exception {
    // 현재 스레ㅔ드 보관소에 저장된 객체 꺼냄
    Connection con = connectionBox.get();

    if (con == null) {
      // 현재 스레드에 장착된 DB 커넥션 객체가 없다면,

      if (connectionPool.size() > 0) {
        // 커넥션풀에 사용할 수 있는 객체가 있다면, 꺼낸다.
        con = connectionPool.remove(0);
        System.out.printf("[%s] - DB 커넥션풀에서 꺼냄!\n", Thread.currentThread().getName());
      } else {
        // 커넥션풀에 사용할 수 있는 객체가 없다면, 꺼낸다.
        con = DriverManager.getConnection(jdbcUrl, username, password);
        con.setAutoCommit(true);
        System.out.printf("[%s] - 새 DB 커넥션 생성!\n", Thread.currentThread().getName());
      }
      // connectionBox를 통해 새로 생성한 객체를 현재 스레드에 보관한다.
      connectionBox.set(con);
      System.out.printf("[%s] - 스레드에 커넥션 객체 보관!\n", Thread.currentThread().getName());
    } else {
      System.out.printf("[%s] - 스레드에 보관된 커넥션 사용!\n", Thread.currentThread().getName());

    }
    return con;
  }

  public void clean() {
    // 스레드가 작업을 끝냈으면, 이 스레드에 보관된 Connection 객체를 제거한다.
    Connection con = connectionBox.get();
    if (con != null) {
      // 스레드가 사용한 DB 커넥션을 다른 스레드에서 사용할 수 있도록 커넥션풀에 저장한다.
      connectionPool.add(con);
      System.out.printf("[%s] - 커넥션 저장!\n", Thread.currentThread().getName());
      connectionBox.remove();
      System.out.printf("[%s] - 커넥션 제거!\n", Thread.currentThread().getName());
    }
  }

}
