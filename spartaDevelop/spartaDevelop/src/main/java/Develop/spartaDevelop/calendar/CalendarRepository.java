package Develop.spartaDevelop.calendar;

import Develop.spartaDevelop.calendar.dto.CalendarGetResponse;
import Develop.spartaDevelop.calendar.dto.CalendarResponse;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Id> {

    CalendarResponse save(Calendar request);

    List<CalendarResponse> findAll(String author);

    List<Calendar> findByAuthor(String author);

    Optional<Calendar> findById(long calendarId);

    CalendarGetResponse deleteByAuthorName(String authorName);
}