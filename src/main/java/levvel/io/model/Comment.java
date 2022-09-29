package levvel.io.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    String id;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;


    // info for comments
    String author;
    String text;
    // store id that blog belongs to
    private String blogId;


    // getters and setters
    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }
    public String getBlogID() {
        return this.blogId;
    }
}
