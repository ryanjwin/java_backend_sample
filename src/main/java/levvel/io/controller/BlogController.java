package levvel.io.controller;

import levvel.io.model.Blog;
import levvel.io.model.Comment;
import levvel.io.service.BlogService;
import levvel.io.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    private BlogService blogService;
    private CommentService commentService;

    // Post Request to add a blog
    @PostMapping("/post")
    public ResponseEntity<Blog> addBlog(@RequestBody Blog blog) {
        blogService.addBlog(blog);
        return ResponseEntity.ok().body(blog);
    }

    // Get the blog at the specified id
    @GetMapping("/post/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable String id) {
        Blog blog = blogService.getBlog(id);
        return ResponseEntity.ok().body(blog);
    }

    // view all comments for post 
    @GetMapping("/post/{id}/comment")
    public ResponseEntity<List<Comment>> getBlogComments(@PathVariable String id) {
        List<Comment> comments = commentService.getComment(id);
        return ResponseEntity.ok().body(comments);
    }
    // post cooments for post
    @PostMapping("/post/{id}/comment")
    public ResponseEntity<Comment> addBlogComment(@PathVariable String id, @RequestBody Comment comment) {
        commentService.addComment(comment, id);
        return ResponseEntity.ok().body(comment);
    }
}
