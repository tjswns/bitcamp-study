package bitcamp.myapp.dao;

import java.util.List;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.p_size_data;

public interface p_size_dataDao {
  void insert(p_size_data p_size_data);
  List<p_size_data> findAll();
  p_size_data findBy(int no);
  int update(p_size_data p_size_data);
  int updateCount(p_size_data p_size_data);
  int delete(p_size_data p_size_data);

  int insertFiles(p_size_data p_size_data);
  AttachedFile findFileBy(int no);
  int deleteFile(int fileNo);
}
