package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jeremy on 18/10/2017.
 */

public class Question implements Serializable{
    //Fields
    private String title;
    private List<Answer> answers;
    private Answer goodAnswer;

    public Question(String title, List<Answer> answers, Answer goodAnswer) {
        this.title = title;
        this.answers = answers;
        this.goodAnswer = goodAnswer;
    }

    public Question() {
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<Answer> getAnswers() {
        return answers;
    }
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
    public Answer getGoodAnswer() {
        return goodAnswer;
    }
    public void setGoodAnswer(Answer goodAnswer) {
        this.goodAnswer = goodAnswer;
    }


}
