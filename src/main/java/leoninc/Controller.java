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

    private final PostList postList;

    @Autowired
    public Controller(PostList postList) {
        this.postList = postList;
    }


    @RequestMapping("/")
    public String index(@RequestParam(value = "name",required = false, defaultValue = "World") String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping("/list")
    public String getList(Model model, HttpSession session){
        log.info("Get list. List size:"+postList.size());
        model.addAttribute("list",postList);
        if(checkAuto(session)) {
            model.addAttribute("permission", "admin");
        }   else {
            model.addAttribute("permission", "guest");
        }
        return "list";
    }

    @RequestMapping(value = "/createPost", method = RequestMethod.GET)
    public String createPostForm(HttpSession session){
        if(session.getAttribute("auto") == null || !session.getAttribute("auto").toString().equals("admin")){
            return "redirect:/rest/checklogin";
        }
        return "createPostForm";
    }

    @RequestMapping(value = "/createPost", method = RequestMethod.POST)
    public String createPostConfirm(@RequestParam("title") String title,
                                    @RequestParam("text") String text,
                                    HttpSession session){
        if(session.getAttribute("auto") == null || !session.getAttribute("auto").toString().equals("admin")){
            return "redirect:/rest/checklogin";
        }
        log.info(String.format("Create post: %s,%s",title,text));
        postList.createPost(title, text);
        return "redirect:/list";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam("password") String password, HttpSession session){
        if(password.equals("admin")){
            log.info("Admin is login");
            session.setAttribute("auto", "admin");
        } else {
            log.warn("Somebody tried to login with incorrect password");
        }
        return "redirect:rest/checklogin";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String login(HttpSession session){
        log.info("Admin is logout");
        session.invalidate();
        return "redirect:rest/checklogin";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deletePost(@RequestParam("id") Long id, HttpSession session){
        if(!checkAuto(session)){
            return "redirect:rest/checklogin";
        }
        postList.remove(id);
        return "redirect:/list";
    }


    private boolean checkAuto(HttpSession session){
        return session.getAttribute("auto") != null && session.getAttribute("auto").toString().equals("admin");
    }


}
