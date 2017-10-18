package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.model.Answer;
import androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.model.Question;
import androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.receiver.QuestionResultReceiver;
import androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.service.QuestionService;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment implements QuestionResultReceiver.QuestionReceiver {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_QUESTION = "question";

    // TODO: Rename and change types of parameters
    private Question question;
    private int question_index;

    private OnFragmentInteractionListener mListener;

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param question Parameter
     * @return A new instance of fragment QuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(Question question) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_QUESTION, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Serializable qSer = getArguments().getSerializable(ARG_QUESTION);
            if(qSer instanceof Question)
            {
                question = (Question)qSer;
            }
            else
            {
                throw new RuntimeException("question arg must be instance of Question");
            }
        }

        question_index = 0;
        QuestionService.startActionGetQuestion(this.getContext(), question_index, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        // Inflate the layout for this fragment

        ((Button)view.findViewById(R.id.question_btn_1)).setOnClickListener(new AnswerButtonListener(0, this));
        ((Button)view.findViewById(R.id.question_btn_2)).setOnClickListener(new AnswerButtonListener(1, this));
        ((Button)view.findViewById(R.id.question_btn_3)).setOnClickListener(new AnswerButtonListener(2, this));
        ((Button)view.findViewById(R.id.question_btn_4)).setOnClickListener(new AnswerButtonListener(3, this));

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onAnswer(int id) {
        QuestionService.startActionCheckAnswer(this.getContext(), question_index, question.getAnswers().get(id).getId(), this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onReceiveQuestionResult(int resultCode, Bundle resultData) {
        System.out.println("receive question result "+resultCode);

        switch (resultCode) {
            case QuestionService.CODE_GET_QUESTION:
                Serializable qSer = resultData.getSerializable(QuestionService.ARG_QUESTION);
                if(qSer instanceof Question)
                {
                    question = (Question)qSer;
                }
                else
                {
                    throw new RuntimeException("question arg must be instance of Question");
                }
                updateQuestionView();
                break;
            case QuestionService.CODE_CHECK_ANSWER:
                boolean correct = resultData.getBoolean(QuestionService.ARG_ANSWER);
                System.out.println("question "+correct);
                getNextQuestion();
                break;
            case QuestionService.CODE_ERROR:
                /* Handle the error */
                System.out.println("error");
                break;
        }
    }

    private void getNextQuestion() {
        this.question_index++;
        QuestionService.startActionGetQuestion(this.getContext(), question_index, this);
    }

    private void updateQuestionView() {
        TextView questionView = (TextView)getActivity().findViewById(R.id.question_title);
        questionView.setText(question.getQuestion());

        List<Answer> answers = question.getAnswers();
        ((Button)getActivity().findViewById(R.id.question_btn_1)).setText(answers.get(0).getAnswer());
        ((Button)getActivity().findViewById(R.id.question_btn_2)).setText(answers.get(1).getAnswer());
        ((Button)getActivity().findViewById(R.id.question_btn_3)).setText(answers.get(2).getAnswer());
        ((Button)getActivity().findViewById(R.id.question_btn_4)).setText(answers.get(3).getAnswer());
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class AnswerButtonListener implements View.OnClickListener{

        private int id;
        private QuestionFragment question;

        public AnswerButtonListener(int id, QuestionFragment question) {
            this.id = id;
            this.question = question;
        }

        @Override
        public void onClick(View view) {
            question.onAnswer(id);
        }
    }
}
