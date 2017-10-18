package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import java.util.ArrayList;
import java.util.List;

import androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.model.Answer;
import androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.model.Question;
import androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.receiver.QuestionResultReceiver;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class QuestionService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_GET_QUESTION = "androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.service.action.get_question";
    private static final String ACTION_CHECK_ANSWER = "androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.service.action.check_answer";

    // TODO: Rename parameters
    private static final String EXTRA_QUESTION_INDEX = "androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.service.extra.extra_question_index";
    private static final String EXTRA_ANSWER_ID = "androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.service.extra.extra_answer_id";
    private static final String EXTRA_RECEIVER = "androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.service.extra.receiver";

    public static final int CODE_GET_QUESTION = 1;
    public static final int CODE_CHECK_ANSWER = 2;
    public static final int CODE_ERROR = 3;

    public static final String ARG_QUESTION = "androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.service.arg.question";
    public static final String ARG_ANSWER = "androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.service.arg.answer";

    private QuestionResultReceiver receiver;

    private List<Question> questions;

    public QuestionService() {
        super("QuestionService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        questions = new ArrayList<>();
        Question q1 = new Question("blablbalbla?", new Answer(1, "blablabla"), new Answer(2, "aaaz"), new Answer(3, "zzz"), new Answer(4, "eee"));
        Question q2 = new Question("blablbalbla22222?", new Answer(5, "blablabla22"), new Answer(6, "aaa22z"), new Answer(7, "zz22z"), new Answer(8, "e22ee"));
        questions.add(q1);
        questions.add(q2);
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionGetQuestion(Context context, int index, QuestionResultReceiver.QuestionReceiver receiver) {
        Intent intent = new Intent(context, QuestionService.class);
        intent.setAction(ACTION_GET_QUESTION);
        intent.putExtra(EXTRA_QUESTION_INDEX, index);
        intent.putExtra(EXTRA_RECEIVER, new QuestionResultReceiver(new Handler()).setReceiver(receiver));
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionCheckAnswer(Context context, int question_index, int answer_id, QuestionResultReceiver.QuestionReceiver receiver) {
        Intent intent = new Intent(context, QuestionService.class);
        intent.setAction(ACTION_CHECK_ANSWER);
        intent.putExtra(EXTRA_QUESTION_INDEX, question_index);
        intent.putExtra(EXTRA_ANSWER_ID, answer_id);
        intent.putExtra(EXTRA_RECEIVER, new QuestionResultReceiver(new Handler()).setReceiver(receiver));
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_QUESTION.equals(action)) {
                final int question_index = intent.getIntExtra(EXTRA_QUESTION_INDEX, 0);
                final ResultReceiver receiver = intent.getParcelableExtra(EXTRA_RECEIVER);
                handleActionGetQuestion(question_index, receiver);
            } else if (ACTION_CHECK_ANSWER.equals(action)) {
                final int question_index = intent.getIntExtra(EXTRA_QUESTION_INDEX, 0);
                final int answer_id = intent.getIntExtra(EXTRA_ANSWER_ID, 0);
                final ResultReceiver receiver = intent.getParcelableExtra(EXTRA_RECEIVER);
                handleActionCheckAnswer(question_index, answer_id, receiver);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionGetQuestion(int question_index, ResultReceiver receiver) {
        Bundle bundle = new Bundle();
        Question question = this.questions.get(question_index);
        bundle.putSerializable(ARG_QUESTION, question);
        receiver.send(CODE_GET_QUESTION, bundle);
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionCheckAnswer(int question_index, int answer_id, ResultReceiver receiver) {
        Bundle bundle = new Bundle();
        Question question = this.questions.get(question_index);
        Answer answer;
        try {
            answer = question.findAnswer(answer_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        bundle.putBoolean(ARG_ANSWER, answer.isCorrect());

        receiver.send(CODE_CHECK_ANSWER, bundle);
//        throw new UnsupportedOperationException("Not yet implemented");
    }
}
