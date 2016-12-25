package leoninc;

import leoninc.Model.PostList;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by Leonid Cheremshantcev on 23.12.16.
 */
@Log4j2
@org.springframework.stereotype.Controller
@Component
public class Controller {

    @Autowired
    private PostList postList;


    @RequestMapping("/")
    public String index(@RequestParam(value = "name",required = false, defaultValue = "World") String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping("/love")
    public String love(){
        return "love";
    }

    @RequestMapping("/test")
    public String test(){
        return "src/main/resources/public/readme.txt";
    }

    @RequestMapping("/list")
    public String getList(Model model){
        log.info("Get list. List size:"+postList.size());
        model.addAttribute("list",postList);
        return "list";
    }

    @RequestMapping(value = "/createPost", method = RequestMethod.GET)
    public String createPostForm(){
        return "createPostForm";
    }

    @RequestMapping(value = "/createPost", method = RequestMethod.POST)
    public String createPostConfirm(@RequestParam("title") String title, @RequestParam("text") String text){
        log.info(String.format("Create post: %s,%s",title,text));
        postList.createPost(title, text);
        return "redirect:/list";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam("password") String password, HttpSession session){
        if(password.equals("admin")){
            session.setAttribute("auto", "admin");
        }
        return "redirect:rest/checklogin";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String login(HttpSession session){
        session.invalidate();
        return "redirect:rest/checklogin";
    }



}
