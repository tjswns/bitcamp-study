package bitcamp.myapp;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.dao.AccListDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.MemberListDao;
import bitcamp.myapp.dao.StylingDao;
import bitcamp.myapp.dao.StylingListDao;
import bitcamp.myapp.vo.Acc;
import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.Styling;
import bitcamp.net.RequestEntity;
import bitcamp.net.ResponseEntity;

public class ServerApp {

  int port;
  ServerSocket serverSocket;

  MemberDao memberDao = new MemberListDao("member.json");
  AccDao accDao = new AccListDao("acc.json");
  StylingDao stylingDao = new StylingListDao("styling.json");

  public ServerApp(int port) throws Exception {
    this.port = port;
  }

  public void close() throws Exception {
    serverSocket.close();
  }

  public static void main(String[] args) throws Exception {
    if (args.length < 1) {
      System.out.println("실행 예) java ... bitcamp.myapp.ServerApp 포트번호");
      return;
    }
    ServerApp app = new ServerApp(Integer.parseInt(args[0]));
    app.execute();
    app.close();
  }


  public void execute() throws Exception {
    System.out.println("[MyList 서버 애플리케이션]");

    this.serverSocket = new ServerSocket(port);
    System.out.println("서버 실행 중...");

    Socket socket = serverSocket.accept();
    DataInputStream in = new DataInputStream(socket.getInputStream());
    DataOutputStream out = new DataOutputStream(socket.getOutputStream());

    while (true) {
      RequestEntity request = RequestEntity.fromJson(in.readUTF());

      String command = request.getCommand();
      System.out.println(command);

      if (command.equals("quit")) {
        break;
      }

      ResponseEntity response = new ResponseEntity();

      switch (command) {
        case "acc/list":
          response.status(ResponseEntity.SUCCESS).result(accDao.list());
          break;
        case "acc/insert":
          accDao.insert(request.getObject(Acc.class));
          response.status(ResponseEntity.SUCCESS);
          break;
        case "acc/findBy":
          Acc acc = accDao.findBy(request.getObject(Integer.class));
          if (acc == null) {
            response.status(ResponseEntity.FAILURE).result("해당 번호의 게시글이 없습니다!");
          } else {
            response.status(ResponseEntity.SUCCESS).result(acc);
          }
          break;
        case "acc/update":
          int value = accDao.update(request.getObject(Acc.class));
          response.status(ResponseEntity.SUCCESS).result(value);
          break;
        case "acc/delete":
          value = accDao.delete(request.getObject(Integer.class));
          response.status(ResponseEntity.SUCCESS).result(value);
          break;
        case "member/list":
          response.status(ResponseEntity.SUCCESS).result(memberDao.list());
          break;
        case "member/insert":
          memberDao.insert(request.getObject(Member.class));
          response.status(ResponseEntity.SUCCESS);
          break;
        case "member/findBy":
          Member member = memberDao.findBy(request.getObject(Integer.class));
          if (member == null) {
            response.status(ResponseEntity.FAILURE).result("해당 번호의 회원이 없습니다!");
          } else {
            response.status(ResponseEntity.SUCCESS).result(member);
          }
          break;
        case "member/update":
          value = memberDao.update(request.getObject(Member.class));
          response.status(ResponseEntity.SUCCESS).result(value);
          break;
        case "member/delete":
          value = memberDao.delete(request.getObject(Integer.class));
          response.status(ResponseEntity.SUCCESS).result(value);
          break;
        case "styling/list":
          response.status(ResponseEntity.SUCCESS).result(stylingDao.list());
          break;
        case "styling/insert":
          stylingDao.insert(request.getObject(Styling.class));
          response.status(ResponseEntity.SUCCESS);
          break;
        case "styling/findBy":
          Styling styling = stylingDao.findBy(request.getObject(Integer.class));
          if (styling == null) {
            response.status(ResponseEntity.FAILURE).result("해당 번호의 게시글이 없습니다!");
          } else {
            response.status(ResponseEntity.SUCCESS).result(styling);
          }
          break;
        case "styling/update":
          int value2 = stylingDao.update(request.getObject(Styling.class));
          response.status(ResponseEntity.SUCCESS).result(value2);
          break;
        case "styling/delete":
          value2 = stylingDao.delete(request.getObject(Integer.class));
          response.status(ResponseEntity.SUCCESS).result(value2);
          break;
        default:
          response.status(ResponseEntity.ERROR).result("해당 명령을 지원하지 않습니다!");
      }

      out.writeUTF(response.toJson());
    }

    in.close();
    out.close();
    socket.close();
  }
}

