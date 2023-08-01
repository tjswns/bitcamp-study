package bitcamp.myapp;

import java.net.Socket;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.config.AppConfig;
import bitcamp.util.ApplicationContext;
import bitcamp.util.DispatcherListener;
import bitcamp.util.SqlSessionFactoryProxy;

public class ServerApp {

  ApplicationContext iocContainer;
  DispatcherListener facadeListener;

  int port;

  public ServerApp(int port) throws Exception {
    this.port = port;
    iocContainer = new ApplicationContext(AppConfig.class); // IoC 컨테이너
    facadeListener = new DispatcherListener(iocContainer); // Facade Listener
  }

  public void close() throws Exception {

  }

  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp(8888);
    app.execute();
    app.close();
  }

  public void execute() {

  }

  private void processRequest(Socket socket) {
    try {



    } catch (Exception e) {
      System.out.println("클라이언트 통신 오류!");
      e.printStackTrace();

    } finally {
      SqlSessionFactoryProxy sqlSessionFactoryProxy =
          (SqlSessionFactoryProxy) iocContainer.getBean(SqlSessionFactory.class);
      sqlSessionFactoryProxy.clean();
    }
  }
}
