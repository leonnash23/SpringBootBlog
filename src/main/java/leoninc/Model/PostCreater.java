package leoninc.Model;

import java.util.Date;

/**
 * Created by Leonid Cheremshantcev on 23.12.16.
 */
class PostCreater {
    private Long nextId = 0L;

    private Long getNextId() {
        return nextId++;
    }

    Post createPost(String title, String text){
        return new Post(getNextId(),title,text,new Date());
    }
}
