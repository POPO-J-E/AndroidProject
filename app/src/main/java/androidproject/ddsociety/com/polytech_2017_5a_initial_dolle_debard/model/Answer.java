package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.model;

/**
 * Created by kifkif on 18/10/2017.
 */

public class Answer {
    private static int ids = 0;

    private String answer;
    private boolean correct;
    private int id;

    public Answer(String answer, boolean correct) {
        this.answer = answer;
        this.correct = correct;
        this.id = ids++;
    }

    public Answer(String answer) {
        this(answer, false);
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

    public static int getIds() {
        return ids;
    }
}
