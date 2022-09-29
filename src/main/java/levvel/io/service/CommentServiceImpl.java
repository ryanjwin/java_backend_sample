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

    // Add a comment to the repository
    @Override
    public void addComment(Comment comment, String blogId) {
        // set the corresponding blog id
        comment.setBlogId(blogId);
        commentRepository.save(comment);
    }

    // return all comments with blog id
    @Override
    public List<Comment> getComment(String id){
        return commentRepository.findByBlogId(id);
    }

}
