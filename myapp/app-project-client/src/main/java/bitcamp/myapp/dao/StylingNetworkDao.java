package bitcamp.myapp.dao;

import java.util.ArrayList;
import java.util.List;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.JsonDataHelper;

public class StylingNetworkDao implements StylingDao {
  String filename;
  ArrayList<Styling> list = new ArrayList<>();

  public StylingNetworkDao(String filename) {
    this.filename = filename;
    JsonDataHelper.loadJson(filename, list, Styling.class);
  }

  @Override
  public void insert(Styling styling) {
    styling.setNo(Styling.stylingNo++);
    styling.setCreatedDate(System.currentTimeMillis());
    this.list.add(styling);
    JsonDataHelper.saveJson(filename, list);
  }

  @Override
  public List<Styling> list() {
    return this.list;
  }

  @Override
  public Styling findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Styling m = this.list.get(i);
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }

  @Override
  public int update(Styling styling) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == styling.getNo()) {
        list.set(i, styling);
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
