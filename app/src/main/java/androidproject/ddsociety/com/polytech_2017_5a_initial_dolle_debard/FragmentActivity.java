package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.Animation.ResizeAnimation;
import androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.dummy.DummyContent;

public class FragmentActivity extends AppCompatActivity  implements ShowItemFragment.OnFragmentInteractionListener, ItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        ShowItemFragment showItemFragment = null;
        showItemFragment = (ShowItemFragment) getSupportFragmentManager().findFragmentById(R.id.show_item_fragment);
        View frgViewItem = showItemFragment.getView();
        if(frgViewItem != null) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) frgViewItem.getLayoutParams();
            lp.weight = 0f;
            frgViewItem.setLayoutParams(lp);
            frgViewItem.getParent().requestLayout();
        }
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        openShow();

        ShowItemFragment showItemFragment = null;
        showItemFragment = (ShowItemFragment)getSupportFragmentManager().findFragmentById(R.id.show_item_fragment);
        showItemFragment.populateItem(item);
        showItemFragment.getView();
    }

    @Override
    public void onFragmentInteraction() {
        closeShow();
    }

    protected void closeShow()
    {
        setShowWeight(0f);
    }

    protected void openShow()
    {
        setShowWeight(2f);
    }

    protected void setShowWeight(float weight)
    {
        ShowItemFragment showItemFragment = null;
        showItemFragment = (ShowItemFragment)getSupportFragmentManager().findFragmentById(R.id.show_item_fragment);
        View frgViewShowItem = showItemFragment.getView();

        ItemFragment itemFragment = null;
        itemFragment = (ItemFragment)getSupportFragmentManager().findFragmentById(R.id.item_fragment);
        View frgViewItem = itemFragment.getView();

        if(frgViewShowItem != null && frgViewItem != null)
        {
            ResizeAnimation resize = new ResizeAnimation(frgViewShowItem, weight);
            resize.setDuration(500);
            frgViewShowItem.startAnimation(resize);
        }
    }
}
