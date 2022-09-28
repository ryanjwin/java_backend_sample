package levvel.io.controller;

import levvel.io.model.Blog;
import levvel.io.model.Comment;
import levvel.io.service.BlogService;
import levvel.io.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    private BlogService blogService;
    private CommentService commentService;

    @PostMapping("/post")
    public ResponseEntity<Blog> addBlog(@RequestBody Blog blog) {
        blogService.addBlog(blog);
        return ResponseEntity.ok().body(blog);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable String id) {
        Blog blog = blogService.getBlog(id);
        return ResponseEntity.ok().body(blog);
    }

    // view all comments for post 
    @GetMapping("/post/{id}/comment")
    public ResponseEntity<Comment> getBlogComments(@PathVariable String id) {
        Comment comment = commentService.getComment(id);
        return ResponseEntity.ok().body(comment);
    }
    // post cooments for post
    @PostMapping("/post/{id}/comment")
    public ResponseEntity<Comment> addBlogComment(@PathVariable String id, @RequestBody Comment comment) {
        commentService.addComment(comment, id);
        return ResponseEntity.ok().body(comment);
    }
}
