package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by kifkif on 18/10/2017.
 */

public class Question implements Serializable {
    private String question;
    private List<Answer> answers;

    public Question()
    {

    }

    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
        Collections.shuffle(this.answers);
    }

    public Question(String question, Answer... answers) {
        this(question, Arrays.asList(answers));
    }

    public String getQuestion() {
        return question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answer findAnswer(int id) throws Exception {
        System.out.println(question);
        for (Answer answer: answers)
        {
            System.out.println(answer.getId());
            if(answer.getId() == id)
                return answer;
        }

        throw new Exception("cant find answer with id "+id);
    }
}
