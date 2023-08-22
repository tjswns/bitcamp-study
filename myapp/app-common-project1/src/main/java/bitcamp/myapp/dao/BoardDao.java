package bitcamp.myapp.dao;

import java.util.List;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.p_size_data;

public interface BoardDao {
  void insert(p_size_data board);
  List<p_size_data> findAll(int category);
  p_size_data findBy(int category, int no);
  int update(p_size_data board);
  int updateCount(p_size_data board);
  int delete(p_size_data board);

  int insertFiles(p_size_data board);
  AttachedFile findFileBy(int no);
  int deleteFile(int fileNo);
}
