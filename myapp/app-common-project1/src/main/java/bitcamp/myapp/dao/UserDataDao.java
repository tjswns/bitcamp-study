package bitcamp.myapp.dao;

import java.util.List;
import bitcamp.myapp.vo.UserData;

public interface UserDataDao {
  void insert(UserData userData);
  List<UserData> findAll();
  UserData findBy(int no);
  UserData findByEmailAndPassword(UserData userData);
  int update(UserData userData);
  int delete(int no);
}
