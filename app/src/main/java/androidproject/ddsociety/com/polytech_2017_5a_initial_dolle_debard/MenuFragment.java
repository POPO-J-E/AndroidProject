package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.model.Answer;
import androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.model.Question;
import androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.service.QuestionService;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Question question;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QuestionService.startActionGetNextQuestion(getContext());

        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageReceiver, new IntentFilter("getNextQuestionAction"));

    }

    private void checkAnswer(Question question, Answer answer) {
        Intent intent = new Intent(getContext(), QuestionService.class);
        intent.putExtra("question",question);
        intent.putExtra("answer",answer);
        QuestionService.startActionCheckQuestion(getContext(), intent);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            question = (Question) intent.getSerializableExtra("question");
            changeQuestionTitle(question);
            changeAnswers(question);
        }
    };

    private void changeAnswers(Question question) {
        Button btn1 = (Button) this.getActivity().findViewById(R.id.btn_answer1);
        Button btn2 = (Button) this.getActivity().findViewById(R.id.btn_answer2);
        Button btn3 = (Button) this.getActivity().findViewById(R.id.btn_answer3);
        Button btn4 = (Button) this.getActivity().findViewById(R.id.btn_answer4);

        btn1.setText(question.getAnswers().get(0).getTitle());
        btn2.setText(question.getAnswers().get(1).getTitle());
        btn3.setText(question.getAnswers().get(2).getTitle());
        btn4.setText(question.getAnswers().get(3).getTitle());
    }

    private void changeQuestionTitle(Question question) {
        TextView textView = (TextView) this.getActivity().findViewById(R.id.question);
        textView.setText(question.getTitle());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);

        rootView.findViewById(R.id.btn_answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(question,question.getAnswers().get(0));
            }
        });
        rootView.findViewById(R.id.btn_answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(question,question.getAnswers().get(1));
            }
        });
        rootView.findViewById(R.id.btn_answer3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(question,question.getAnswers().get(2));
            }
        });
        rootView.findViewById(R.id.btn_answer4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(question,question.getAnswers().get(3));
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    @Override
    public void onDestroy() {
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }
}
