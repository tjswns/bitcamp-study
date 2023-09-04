package bitcamp.myapp.service;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
public class DefaultBoardService implements BoardService {

  BoardDao boardDao;

  public DefaultBoardService(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Transactional // 이 메서드는 트랜잭션 상태에서 실행하라고 지정한다.
  @Override
  public int add(Board board) throws Exception {
        int count = boardDao.insert(board);
        if (board.getAttachedFiles().size() > 0) {
          boardDao.insertFiles(board);
        }
        return count;
  }

  @Override
  public List<Board> list(int category) throws Exception {
    return boardDao.findAll(category);
  }

  @Override
  public Board get(int boardNo) throws Exception {
    return boardDao.findBy(boardNo);
  }

  @Transactional
  @Override
  public int update(Board board) throws Exception {
      int count = boardDao.update(board);
      if (count > 0 && board.getAttachedFiles().size() > 0) {
        boardDao.insertFiles(board);
      }
      return count;
  }

  @Transactional
  @Override
  public int delete(int boardNo) throws Exception {
      boardDao.deleteFiles(boardNo);
      return boardDao.delete(boardNo);
  }

  @Override
  public int increaseViewCount(int boardNo) throws Exception {
      int count = boardDao.updateCount(boardNo);
      return count;
  }

  @Override
  public AttachedFile getAttachedFile(int fileNo) throws Exception {
    return boardDao.findFileBy(fileNo);
  }

  @Override
  public int deleteAttachedFile(int fileNo) throws Exception {
    return boardDao.deleteFile(fileNo);
  }
}