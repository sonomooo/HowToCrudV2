package Develop.spartaDevelop.calendar;

import Develop.spartaDevelop.calendar.dto.baseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
public class Calendar extends baseEntity {

    @Id @GeneratedValue
    Long id;

    String authorName;
    String toDoTitle;
    String content;

    public Calendar(String authorName, String content,String toDoTitle) {
        this.authorName = authorName;
        this.content = content;
        this.toDoTitle = toDoTitle;
    }

    public void updateTitleAuthor(String title, String author, String content){
        this.toDoTitle = title;
        this.authorName = author;
        this.content = content;
    }
}
