package Develop.spartaDevelop.calendar.dto;

import lombok.Getter;

@Getter
public class CalendarUpdateRequest {

    private final String authorName;
    private final String toDoTitle;
    private final String content;

    public CalendarUpdateRequest(String authorName, String toDoTitle, String content) {
        this.authorName = authorName;
        this.toDoTitle = toDoTitle;
        this.content = content;
    }
}
