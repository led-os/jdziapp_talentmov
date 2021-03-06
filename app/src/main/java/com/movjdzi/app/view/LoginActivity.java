package com.movjdzi.app.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lib.common.util.DataInter;
import com.movjdzi.app.R;
import com.movjdzi.app.model.dto.LoginDto;
import com.movjdzi.app.presenter.LoginPresenter;
import com.movjdzi.app.presenter.iview.IUserView;
import com.movjdzi.app.util.ToastUtil;
import com.movjdzi.app.util.UserUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author huangyong
 * createTime 2019-09-18
 */
public class LoginActivity extends AppCompatActivity implements IUserView {

    @BindView(R.id.backup)
    ImageView backup;
    @BindView(R.id.center_tv)
    TextView centerTv;
    @BindView(R.id.right_view)
    FrameLayout rightView;
    @BindView(R.id.name)
    AutoCompleteTextView name;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.login_bt)
    TextView loginBt;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);
        centerTv.setText("登录");
        TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.bt_layout, rightView, false);
        tv.setText("注册");
        tv.setOnClickListener(v -> {
            RegistActivity.start(LoginActivity.this);
            finish();
        });
        rightView.addView(tv);

        LoginPresenter loginPresenter = new LoginPresenter(this);
        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString())){
                    ToastUtil.showMessage("用户名不能为空");
                    return;
                }
                if (name.getText().toString().length()<6){
                    ToastUtil.showMessage("用户名长度不得少于 6 位");
                    return;
                }
                if (TextUtils.isEmpty(pass.getText().toString())){
                    ToastUtil.showMessage("密码不能为空");
                    return;
                }

                loginPresenter.login(name.getText().toString(),pass.getText().toString());
            }
        });

        backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void loadDone(LoginDto dto) {
        if (dto.getData().getCode()==0){
            ToastUtil.showMessage("登录成功");
            UserUtil.saveUserInfo(this,dto.getData(),new Gson().toJson(dto.getData()));
            sendBroadcast(new Intent(DataInter.KEY.ACTION_REFRESH_COIN));
            finish();
        }else {
            ToastUtil.showMessage("登录失败，"+dto.getData().getTips());
        }


    }

    @Override
    public void loadError() {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadEmpty() {

    }
}
