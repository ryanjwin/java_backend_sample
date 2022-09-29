package levvel.io;

// import files needed
import levvel.io.model.Blog;
import levvel.io.model.Comment;
import levvel.io.service.BlogService;
import levvel.io.service.CommentService;
import levvel.io.controller.BlogController;

// import junit, spring, mockito, and mockmvc
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

// import java data structures
import java.util.ArrayList;
import java.util.List;

// import to convert to json
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
public class BlogControllerTest {

    // Create Mock
    private MockMvc mvc;

    // Sample mock objects
    Blog blog = new Blog("0000", null, null, "Peter Parker", "Title Example", "This is sample text for a blog post!");
    Comment comment = new Comment("1000", null, null, "Bruce Wayne", "This is sample text for a comment!", "0000");


    // Create the mocks
    @Mock
    BlogService blogService;

    @Mock
    CommentService commentService;

    // Inject them to to blogController
    @InjectMocks
    BlogController blogController;
    
    // Setup Mockito and mockmvc before each test
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(blogController).build();
    }

    // test if blog is returned correctly (GET Method)
    @Test
    public void getBlog() throws Exception{
        // Don't actually get from db
        Mockito.when(blogService.getBlog("0000")).thenReturn(blog);

        mvc.perform(MockMvcRequestBuilders.get("/blog/post/0000").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.author").value("Peter Parker"));
    }

    // test if comments are returned correctly (GET Method)
    @Test
    public void getComments() throws Exception{
        // To compare the results to.  An array list of one comment
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        // Don't actually get from db
        Mockito.when(commentService.getComment("0000")).thenReturn(comments);
        mvc.perform(MockMvcRequestBuilders.get("/blog/post/0000/comment").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$[0].author").value("Bruce Wayne"));
    }

    // test if blog is posted correctly (POST Method)
    @Test
    public void addBlog() throws Exception{
        // dont add blog for testing only
        Mockito.doNothing().when(blogService).addBlog(blog);

        // convert to json
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(blog);

        // POST still returns the blog so it should return blog by peter parker
        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/blog/post").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(content);
        mvc.perform(postRequest).andExpect(status().isOk()).andExpect(jsonPath("$.author").value("Peter Parker"));;

    }

    //test if comments are posted correctly (POST Method)
    @Test
    public void addComment() throws Exception{
        // dont add comment for testing only
        Mockito.doNothing().when(commentService).addComment(comment, "0000");

        // convert to json
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(comment);

        // POST still returns the blog so it should return blog by peter parker
        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/blog/post/0000/comment").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(content);
        mvc.perform(postRequest).andExpect(status().isOk()).andExpect(jsonPath("$.author").value("Bruce Wayne"));
    }
}   
