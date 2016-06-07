package com.liulian.chatuidemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.liulian.chatuidemo.R;

public class LoginRegisterActivity extends Activity {
    Button btnLogin,btnRegister;
    ImageView iv_login_qq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginregister);
        initView();
        listener();
    }

    private void listener() {
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(LoginRegisterActivity.this,LoginActivity.class),0);
            }
        });
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(LoginRegisterActivity.this,RegisterActivity.class),0);
            }
        });
    }


    private void initView() {
        btnLogin = (Button) findViewById(R.id.login);
        btnRegister = (Button) findViewById(R.id.register);
    }
}
