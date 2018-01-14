package com.mediana.medtemplate;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.mediana.medtemplate.events.ShowConnectionProblemEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by IT-10 on 11/28/2017.
 */

public abstract class BaseActivity extends DaggerAppCompatActivity {

    protected abstract int getLayoutResId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onStop();
    }

    protected void setupToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(false);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(false);
            }
        }
    }

    public MedTemplateApplication getMedTemplateApplication() {
        return (MedTemplateApplication) getApplication();
    }

    @Subscribe
    public void onEvent(ShowConnectionProblemEvent event) {
        Toast.makeText(this, event.message, Toast.LENGTH_LONG).show();
//        AlertDialog.Builder builder = new AlertDialog.Builder(this)
//                .setTitle(event.title)
//                .setMessage(event.message)
//                .setNeutralButton(android.R.string.ok, (dialog, which) -> {
//                });

//        if (!event.title.isEmpty()) {
//            builder.setIcon(R.drawable.ic_warning_black);
//        }

//        builder.show();
    }
}
