package com.example.examhelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examhelper.utils.DialogManager;
import com.example.examhelper.utils.StatusBarManager;

public class MainPageActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int REQUEST_CODE = 0;
    private TextView title_tv;
    private Button info_btn;
    private Button exam_btn;
    private Button grade_btn;
    private String userName;
    private String gradeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        StatusBarManager.setStatusBarFull(MainPageActivity.this, getWindow(), getResources());

        userName = getIntent().getStringExtra("userName");
        initViews();
    }

    /**
     * 初始化界面
     */
    private void initViews() {
        title_tv = (TextView) findViewById(R.id.title_tv);
        info_btn = (Button) findViewById(R.id.info_btn);
        exam_btn = (Button) findViewById(R.id.exam_btn);
        grade_btn = (Button) findViewById(R.id.grade_btn);



        //设置字体
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/text.ttf");
        title_tv.setTypeface(typeface);

        info_btn.setOnClickListener(this);
        exam_btn.setOnClickListener(this);
        grade_btn.setOnClickListener(this);
        grade_btn.setClickable(false);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.info_btn:
                DialogManager.warningDialogShow(this, "用户信息", userName);
                break;
            case R.id.exam_btn:
                Intent intent = new Intent(MainPageActivity.this, ExamActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.grade_btn:
                DialogManager.warningDialogShow(this, "成绩查询", gradeInfo);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            gradeInfo = data.getStringExtra("grade");
            grade_btn.setClickable(true);
            exam_btn.setClickable(false);
        }
    }
}