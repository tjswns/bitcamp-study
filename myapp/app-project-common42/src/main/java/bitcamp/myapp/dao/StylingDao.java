package bitcamp.myapp.dao;

import java.util.List;
import bitcamp.myapp.vo.Styling;

public interface StylingDao {
  void insert(Styling styling);

  List<Styling> list();

  Styling findBy(int no);

  int update(Styling styling);

  int delete(int no);
}
