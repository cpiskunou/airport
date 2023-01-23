package by.piskunou.solvdlaba.persistent;

import by.piskunou.solvdlaba.domain.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface TicketRepository {

    void create(@Param("ticket") Ticket ticket, @Param("userId") long userId);

    Optional<Ticket> findById(long id);

    boolean isOwner(@Param("ticketId") long ticketId, @Param("userId") long userId);

}
