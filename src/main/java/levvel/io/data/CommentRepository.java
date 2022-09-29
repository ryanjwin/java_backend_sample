package levvel.io.data;

import levvel.io.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    // Add method to find comments that match criteria. Using the blog id
    List<Comment> findByBlogId(String blogId);
}
