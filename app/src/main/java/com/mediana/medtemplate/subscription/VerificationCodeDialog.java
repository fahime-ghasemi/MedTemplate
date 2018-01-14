package com.mediana.medtemplate.subscription;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mediana.medtemplate.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by IT-10 on 1/9/2018.
 */

public class VerificationCodeDialog extends AppCompatDialogFragment {
    @BindView(R.id.btn_send)
    TextView btnSend;

    @BindView(R.id.btn_resend)
    TextView btnResend;

    @BindView(R.id.input)
    EditText input;

    @NonNull
    Listener listener;

    public void setListener(@NonNull Listener listener) {
        this.listener = listener;
    }

    public static VerificationCodeDialog getInstance(@NonNull Listener listener) {
        VerificationCodeDialog instance = new VerificationCodeDialog();
        instance.setListener(listener);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_activiation_code, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_send)
    public void sendClick(View view) {
        // TODO: 1/10/2018 check input
        if (input.getText().toString().equals("")) {
            Toast.makeText(getContext(), R.string.error_activation_code_input, Toast.LENGTH_LONG).show();
        }
        listener.onDoneClick(input.getText().toString());
        dismiss();

    }

    public interface Listener {
         void onDoneClick(String code);
    }
}
