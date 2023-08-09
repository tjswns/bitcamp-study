package bitcamp.myapp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.Component;

@Component
public class MySQLStylingDao implements StylingDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLStylingDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Styling styling) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("bitcamp.myapp.dao.StylingDao.insert", styling);
  }

  @Override
  public List<Styling> findAll(int category) {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    return sqlSession.selectList("bitcamp.myapp.dao.StylingDao.findAll", category);
  }

  @Override
  public Styling findBy(int category, int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);

    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put("categoryNo", category);
    paramMap.put("stylingNo", no);

    return sqlSession.selectOne("bitcamp.myapp.dao.StylingDao.findBy", paramMap);
  }

  @Override
  public int update(Styling styling) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("bitcamp.myapp.dao.StylingDao.update", styling);
  }

  @Override
  public int updateCount(Styling styling) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("bitcamp.myapp.dao.StylingDao.updateCount", styling);
  }

  @Override
  public int delete(Styling styling) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("bitcamp.myapp.dao.StylingDao.delete", styling);
  }

}
