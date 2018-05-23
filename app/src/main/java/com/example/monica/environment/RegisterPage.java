package com.example.monica.environment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {
    private Button registerButton,backButton;
    private TextView registerTellNumber,registerPassword,registerPassword2;
    private View registerProgress,registerInputLayout;
    private LinearLayout registerInputTellNumber,registerInputPassword,registerInputPassword2;
    private float mWidth, mHeight;
    private Button btn_register;
    private String tellNumber,password,password2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_regist_page);
        initView();
    }

    private void initView() {
        registerButton = (Button) findViewById(R.id.btn_register);
        backButton = (Button) findViewById(R.id.btn_register_back);
        registerProgress = (View) findViewById(R.id.register_layout_progress);
        registerInputLayout = (View) findViewById(R.id.register_input_layout);
        registerInputTellNumber = (LinearLayout) findViewById(R.id.register_input_tellNumber);
        registerInputPassword = (LinearLayout) findViewById(R.id.register_input_password);
        registerInputPassword2 = (LinearLayout) findViewById(R.id.register_input_password2);
        btn_register = (Button)findViewById(R.id.btn_register);
        //返回登录页面
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterPage.this,LoginPage.class);
                startActivity(intent);
                finish();
            }
        });
        //提交注册信息
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 计算出控件的高与宽
                mWidth = registerButton.getMeasuredWidth();
                mHeight = registerButton.getMeasuredHeight();
                //获取输入的信息
                registerTellNumber = (TextView)findViewById(R.id.register_tellNumber);
                registerPassword = (TextView)findViewById(R.id.register_password);
                registerPassword2 = (TextView)findViewById(R.id.register_password2);
                tellNumber = registerTellNumber.getText().toString();
                password = registerPassword.getText().toString();
                password2 = registerPassword2.getText().toString();
                //检测两次密码输入是否一致
                if(password.equals(password2)){
                    // 隐藏输入框
                    registerTellNumber.setVisibility(View.INVISIBLE);
                    registerPassword.setVisibility(View.INVISIBLE);
                    registerPassword2.setVisibility(View.INVISIBLE);
                    btn_register.setVisibility(View.INVISIBLE);
                    inputAnimator(registerInputLayout, mWidth, mHeight);
                    //进行注册操作


                }else{
                    //进提行示
                    Toast.makeText(RegisterPage.this,"两次输入密码不一致，请重新输入！",Toast.LENGTH_SHORT).show();
                    registerPassword.setFocusable(true);
                }
            }
        });
    }
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

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(registerInputLayout,
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
                registerProgress.setVisibility(View.VISIBLE);
                progressAnimator(registerProgress);
                registerInputLayout.setVisibility(View.INVISIBLE);

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


