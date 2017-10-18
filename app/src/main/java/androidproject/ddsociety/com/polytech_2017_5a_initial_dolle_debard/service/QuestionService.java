package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.model.Answer;
import androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.model.Question;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class QuestionService extends IntentService {

    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String GET_NEXT_QUESTION = "getNextQuestionAction";
    private static final String CHECK_ANSWER = "checkAnswerAction";

    private List<Question> questions;

    private static int counter = 0;
//    private static final String EXTRA_PARAM1 = "androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.service.extra.PARAM1";
//    private static final String EXTRA_PARAM2 = "androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.service.extra.PARAM2";

    public QuestionService() {
        super("QuestionService");

        // Initialisation to perform the practice
        Answer answer1 = new Answer("1");
        Answer answer2 = new Answer("2");
        Answer answer3 = new Answer("3");
        Answer answer4 = new Answer("4");
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);

        Question question1 = new Question("Quel est le nombre le plus petit ?", answers, answer1);
        Question question2 = new Question("Quel est le nombre le plus grand ?", answers, answer4);

        questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        // end of init
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionGetNextQuestion(Context context) {
        Intent intent = new Intent(context, QuestionService.class);
        intent.setAction(GET_NEXT_QUESTION);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionCheckQuestion(Context context, Intent intent) {
//        Intent intent = new Intent(context, QuestionService.class);
        intent.setAction(CHECK_ANSWER);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (GET_NEXT_QUESTION.equals(action)) {
                handleActionGetNextQuestion(questions);
            } else if (CHECK_ANSWER.equals(action)) {
                Answer choicedAnswer = (Answer) intent.getSerializableExtra("answer");
                Question choicedQuestion = (Question) intent.getSerializableExtra("question");
                startActionCheckQuestion(choicedQuestion,choicedAnswer);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionGetNextQuestion(List<Question> questions) {
        Question choicedQuestion = questions.get(counter);
        counter++;
        sendMessage(choicedQuestion);
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void startActionCheckQuestion(Question choicedQuestion, Answer choicedAnswer) {
        if (Objects.equals(choicedQuestion.getGoodAnswer().getTitle(), choicedAnswer.getTitle()))
            System.out.println("bravoooooooooooooooooooo");
        else
            System.out.println("You lose !");
    }

    private void sendMessage(Question choicedQuestion) {
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent("getNextQuestionAction");
        intent.putExtra("question", (Serializable) choicedQuestion);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
