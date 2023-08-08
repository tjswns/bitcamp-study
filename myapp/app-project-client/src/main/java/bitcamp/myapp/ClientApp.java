package bitcamp.myapp;


import java.sql.Connection;
import java.sql.DriverManager;
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
import bitcamp.myapp.handler.FooterListener;
import bitcamp.myapp.handler.HeaderListener;
import bitcamp.myapp.handler.HelloListener;
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
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;



public class ClientApp {

  public static Member loginUser;
  MemberDao memberDao;
  AccDao accDao;
  StylingDao stylingDao;

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public ClientApp(String ip, int port) throws Exception {
    Connection con =
        DriverManager.getConnection("jdbc:mysql://project:1111@localhost:3306/projectdb" // JDBC
        // URL
        );

    this.memberDao = new MySQLMemberDao(con);
    this.accDao = new MySQLAccDao(con, 1);
    this.stylingDao = new MySQLStylingDao(con, 2);

    prepareMenu();
  }


  public void close() throws Exception {
    prompt.close();

  }

  public static void main(String[] args) throws Exception {
    if (args.length < 2) {
      System.out.println("실행 예) java ... bitcamp.myapp.ClientApp 서버주소 포트번호");
      return;
    }
    ClientApp app = new ClientApp(args[0], Integer.parseInt(args[1]));
    app.execute();
    app.close();
  }

  static void printTitle() {
    System.out.println("우리의 OOTD");
    System.out.println("----------------------------------");
  }

  public void execute() {
    printTitle();
    mainMenu.execute(prompt);
  }


  private void prepareMenu() {
    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new Menu("등록", new MemberAddListener(memberDao)));
    memberMenu.add(new Menu("목록", new MemberListListener(memberDao)));
    memberMenu.add(new Menu("조회", new MemberDetailListener(memberDao)));
    memberMenu.add(new Menu("변경", new MemberUpdateListener(memberDao)));
    memberMenu.add(new Menu("삭제", new MemberDeleteListener(memberDao)));
    mainMenu.add(memberMenu);

    MenuGroup stylingMenu = new MenuGroup("스타일");
    stylingMenu.add(new Menu("등록", new StylingAddListener(stylingDao)));
    stylingMenu.add(new Menu("목록", new StylingListListener(stylingDao)));
    stylingMenu.add(new Menu("조회", new StylingDetailListener(stylingDao)));
    stylingMenu.add(new Menu("변경", new StylingUpdateListener(stylingDao)));
    stylingMenu.add(new Menu("삭제", new StylingDeleteListener(stylingDao)));
    mainMenu.add(stylingMenu);

    MenuGroup accMenu = new MenuGroup("악세사리");
    accMenu.add(new Menu("등록", new AccAddListener(accDao)));
    accMenu.add(new Menu("목록", new AccListListener(accDao)));
    accMenu.add(new Menu("조회", new AccDetailListener(accDao)));
    accMenu.add(new Menu("변경", new AccUpdateListener(accDao)));
    accMenu.add(new Menu("삭제", new AccDeleteListener(accDao)));
    mainMenu.add(accMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);
  }
}

