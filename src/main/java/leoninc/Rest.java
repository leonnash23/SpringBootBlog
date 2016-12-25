package leoninc;

import leoninc.Model.PostList;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by Leonid Cheremshantcev on 23.12.16.
 */
@Log4j2
@RestController
@Component
public class Rest {
    @Autowired
    private PostList postList;

    @RequestMapping("/rest/hello")
    public String index(){
        return "Hello, motherfucker!";
    }

    @RequestMapping(value = "/rest/hello/{name}",method = RequestMethod.GET)
    public String hello(@PathVariable String name){
        return String.format("Hello, %s!",name);
    }

    @RequestMapping(value = "/rest/createPost/", method = RequestMethod.GET)
    public String createPost(@RequestParam(value = "title") String title, @RequestParam(value = "text") String text){
        log.info(String.format("Create post from REST: %s,%s",title,text));
        return postList.createPost(title, text).toString();
    }

    @RequestMapping("/rest/checklogin")
    public String checkLogin(HttpSession session){
        if(session.getAttribute("auto") != null) {
            return session.getAttribute("auto").toString();
        }
        else {
            return "You are not admin";
        }
    }
}
