package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.receiver;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by kifkif on 18/10/2017.
 */

public class QuestionResultReceiver extends ResultReceiver {

    private QuestionReceiver receiver;

    public QuestionResultReceiver(Handler handler) {
        super(handler);
    }

    public QuestionResultReceiver setReceiver(QuestionReceiver receiver)
    {
        this.receiver = receiver;
        return this;
    }


    public interface QuestionReceiver {
        public void onReceiveQuestionResult(int resultCode, Bundle resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (receiver != null) {
            receiver.onReceiveQuestionResult(resultCode, resultData);
        }
    }
}
