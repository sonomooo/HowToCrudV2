package Develop.spartaDevelop.calendar;

import Develop.spartaDevelop.calendar.dto.CalendarGetResponse;
import Develop.spartaDevelop.calendar.dto.CalendarRequest;
import Develop.spartaDevelop.calendar.dto.CalendarResponse;
import Develop.spartaDevelop.calendar.dto.CalendarUpdateRequest;
import jakarta.transaction.TransactionScoped;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;

    @Transactional
    public CalendarResponse create(CalendarRequest calendarRequest) {

        if (calendarRequest.getContent() == null){
            throw new IllegalStateException("내용은 필수입니다.");
        }
        if (calendarRequest.getAuthorName() == null){
            throw new IllegalStateException("작성자 이름은 필수입니다.");
        }
        if (calendarRequest.getToDoTitle() == null){
            throw new IllegalStateException("제목은 필수입니다.");
        }

        Calendar calendar = new Calendar(
                calendarRequest.getToDoTitle(),
                calendarRequest.getContent(),
                calendarRequest.getAuthorName()
        );

        CalendarResponse response = calendarRepository.save(calendar);

        return new CalendarResponse(
                response.getAuthor(),
                response.getId(),
                response.getTitle(),
                response.getContent(),
                response.getCreatedAt(),
                response.getModifiedAt()
        );
    }

    public List<CalendarGetResponse> findAll(String author) {
        List<Calendar> calendars = calendarRepository.findAll();
        List<CalendarGetResponse> dtos = new ArrayList<>();
        if (author == null) {

            for (Calendar calendar : calendars) {
                dtos.add(new CalendarGetResponse(
                calendar.getId(),
                calendar.getContent(),
                calendar.getAuthorName(),
                calendar.getToDoTitle(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
                ));
            }
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public List<Calendar> findCalendar(String author) {
        List<Calendar> calendars;

        if (author == null){
            calendars = calendarRepository.findAll();
        } else {
            calendars = calendarRepository.findByAuthor(author);
        }
        return  calendars;
    }

    @TransactionScoped
    public CalendarGetResponse update(long calendarId, CalendarUpdateRequest request) {
        Calendar calendar = (Calendar) calendarRepository.findById(calendarId).orElseThrow(
                () -> new IllegalArgumentException("해당 id의 일정이 없습니다")
        );
        calendar.updateTitleAuthor(
                request.getAuthorName(),
                request.getContent(),
                request.getToDoTitle()
        );

        return new CalendarGetResponse(
                calendar.getId(),
                calendar.getAuthorName(),
                calendar.getContent(),
                calendar.getToDoTitle(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        );
    }

    public CalendarGetResponse delete(String authorName) {
        return calendarRepository.deleteByAuthorName(authorName);
    }
}