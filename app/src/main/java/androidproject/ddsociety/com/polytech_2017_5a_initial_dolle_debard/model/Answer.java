package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.model;

import java.io.Serializable;

/**
 * Created by kifkif on 18/10/2017.
 */

public class Answer implements Serializable{

    private String answer;
    private boolean correct;
    private int id;

    public Answer()
    {

    }

    public Answer(int id, String answer, boolean correct) {
        this.answer = answer;
        this.correct = correct;
        this.id = id;
    }

    public Answer(int id, String answer) {
        this(id, answer, false);
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public int getId() {
        return id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public void setId(int id) {
        this.id = id;
    }
}
