package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.dummy.Beer;

public class MainActivity extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener, QuestionFragment.OnFragmentInteractionListener, BeerFragment.OnListFragmentInteractionListener{

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.context = getApplicationContext();
        setContentView(R.layout.activity_main);
        BeerFragment fragment = new BeerFragment();
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

    @Override
    public void onListFragmentInteraction(Beer item) {

    }

    public static Context getAppContext() {
        return MainActivity.context;
    }
}
