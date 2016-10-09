package com.gcy.offsitelanding.offsitelanding;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity {

    String url = "http://169.254.97.133:8080/offsitelanding_server/LoginServlet";
    /**
     * @ViewInject注解
     * xUtils使用注解方式就可以进行UI，资源的绑定，替代findViewById()
     */
    @ViewInject(R.id.login_username)
    private EditText loginUserName;
    @ViewInject(R.id.login_pwd)
    private EditText loginPwd;
    @ViewInject(R.id.login)
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        init();
    }
    public void init(){
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = new RequestParams(url);
                params.addParameter("username" , loginUserName.getText().toString());
                params.addParameter("password" , loginPwd.getText().toString());
                params.addParameter("registrationId" , JPushInterface.getRegistrationID(MainActivity.this));
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(MainActivity.this , "登录成功" , Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(MainActivity.this , "登陆失败" , Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            }
        });

        //利用Intent判断是否有自定义消息
        String message = getIntent().getStringExtra("MessageContent");
        if(message != null && !message.equals("")){
            new AlertDialog.Builder(this).setTitle("系统提示").setMessage(message).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //在这里可清除本地的用户信息
                }
            }).setNegativeButton("重新登录", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //再次执行登录操作
                }
            }).show();
        }
    }
}
