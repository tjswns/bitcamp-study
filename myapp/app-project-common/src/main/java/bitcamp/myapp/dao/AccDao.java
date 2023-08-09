package bitcamp.myapp.dao;

import java.util.List;
import bitcamp.myapp.vo.Acc;

public interface AccDao {
  void insert(Acc acc);

  List<Acc> findAll(int category);

  Acc findBy(int category, int no);

  int update(Acc acc);

  int updateCount(Acc acc);

  int delete(Acc acc);
}
