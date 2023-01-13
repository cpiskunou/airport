package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.Airplane;

import java.util.List;
import java.util.Optional;

public interface AirplaneRepository {

    String FIND_BY_ID = """
            select id as airplane_id,
                   model as airplane_model,
                   seats_in_row as airplane_seats_in_row,
                   row_no as airplane_row_no
            from airplane where id = ?""";

    String FIND_BY_MODEL = """
            select id as airplane_id,
                   model as airplane_model,
                   seats_in_row as airplane_seats_in_row,
                   row_no as airplane_row_no
            from airplane where model = ?""";

    String FIND_ALL = """
             select id as airplane_id,
                    model as airplane_model,
                    seats_in_row as airplane_seats_in_row,
                    row_no as airplane_row_no
             from airplane""";

    String CREATE = "insert into airplane(username, row_seat_no, row_no) values(?, ?, ?)";
    String DELETE = "delete from airplane where id = ?";

    void create(Airplane airplane);

    Optional<Airplane> findById(long id);

    Optional<Airplane> findByModel(String model);

    List<Airplane> findAll();

    void removeById(long id);

}
