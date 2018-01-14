package com.mediana.medtemplate;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FloatingActionActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView textView;

    @BindView(R.id.text)
    TextView textView2;

    @BindView(R.id.linTitle)
    LinearLayout linearLayout;
    boolean arrowUp=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.image)
    protected void onClick(View view) {
        TransitionManager.beginDelayedTransition(findViewById(R.id.root));
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.rightToLeft = -1;
        layoutParams.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        textView.setLayoutParams(layoutParams);
        //------
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) textView2.getLayoutParams();
        layoutParams2.leftToRight = -1;
        layoutParams2.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams2.topToTop = -1;
        layoutParams2.topToBottom = R.id.image;
        textView2.setLayoutParams(layoutParams2);
        //-------
        ImageView animatedView = (ImageView) findViewById(R.id.arrow);
        if (arrowUp) {
            animatedView.setImageResource(R.drawable.arrow_top_bottom_anim);
            Drawable animation = animatedView.getDrawable();
            if (animation instanceof Animatable) {
                ((Animatable) animation).start();
            }
        } else {
            animatedView.setImageResource(R.drawable.arrow_bottom_top_anim);
            Drawable animation = animatedView.getDrawable();
            if (animation instanceof Animatable) {
                ((Animatable) animation).start();
            }
        }
        arrowUp = !arrowUp;
    }
}
