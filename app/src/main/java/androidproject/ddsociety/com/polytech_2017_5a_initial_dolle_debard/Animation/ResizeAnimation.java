package androidproject.ddsociety.com.polytech_2017_5a_initial_dolle_debard.Animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

/**
 * Created by kifkif on 11/10/2017.
 */

public class ResizeAnimation extends Animation {
    final float targetWeight;
    View view;
    float startWeight;

    public ResizeAnimation(View view, float targetWeight) {
        this.view = view;

        this.targetWeight = targetWeight;
        this.startWeight = ((LinearLayout.LayoutParams)view.getLayoutParams()).weight;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float newWeight = (startWeight + (targetWeight-startWeight) * interpolatedTime);
        //to support decent animation, change new heigt as Nico S. recommended in comments
        //int newHeight = (int) (startHeight+(targetHeight - startHeight) * interpolatedTime);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
        lp.weight = newWeight;
        view.setLayoutParams(lp);
        view.getParent().requestLayout();

    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}