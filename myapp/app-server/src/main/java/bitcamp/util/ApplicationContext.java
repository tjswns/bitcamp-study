package bitcamp.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import bitcamp.myapp.config.AppConfig;

public class ApplicationContext {
  // 객체 보관소
  Map<String, Object> beanContainer = new HashMap<>();

  public ApplicationContext(Class<?> configClass) throws Exception {
    // 클래스의 생성자 알아내기
    Constructor<?> constructor = configClass.getConstructor();

    Object obj = constructor.newInstance();

    Method[] methods = configClass.getDeclaredMethods(); // 해당 클래스에 정의된 메서드 목록만 가져온다.
    for (Method m : methods) {
      if (m.getReturnType() == void.class) {
        // 메서드의 리턴 타입이 없다면 무시한다.
        continue;
      }

      // @Bean 애노테이션을 붙지 않은 메서드라면 무시한다.
      Bean beanAnnotation = m.getAnnotation(Bean.class);
      if (beanAnnotation == null) {
        continue;
      }

      // 오직 값을 리턴하는 메서드만 호출한다.
      Object returnValue = m.invoke(obj);

      // 메서드가 리턴한 값을 컨테이너에 저장한다.
      if (beanAnnotation.value().length() > 0) {
        //
        beanContainer.put(beanAnnotation.value(), returnValue);
      } else {
        beanContainer.put(m.getName(), returnValue);
      }

    }
  }


  public Object getBean(String name) {
    return beanContainer.get(name);
  }

  public String[] getBeanNames() {
    return beanContainer.keySet().toArray(new String[] {});
  }

  public static void main(String[] args) throws Exception {
    ApplicationContext applicationContext = new ApplicationContext(AppConfig.class);
    for (String name : applicationContext.getBeanNames()) {
      System.out.println(name);
    }
  }

  @Bean("ohora")
  public SqlSessionFactory createSqlsessionFactory() throws Exception {
    return new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder()
        .build(Resources.getResourceAsStream("bitcamp/myapp/config/mybatis-config.xml")));
  }
}
