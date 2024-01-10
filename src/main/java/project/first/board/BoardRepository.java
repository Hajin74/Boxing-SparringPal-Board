package project.first.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Board> boardMapper() {
        return (resultSet, rowNum) -> {
            Board board = new Board();
            board.setId(resultSet.getLong("board_id"));
            board.setTitle(resultSet.getString("title"));
            return board;
        };
    }

    public void save(Board board) {
        String sql = "INSERT INTO board (title) VALUES (?)";
        jdbcTemplate.update(sql, board.getTitle());
    }

    public List<Board> findAll() {
        String sql = "SELECT * FROM board";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> Board.builder()
                .id(resultSet.getLong("board_id"))
                .title(resultSet.getString("title"))
                .build());
    }

    public Board findById(Long id) {
       String sql = "SELECT * from board WHERE board_id = (?)";
        return (Board) jdbcTemplate.query(sql, boardMapper());
    }

}
