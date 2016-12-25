package leoninc.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by Leonid Cheremshantcev on 23.12.16.
 */
@ToString
@AllArgsConstructor
public class Post {
    @Getter private Long id;
    @Getter private String title;
    @Getter private String text;
    @Getter private Date date;


}
