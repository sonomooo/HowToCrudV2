package Develop.spartaDevelop.calendar.dto;


import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public class CalendarRequest {

    Long id;
    String authorName;
    String toDoTitle;
    String content;

}
