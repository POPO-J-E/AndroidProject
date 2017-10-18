package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.model;

import java.io.Serializable;

/**
 * Created by jeremy on 18/10/2017.
 */

public class Answer implements Serializable{

    private String title;

    public Answer(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
