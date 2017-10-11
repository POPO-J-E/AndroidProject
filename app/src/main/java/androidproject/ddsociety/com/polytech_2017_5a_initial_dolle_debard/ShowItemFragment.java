package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.dummy.DummyContent;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowItemFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShowItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowItemFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ITEM = "item";

    // TODO: Rename and change types of parameters
    private DummyContent.DummyItem item;

    private OnFragmentInteractionListener mListener;

    public ShowItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param item Parameter 1.
     * @return A new instance of fragment ShowItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowItemFragment newInstance(DummyContent.DummyItem item) {
        ShowItemFragment fragment = new ShowItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.populateItem((DummyContent.DummyItem)getArguments().getSerializable(ARG_ITEM));
        }

        Button btnClose = (Button)getActivity().findViewById(R.id.btn_hide);

        if(btnClose != null)
        {
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hide();
                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_item, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void hide() {
        if (mListener != null) {
            mListener.onFragmentInteraction();
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
        void onFragmentInteraction();
    }

    public void populateItem(DummyContent.DummyItem item)
    {
        this.item = item;

        ((TextView)getActivity().findViewById(R.id.txt_item_id)).setText(item.id);
        ((TextView)getActivity().findViewById(R.id.txt_item)).setText(item.content);
        ((TextView)getActivity().findViewById(R.id.txt_item_details)).setText(item.details);

        Button btnClose = (Button)getActivity().findViewById(R.id.btn_hide);

        if(btnClose != null)
        {
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hide();
                }
            });
        }
    }
}
