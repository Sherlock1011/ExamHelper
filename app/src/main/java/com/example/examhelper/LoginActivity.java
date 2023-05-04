package com.example.examhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examhelper.utils.DialogManager;
import com.example.examhelper.utils.StatusBarManager;

public class LoginActivity extends AppCompatActivity {
    private EditText user_name_input;
    private EditText pwd_input;
    private Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarManager.setStatusBarFull(LoginActivity.this, getWindow(),getResources());
        initViews();
    }

    /**
     * 初始化界面
     */
    private void initViews() {
        user_name_input = (EditText) findViewById(R.id.user_name_input);
        pwd_input = (EditText) findViewById(R.id.pwd_input);
        login_btn = (Button) findViewById(R.id.login_btn);

        user_name_input.setText("2023062200");
        pwd_input.setText("111111");

        //设置登录按钮点击事件
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    /**
     * 验证登录
     */
    private void login() {
        String userName = user_name_input.getText().toString();
        String pwd = pwd_input.getText().toString();
        if(userName.equals("") || pwd.equals("")){
            DialogManager.warningDialogShow(this,"提示", "用户名或者密码不能为空！");
        }
        else if(userName.equals("2023062200") && pwd.equals("111111")){
            Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);
            intent.putExtra("userName", userName);
            startActivity(intent);
        }
        else {
            DialogManager.warningDialogShow(this, "提示","用户名或密码不正确");
        }
    }
}