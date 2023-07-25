package bitcamp.myapp;


import java.util.ArrayList;
import java.util.List;
import bitcamp.io.BufferedDataInputStream;
import bitcamp.io.BufferedDataOutputStream;
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
      BufferedDataInputStream in = new BufferedDataInputStream("member.data");
      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        Member member = new Member();
        member.setNo(in.readInt());
        member.setName(in.readUTF());
        member.setEmail(in.readUTF());
        member.setPassword(in.readUTF());
        member.setAge(in.readUTF());
        member.setGender(in.readChar());
        member.setTop(in.readUTF());
        member.setPants(in.readUTF());
        member.setShoes(in.readUTF());
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
      BufferedDataInputStream in = new BufferedDataInputStream(filename);
      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        Styling styling = new Styling();
        styling.setNo(in.readInt());
        styling.setStyle(in.readUTF());
        styling.setBrand(in.readUTF());
        styling.setFit(in.readUTF());
        styling.setPassword(in.readUTF());
        styling.setViewCount(in.readInt());
        styling.setCreatedDate(in.readLong());
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
      BufferedDataInputStream in = new BufferedDataInputStream(filename);
      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        Acc acc = new Acc();
        acc.setNo(in.readInt());
        acc.setSelect(in.readUTF());
        acc.setStyle(in.readUTF());
        acc.setSize(in.readUTF());
        acc.setPassword(in.readUTF());
        acc.setViewCount(in.readInt());
        acc.setCreatedDate(in.readLong());
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
      BufferedDataOutputStream out = new BufferedDataOutputStream("member.data");

      // 저장할 데이터의 개수를 먼저 출력한다.
      out.writeShort(memberList.size());

      for (Member member : memberList) {
        out.writeInt(member.getNo());
        out.writeUTF(member.getName());
        out.writeUTF(member.getEmail());
        out.writeUTF(member.getPassword());
        out.writeUTF(member.getAge());
        out.writeChar(member.getGender());
        out.writeUTF(member.getTop());
        out.writeUTF(member.getPants());
        out.writeUTF(member.getShoes());
      }
      out.close();

    } catch (Exception e) {
      System.out.println("회원 정보를 저장하는 중 오류 발생!");
    }
  }

  private void saveStyling(String filename, List<Styling> list) {
    try {
      BufferedDataOutputStream out = new BufferedDataOutputStream("styling.data");


      out.writeShort(list.size());

      for (Styling styling : list) {
        out.writeInt(styling.getNo());
        out.writeUTF(styling.getStyle());
        out.writeUTF(styling.getBrand());
        out.writeUTF(styling.getFit());
        out.writeUTF(styling.getPassword());
        out.writeInt(styling.getViewCount());
        out.writeLong(styling.getCreatedDate());

      }
      out.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
    }
  }

  private void saveAcc(String filename, List<Acc> list) {
    try {
      BufferedDataOutputStream out = new BufferedDataOutputStream(filename);

      out.writeShort(list.size());

      for (Acc acc : list) {
        out.writeInt(acc.getNo());
        out.writeUTF(acc.getSelect());
        out.writeUTF(acc.getStyle());
        out.writeUTF(acc.getSize());
        out.writeUTF(acc.getPassword());
        out.writeInt(acc.getViewCount());
        out.writeLong(acc.getCreatedDate());

      }
      out.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
    }
  }
}

