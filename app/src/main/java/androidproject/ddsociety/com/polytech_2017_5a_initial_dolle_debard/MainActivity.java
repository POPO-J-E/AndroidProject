package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener, QuestionFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuestionFragment fragment = new QuestionFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frangment_container, fragment);
        transaction.commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void changeFragment(){
        MenuFragment newFragment = new MenuFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);

        transaction.replace(R.id.frangment_container, newFragment);
        transaction.commit();
    }
}
