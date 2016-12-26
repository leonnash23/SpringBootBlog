package leoninc.Model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Leonid Cheremshantcev on 23.12.16.
 */
@Configuration
@ComponentScan
public class PostList extends ArrayList<Post> {
    private PostCreater postCreater;

    public PostList(){
        postCreater = new PostCreater();
    }

    @Bean
    PostList createPostList(){
        return new PostList();
    }

    public Post createPost(String title, String text){
        Post post = postCreater.createPost(title,text);
        super.add(post);
        return post;
    }

    public Post remove(Long index) {
        for (Post n : this) {
            if (Objects.equals(index, n.getId())) {
                super.remove(n);
                return n;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }
}
