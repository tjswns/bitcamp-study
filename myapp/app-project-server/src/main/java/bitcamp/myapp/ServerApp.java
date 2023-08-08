package bitcamp.myapp;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.MySQLAccDao;
import bitcamp.myapp.dao.MySQLMemberDao;
import bitcamp.myapp.dao.MySQLStylingDao;
import bitcamp.myapp.dao.StylingDao;
import bitcamp.myapp.handler.AccAddListener;
import bitcamp.myapp.handler.AccDeleteListener;
import bitcamp.myapp.handler.AccDetailListener;
import bitcamp.myapp.handler.AccListListener;
import bitcamp.myapp.handler.AccUpdateListener;
import bitcamp.myapp.handler.LoginListener;
import bitcamp.myapp.handler.MemberAddListener;
import bitcamp.myapp.handler.MemberDeleteListener;
import bitcamp.myapp.handler.MemberDetailListener;
import bitcamp.myapp.handler.MemberListListener;
import bitcamp.myapp.handler.MemberUpdateListener;
import bitcamp.myapp.handler.StylingAddListener;
import bitcamp.myapp.handler.StylingDeleteListener;
import bitcamp.myapp.handler.StylingDetailListener;
import bitcamp.myapp.handler.StylingListListener;
import bitcamp.myapp.handler.StylingUpdateListener;
import bitcamp.net.NetProtocol;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;



public class ServerApp {

  // 자바 스레드풀 준비
  ExecutorService threadPool = Executors.newFixedThreadPool(2);

  DataSource ds = new DataSource("jdbc:mysql://localhost:3306/projectdb", "project", "1111");
  MemberDao memberDao;
  AccDao accDao;
  StylingDao stylingDao;

  MenuGroup mainMenu = new MenuGroup("메인");

  int port;

  public ServerApp(int port) throws Exception {
    this.port = port;

    this.memberDao = new MySQLMemberDao(ds);
    this.accDao = new MySQLAccDao(ds, 1);
    this.stylingDao = new MySQLStylingDao(ds, 2);

    prepareMenu();
  }


  public void close() throws Exception {

  }

  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp(8888);
    app.execute();
    app.close();
  }


  public void execute() {
    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
      System.out.println("서버 실행 중...");

      while (true) {
        Socket socket = serverSocket.accept();
        threadPool.execute(() -> processRequest(socket));
      }
    } catch (Exception e) {
      System.out.println("서버 실행 오류!");
      e.printStackTrace();
    }
  }

  private void processRequest(Socket socket) {
    try (Socket s = socket;
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

      BreadcrumbPrompt prompt = new BreadcrumbPrompt(in, out);

      InetSocketAddress clientAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
      System.out.printf("%s 클라이언트 접속함!\n", clientAddress.getHostString());

      out.writeUTF("[우리의 OOTD]\n" + "-----------------------------------------");

      new LoginListener(memberDao).service(prompt);

      mainMenu.execute(prompt);
      out.writeUTF(NetProtocol.NET_END);

    } catch (Exception e) {
      System.out.println("클라이언트 통신 오류!");
      e.printStackTrace();

    } finally {
      ds.clean(); // 현재 스레드에 보관된 Connection 객체를 닫고, 스레드에서 제거한다.
    }
  }

  private void prepareMenu() {
    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new Menu("등록", new MemberAddListener(memberDao, ds)));
    memberMenu.add(new Menu("목록", new MemberListListener(memberDao)));
    memberMenu.add(new Menu("조회", new MemberDetailListener(memberDao)));
    memberMenu.add(new Menu("변경", new MemberUpdateListener(memberDao, ds)));
    memberMenu.add(new Menu("삭제", new MemberDeleteListener(memberDao, ds)));
    mainMenu.add(memberMenu);

    MenuGroup stylingMenu = new MenuGroup("스타일");
    stylingMenu.add(new Menu("등록", new StylingAddListener(stylingDao, ds)));
    stylingMenu.add(new Menu("목록", new StylingListListener(stylingDao)));
    stylingMenu.add(new Menu("조회", new StylingDetailListener(stylingDao, ds)));
    stylingMenu.add(new Menu("변경", new StylingUpdateListener(stylingDao, ds)));
    stylingMenu.add(new Menu("삭제", new StylingDeleteListener(stylingDao, ds)));
    mainMenu.add(stylingMenu);

    MenuGroup accMenu = new MenuGroup("악세사리");
    accMenu.add(new Menu("등록", new AccAddListener(accDao, ds)));
    accMenu.add(new Menu("목록", new AccListListener(accDao)));
    accMenu.add(new Menu("조회", new AccDetailListener(accDao, ds)));
    accMenu.add(new Menu("변경", new AccUpdateListener(accDao, ds)));
    accMenu.add(new Menu("삭제", new AccDeleteListener(accDao, ds)));
    mainMenu.add(accMenu);

    // Menu helloMenu = new Menu("안녕!");
    // helloMenu.addActionListener(new HeaderListener());
    // helloMenu.addActionListener(new HelloListener());
    // helloMenu.addActionListener(new FooterListener());
    // mainMenu.add(helloMenu);
  }
}

