package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.model.Question;

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

    private List<Question> questions;

    public QuestionService() {
        super("QuestionService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        questions = new ArrayList<>();
        Question q1 = new Question("blablbalbla?", "blablabla", "aaaz", "zzz", "eee");
        Question q2 = new Question("blablbalbla2?", "blablabla2", "aaaz2", "zzz2", "eee2");
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
    public static void startActionGetQuestion(Context context, int index) {
        Intent intent = new Intent(context, QuestionService.class);
        intent.setAction(ACTION_GET_QUESTION);
        intent.putExtra(EXTRA_QUESTION_INDEX, index);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionCheckAnswer(Context context, int question_index, int answer_id) {
        Intent intent = new Intent(context, QuestionService.class);
        intent.setAction(ACTION_CHECK_ANSWER);
        intent.putExtra(EXTRA_QUESTION_INDEX, question_index);
        intent.putExtra(EXTRA_ANSWER_ID, answer_id);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_QUESTION.equals(action)) {
                final int question_index = intent.getIntExtra(EXTRA_QUESTION_INDEX, 0);
                handleActionGetQuestion(question_index);
            } else if (ACTION_CHECK_ANSWER.equals(action)) {
                final int question_index = intent.getIntExtra(EXTRA_ANSWER_ID, 0);
                final int answer_id = intent.getIntExtra(EXTRA_ANSWER_ID, 0);
                handleActionCheckAnswer(question_index, answer_id);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionGetQuestion(int question_index) {
        // TODO: Handle action Foo
        System.out.println("get question");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionCheckAnswer(int question_index, int answer_id) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
