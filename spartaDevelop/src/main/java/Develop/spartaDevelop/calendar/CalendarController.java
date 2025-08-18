package Develop.spartaDevelop.calendar;

import Develop.spartaDevelop.calendar.dto.CalendarGetResponse;
import Develop.spartaDevelop.calendar.dto.CalendarRequest;
import Develop.spartaDevelop.calendar.dto.CalendarResponse;
import Develop.spartaDevelop.calendar.dto.CalendarUpdateRequest;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {

    CalendarService calendarService;

    @PostMapping("/create")
    public CalendarResponse createCalendar(@RequestBody CalendarRequest calendarRequest) {
        return calendarService.create(calendarRequest);
    }

    @GetMapping("/read")
    public ResponseEntity<List<CalendarGetResponse>> readCalendar(
            @RequestParam(required = false) String author
    ) {
        return ResponseEntity.ok(calendarService.findAll(author));
    }

    @PostMapping("/update")
    public ResponseEntity<CalendarGetResponse> update(
            @PathVariable long scheduleId,
            @RequestBody CalendarUpdateRequest request
    ) {
        return ResponseEntity.ok(calendarService.update(scheduleId, request));
    }

    @PostMapping("/delete")
    public void delete(
            @PathVariable String authorName
    ){
        ResponseEntity.ok(calendarService.delete(authorName));
    }

}
