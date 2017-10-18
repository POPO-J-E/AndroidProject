package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by kifkif on 18/10/2017.
 */

public class Question implements Serializable {
    private String question;
    private List<Answer> answers;

    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public Question(String question, Answer... answers) {
        this(question, Arrays.asList(answers));
    }

    public Question(String question, String correctAnswer, String... answers) {
        this.question = question;
        this.answers = new ArrayList<>();
        this.answers.add(new Answer(correctAnswer, true));

        for (String answer : answers) {
            this.answers.add(new Answer(answer));
        }

        Collections.shuffle(this.answers);
    }

    public String getQuestion() {
        return question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
