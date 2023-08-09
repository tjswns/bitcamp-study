package bitcamp.myapp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.vo.Acc;

public class MySQLAccDao implements AccDao {

  SqlSessionFactory sqlSessionFactory;


  public MySQLAccDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Acc acc) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("bitcamp.myapp.dao.AccDao.insert", acc);
  }

  @Override
  public List<Acc> findAll(int category) {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    return sqlSession.selectList("bitcamp.myapp.dao.AccDao.findAll", category);
  }

  @Override
  public Acc findBy(int category, int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);

    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put("categoryNo", category);
    paramMap.put("accNo", no);

    return sqlSession.selectOne("bitcamp.myapp.dao.AccDao.findBy", paramMap);
  }

  @Override
  public int update(Acc acc) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("bitcamp.myapp.dao.AccDao.update", acc);
  }

  @Override
  public int updateCount(Acc acc) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("bitcamp.myapp.dao.AccDao.updateCount", acc);
  }

  @Override
  public int delete(Acc acc) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("bitcamp.myapp.dao.AccDao.delete", acc);
  }

}
