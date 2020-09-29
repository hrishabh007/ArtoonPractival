package com.app.artoonpractival;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.artoonpractival.CommonUtils.PrefKeys;
import com.app.artoonpractival.CommonUtils.Prefs;
import com.app.artoonpractival.Ui.RecyclerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.login_emailid)
    EditText loginEmailid;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.forgot_password)
    TextView forgotPassword;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.login_layout)
    LinearLayout loginLayout;
    @BindView(R.id.createAccount)
    TextView createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (MyApp.isLogin()) {
            Intent intent = new Intent(getBaseContext(), RecyclerActivity.class);
            startActivity(intent);
            finish();

        }
    }


    @OnClick(R.id.loginBtn)
    public void onViewClicked() {

        String email = loginEmailid.getText().toString().trim();
        String pass = loginPassword.getText().toString().trim();

        if (email.equals("rishabh@gmail.com") && pass.equals("12345678")) {
            Prefs.putString("email", "rishabh@gmail.com");
            Prefs.putInt("id", 1);
            Prefs.putBoolean(PrefKeys.PREF_IS_LOGIN, true);
            Intent i = new Intent(MainActivity.this, RecyclerActivity.class);
            startActivity(i);
            finish();
        } else if (email.equals("admin@gmail.com") && pass.equals("12345678")) {
            Prefs.putString("email", "admin@gmail.com");
            Prefs.putBoolean(PrefKeys.PREF_IS_LOGIN, true);
            Prefs.putInt("id", 2);
            Intent i = new Intent(MainActivity.this, RecyclerActivity.class);
            startActivity(i);
            finish();
        }
    }
}