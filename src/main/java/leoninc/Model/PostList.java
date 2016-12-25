package leoninc.Model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

}
