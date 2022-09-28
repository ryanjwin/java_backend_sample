package levvel.io.controller;

import levvel.io.model.Blog;
import levvel.io.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    private BlogService blogService;

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
    public ResponseEntity<Blog> getBlogComments(@PathVariable String id) {
        // TODO: Implement getBlogComments
        //Blog blog = blogService.getBlogComments(id);
        return ResponseEntity.ok().body(blog);
    }
    // post cooments for post
    @PostMapping("/post/{id}/comment")
    public ResponseEntity<Blog> addBlogComment(@PathVariable String id, @RequestBody Blog blog) {
        // TODO: Implement addBlogComment.
        //blogService.addBlogComment(id, blog);
        return ResponseEntity.ok().body(blog);
    }
}
