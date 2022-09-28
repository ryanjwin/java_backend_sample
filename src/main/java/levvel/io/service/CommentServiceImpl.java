package levvel.io.service;

import levvel.io.data.CommentRepository;
import levvel.io.model.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{
    
    private CommentRepository commentRepository;

    @Override
    public void addComment(Comment comment, String blogId) {
        comment.setBlogId(blogId);
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getComment(String id){
        return commentRepository.findByBlogId(id);
    }

}
