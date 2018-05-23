package com.example.monica.environment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class LoginPage extends AppCompatActivity{
    private TextView mBtnLogin;
    private View progress,mInputLayout;
    private float mWidth, mHeight;
    private LinearLayout mName, mPsw;
    private Button newUser,login_back;
    private TextView userNameView,passwordView;
    private String userName,password;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_page);
        initView();
        //返回键按钮
        login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //注册按钮
        sharedPreferences = getSharedPreferences("SomeInformation", Context.MODE_PRIVATE);
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this,RegisterPage.class);
                startActivity(intent);
                finish();
            }
        });
        //登录按钮
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 计算出控件的高与宽
                mWidth = mBtnLogin.getMeasuredWidth();
                mHeight = mBtnLogin.getMeasuredHeight();
                userNameView = (TextView)findViewById(R.id.login_username);
                passwordView = (TextView)findViewById(R.id.login_password);
                userName = userNameView.getText().toString();
                password = passwordView.getText().toString();
                if (userName.equals("admin")&&password.equals("123456")){
                    //保存用户登录信息
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userName",userName);
                    editor.putString("password",password);
                    editor.commit();
                    // 隐藏输入框
                    mName.setVisibility(View.INVISIBLE);
                    mPsw.setVisibility(View.INVISIBLE);
                    mBtnLogin.setVisibility(View.INVISIBLE);
                    inputAnimator(mInputLayout, mWidth, mHeight);
                    //延时5秒跳转至主页面
                    toMainPage();
                }else{

                }
            }
        });
    }


    private void initView() {
        mBtnLogin = (TextView) findViewById(R.id.btn_login);
        progress = findViewById(R.id.login_layout_progress);
        mInputLayout = findViewById(R.id.login_input_layout);
        mName = (LinearLayout) findViewById(R.id.login_input_username);
        mPsw = (LinearLayout) findViewById(R.id.login_input_password);
        newUser = (Button)findViewById(R.id.newUser);
        login_back = (Button)findViewById(R.id.login_back);
    }

    //第一种延时跳转方法
    public void toMainPage(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoginPage.this,MainActivity.class);
                startActivityForResult(intent,RESULT_OK);
                finish();
            }
        }, 3000);
    }
    //第二种延时跳转方法
    public void toMainPage2(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(LoginPage.this,MainActivity.class);
                startActivityForResult(intent,RESULT_OK);
            }
        };
        timer.schedule(task, 3000);
    }
    ////////
    /**
     * 输入框的动画效果
     *
     * @param view
     *            控件
     * @param w
     *            宽
     * @param h
     *            高
     */
    private void inputAnimator(final View view, float w, float h) {

        AnimatorSet set = new AnimatorSet();

        ValueAnimator animator = ValueAnimator.ofFloat(0, w);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view
                        .getLayoutParams();
                params.leftMargin = (int) value;
                params.rightMargin = (int) value;
                view.setLayoutParams(params);
            }
        });

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout,
                "scaleX", 1f, 0.5f);
        set.setDuration(1000);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.playTogether(animator, animator2);
        set.start();
        set.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                /**
                 * 动画结束后，先显示加载的动画，然后再隐藏输入框
                 */
                progress.setVisibility(View.VISIBLE);
                progressAnimator(progress);
                mInputLayout.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });

    }
    /**
     * 出现进度动画
     *
     * @param view
     */
    private void progressAnimator(final View view) {
        PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
                0.5f, 1f);
        PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
                0.5f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(view,
                animator, animator2);
        animator3.setDuration(1000);
        animator3.setInterpolator(new JellyInterpolator());
        animator3.start();

    }

}
