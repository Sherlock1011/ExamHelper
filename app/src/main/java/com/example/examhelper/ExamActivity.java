package com.example.examhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examhelper.utils.StatusBarManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

public class ExamActivity extends AppCompatActivity {
    private TextView exam_title_tv;
    private QMUILinearLayout exam_layout;
    private FloatingActionButton submit_btn;

    private RadioButton op1, op2, op3, op4;
    private CheckBox mop1, mop2, mop3, mop4, mop5;

    private int score = 0;
    private String level = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        StatusBarManager.setStatusBarFull(ExamActivity.this, getWindow(), getResources());
        initViews();
    }

    /**
     * 初始化界面布局
     */
    private void initViews() {
        exam_title_tv = (TextView) findViewById(R.id.exam_title_tv);
        exam_layout = (QMUILinearLayout) findViewById(R.id.exam_layout);
        submit_btn = (FloatingActionButton) findViewById(R.id.submit_btn);

        op1 = (RadioButton) findViewById(R.id.option1);
        op2 = (RadioButton) findViewById(R.id.option2);
        op3 = (RadioButton) findViewById(R.id.option3);
        op4 = (RadioButton) findViewById(R.id.option4);

        mop1 = (CheckBox) findViewById(R.id.multi_option1);
        mop2 = (CheckBox) findViewById(R.id.multi_option2);
        mop3 = (CheckBox) findViewById(R.id.multi_option3);
        mop4 = (CheckBox) findViewById(R.id.multi_option4);
        mop5 = (CheckBox) findViewById(R.id.multi_option5);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/text.ttf");
        exam_title_tv.setTypeface(typeface);

        exam_layout.setRadius(20);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //计算分数
                calculateGrade();
                QMUIDialog.MessageDialogBuilder messageDialogBuilder = new QMUIDialog.MessageDialogBuilder(ExamActivity.this);
                messageDialogBuilder.setTitle("考试结束")
                        .setMessage("考试成绩:" + score +"\n等级：" + level)
                        .addAction("确定", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                Intent intent = new Intent();
                                intent.putExtra("grade","考试成绩:" + score +"\n等级：" + level);
                                setResult(RESULT_OK,intent);
                                finish();
                                dialog.dismiss();
                            }
                        }).show();
            }
        });
    }

    /**
     * 计算分数
     */
    private void calculateGrade(){
        //计算分数
        if (op1.isChecked()) {
            score += 50;
        }

        if(mop5.isChecked()){
            score += 0;
        }
        else if(mop1.isChecked() && mop2.isChecked() && mop3.isChecked() && mop4.isChecked()){
            score += 50;
        }
        else {
            if (mop1.isChecked()) {
                score += 10;
            }
            if (mop2.isChecked()) {
                score += 10;
            }
            if (mop3.isChecked()) {
                score += 10;
            }
            if (mop4.isChecked()) {
                score += 10;
            }
        }

        //计算等级
        if(score >= 90 && score <= 100){
            level = "优秀";
        }
        else if (score >= 70 && score < 90){
            level = "良好";
        }
        else if (score >= 60 && score < 70){
            level = "合格";
        }
        else {
            level = "不及格";
        }
    }

}