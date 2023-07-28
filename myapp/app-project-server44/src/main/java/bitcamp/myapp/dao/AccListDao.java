package bitcamp.myapp.dao;

import java.util.ArrayList;
import java.util.List;
import bitcamp.myapp.vo.Acc;
import bitcamp.util.JsonDataHelper;

public class AccListDao implements AccDao {

  String filename;
  ArrayList<Acc> list = new ArrayList<>();

  public AccListDao(String filename) {
    this.filename = filename;
    JsonDataHelper.loadJson(filename, list, Acc.class);
  }

  @Override
  public void insert(Acc acc) {
    acc.setNo(Acc.accNo++);
    acc.setCreatedDate(System.currentTimeMillis());
    this.list.add(acc);
    JsonDataHelper.saveJson(filename, list);
  }

  @Override
  public List<Acc> list() {
    return this.list;
  }

  @Override
  public Acc findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Acc m = this.list.get(i);
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }

  @Override
  public int update(Acc acc) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == acc.getNo()) {
        list.set(i, acc);
        JsonDataHelper.saveJson(filename, list);
        return 1;
      }
    }
    return 0;
  }

  @Override
  public int delete(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no) {
        list.remove(i);
        JsonDataHelper.saveJson(filename, list);
        return 1;
      }
    }
    return 0;
  }

}
