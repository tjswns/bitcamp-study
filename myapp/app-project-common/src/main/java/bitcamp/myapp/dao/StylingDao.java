package bitcamp.myapp.dao;

import java.util.List;
import bitcamp.myapp.vo.Styling;

public interface StylingDao {
  void insert(Styling styling);

  List<Styling> findAll(int category);

  Styling findBy(int category, int no);

  int update(Styling styling);

  int updateCount(Styling styling);

  int delete(Styling styling);
}
