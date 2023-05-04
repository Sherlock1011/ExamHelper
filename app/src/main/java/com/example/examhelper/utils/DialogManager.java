package com.example.examhelper.utils;

import android.app.Activity;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

public class DialogManager {
    /**
     * 提示对话框
     * 用于提示错误的操作
     * @param activity
     * @param title
     * @param msg
     */
    public static void warningDialogShow(Activity activity, String title, String msg){
        QMUIDialog.MessageDialogBuilder messageDialogBuilder = new QMUIDialog.MessageDialogBuilder(activity);
        messageDialogBuilder.setTitle(title)
                .setMessage(msg)
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                }).show();
    }
}
