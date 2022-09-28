package levvel.io.service;

import levvel.io.data.CommentRepository;
import levvel.io.model.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Comment getComment(String id){
        return commentRepository.findById(id).orElseGet(null);
    }

}
