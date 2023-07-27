package bitcamp.myapp.dao;

import java.util.List;
import bitcamp.myapp.vo.Acc;

public interface AccDao {
  void insert(Acc acc);

  List<Acc> list();

  Acc findBy(int no);

  int update(Acc acc);

  int delete(int no);
}
