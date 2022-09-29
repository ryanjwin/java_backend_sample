package levvel.io.service;

import levvel.io.model.Comment;
import java.util.List;

public interface CommentService {
    void addComment(Comment comment, String blogId);
    List<Comment> getComment(String id);
}
