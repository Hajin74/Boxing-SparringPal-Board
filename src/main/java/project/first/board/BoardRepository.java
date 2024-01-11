package project.first.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@Transactional
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

    public Long save(Board board) {
        String sql = "INSERT INTO board (title) VALUES (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"board_id"});
            ps.setString(1, board.getTitle());
            return ps;
        }, keyHolder);

        Long key = keyHolder.getKey().longValue();
        return key;
    }

    public List<Board> findAll() {
        String sql = "SELECT * FROM board";
        return jdbcTemplate.query(sql, boardMapper());
    }

    public Board findById(Long id) {
       String sql = "SELECT * from board WHERE board_id = (?)";
       return jdbcTemplate.queryForObject(sql, boardMapper(), id);
    }

    public void update(Board board) {
        String sql = "UPDATE board SET title = ? WHERE board_id = ?";
        jdbcTemplate.update(sql, board.getTitle(), board.getId());
    }

}
