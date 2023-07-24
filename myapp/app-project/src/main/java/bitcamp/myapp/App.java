package bitcamp.myapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
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
import bitcamp.myapp.vo.Acc;
import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Menu;
import bitcamp.util.MenuGroup;

public class App {
  ArrayList<Member> memberList = new ArrayList<>();
  ArrayList<Acc> accList = new ArrayList<>();
  ArrayList<Styling> stylingList = new ArrayList<>();

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public App() {
    prepareMenu();
  }

  public static void main(String[] args) {
    new App().execute();
  }

  static void printTitle() {
    System.out.println("우리의 OOTD");
    System.out.println("----------------------------------");
  }

  public void execute() {
    printTitle();

    loadData();
    mainMenu.execute(prompt);
    saveData();

    prompt.close();
  }

  private void loadData() {
    loadMember();
    loadStyling("styling.data", stylingList);
    loadAcc("acc.data", accList);

  }

  private void saveData() {
    saveMember();
    saveStyling("styling.data", stylingList);
    saveAcc("acc.data", accList);
  }

  private void prepareMenu() {
    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new Menu("등록", new MemberAddListener(memberList)));
    memberMenu.add(new Menu("목록", new MemberListListener(memberList)));
    memberMenu.add(new Menu("조회", new MemberDetailListener(memberList)));
    memberMenu.add(new Menu("변경", new MemberUpdateListener(memberList)));
    memberMenu.add(new Menu("삭제", new MemberDeleteListener(memberList)));
    mainMenu.add(memberMenu);

    MenuGroup stylingMenu = new MenuGroup("스타일");
    stylingMenu.add(new Menu("등록", new StylingAddListener(stylingList)));
    stylingMenu.add(new Menu("목록", new StylingListListener(stylingList)));
    stylingMenu.add(new Menu("조회", new StylingDetailListener(stylingList)));
    stylingMenu.add(new Menu("변경", new StylingUpdateListener(stylingList)));
    stylingMenu.add(new Menu("삭제", new StylingDeleteListener(stylingList)));
    mainMenu.add(stylingMenu);

    MenuGroup accMenu = new MenuGroup("악세사리");
    accMenu.add(new Menu("등록", new AccAddListener(accList)));
    accMenu.add(new Menu("목록", new AccListListener(accList)));
    accMenu.add(new Menu("조회", new AccDetailListener(accList)));
    accMenu.add(new Menu("변경", new AccUpdateListener(accList)));
    accMenu.add(new Menu("삭제", new AccDeleteListener(accList)));
    mainMenu.add(accMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);
  }

  private void loadMember() {
    try {
      FileInputStream in = new FileInputStream("member.data");
      int size = in.read() << 8;
      size |= in.read();

      byte[] buf = new byte[1000];

      for (int i = 0; i < size; i++) {
        Member member = new Member();
        member.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        int length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        member.setName(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        member.setEmail(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        member.setPassword(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        member.setAge(new String(buf, 0, length, "UTF-8"));

        member.setGender((char) (in.read() << 8 | in.read()));

        length = in.read() << 16 | in.read();
        in.read(buf, 0, length);
        member.setTop(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 16 | in.read();
        in.read(buf, 0, length);
        member.setPants(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 16 | in.read();
        in.read(buf, 0, length);
        member.setShoes(new String(buf, 0, length, "UTF-8"));


        memberList.add(member);
      }
      // 데이터를 로딩한 이후에 추가할 회원의 번호를 설정한다.
      Member.userId = memberList.get(memberList.size() - 1).getNo() + 1;

      in.close();

    } catch (Exception e) {
      System.out.println("회원 정보를 읽는 중 오류 발생!");
    }
  }

  private void loadStyling(String filename, List<Styling> list) {
    try {
      FileInputStream in = new FileInputStream(filename);
      int size = in.read() << 8;
      size |= in.read();

      byte[] buf = new byte[1000];

      for (int i = 0; i < size; i++) {
        Styling styling = new Styling();
        styling.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        int length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        styling.setStyle(new String(buf, 0, length, "UTF-8"));


        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        styling.setBrand(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        styling.setFit(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        styling.setPassword(new String(buf, 0, length, "UTF-8"));

        styling.setViewCount(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        styling.setCreatedDate((long) in.read() << 56 | (long) in.read() << 48
            | (long) in.read() << 40 | (long) in.read() << 32 | (long) in.read() << 24
            | (long) in.read() << 16 | (long) in.read() << 8 | in.read());

        list.add(styling);
      }

      Styling.stylingNo = Math.max(Styling.stylingNo, list.get(list.size() - 1).getNo() + 1);

      in.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
    }
  }

  private void loadAcc(String filename, List<Acc> list) {
    try {
      FileInputStream in = new FileInputStream(filename);
      int size = in.read() << 8;
      size |= in.read();

      byte[] buf = new byte[1000];

      for (int i = 0; i < size; i++) {
        Acc acc = new Acc();
        acc.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        int length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        acc.setSelect(new String(buf, 0, length, "UTF-8"));


        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        acc.setStyle(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        acc.setSize(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        acc.setPassword(new String(buf, 0, length, "UTF-8"));

        acc.setViewCount(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        acc.setCreatedDate((long) in.read() << 56 | (long) in.read() << 48 | (long) in.read() << 40
            | (long) in.read() << 32 | (long) in.read() << 24 | (long) in.read() << 16
            | (long) in.read() << 8 | in.read());

        list.add(acc);
      }

      Acc.accNo = Math.max(Acc.accNo, list.get(list.size() - 1).getNo() + 1);

      in.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
    }
  }

  private void saveMember() {
    try {
      FileOutputStream out = new FileOutputStream("member.data");

      // 저장할 데이터의 개수를 먼저 출력한다.
      int size = memberList.size();
      out.write(size >> 8);
      out.write(size);

      for (Member member : memberList) {
        int no = member.getNo();
        out.write(no >> 24);
        out.write(no >> 16);
        out.write(no >> 8);
        out.write(no);

        byte[] bytes = member.getName().getBytes("UTF-8");
        // 출력할 바이트의 개수를 2바이트로 표시한다.
        out.write(bytes.length >> 8);
        out.write(bytes.length);

        // 문자열의 바이트를 출력한다.
        out.write(bytes);


        bytes = member.getEmail().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = member.getPassword().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        char gender = member.getGender();
        out.write(gender >> 8);
        out.write(gender);
      }
      out.close();

    } catch (Exception e) {
      System.out.println("회원 정보를 저장하는 중 오류 발생!");
    }
  }

  private void saveStyling(String filename, List<Styling> list) {
    try {
      FileOutputStream out = new FileOutputStream(filename);

      // 저장할 데이터의 개수를 먼저 출력한다.
      int size = list.size();
      out.write(size >> 8);
      out.write(size);

      for (Styling styling : list) {
        int no = styling.getNo();
        out.write(no >> 24);
        out.write(no >> 16);
        out.write(no >> 8);
        out.write(no);

        byte[] bytes = styling.getStyle().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);


        bytes = styling.getBrand().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = styling.getFit().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = styling.getPassword().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        int viewCount = styling.getViewCount();
        out.write(viewCount >> 24);
        out.write(viewCount >> 16);
        out.write(viewCount >> 8);
        out.write(viewCount);

        long createdDate = styling.getCreatedDate();
        out.write((int) (createdDate >> 56));
        out.write((int) (createdDate >> 48));
        out.write((int) (createdDate >> 40));
        out.write((int) (createdDate >> 32));
        out.write((int) (createdDate >> 24));
        out.write((int) (createdDate >> 16));
        out.write((int) (createdDate >> 8));
        out.write((int) createdDate);
      }
      out.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
    }
  }

  private void saveAcc(String filename, List<Acc> list) {
    try {
      FileOutputStream out = new FileOutputStream(filename);

      // 저장할 데이터의 개수를 먼저 출력한다.
      int size = list.size();
      out.write(size >> 8);
      out.write(size);

      for (Acc acc : list) {
        int no = acc.getNo();
        out.write(no >> 24);
        out.write(no >> 16);
        out.write(no >> 8);
        out.write(no);

        byte[] bytes = acc.getSelect().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);


        bytes = acc.getStyle().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = acc.getSize().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = acc.getPassword().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        int viewCount = acc.getViewCount();
        out.write(viewCount >> 24);
        out.write(viewCount >> 16);
        out.write(viewCount >> 8);
        out.write(viewCount);

        long createdDate = acc.getCreatedDate();
        out.write((int) (createdDate >> 56));
        out.write((int) (createdDate >> 48));
        out.write((int) (createdDate >> 40));
        out.write((int) (createdDate >> 32));
        out.write((int) (createdDate >> 24));
        out.write((int) (createdDate >> 16));
        out.write((int) (createdDate >> 8));
        out.write((int) createdDate);
      }
      out.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
    }
  }
}

