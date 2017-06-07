package com.why.project.acachedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.why.project.acachedemo.utils.ACache;
import com.why.project.acachedemo.utils.Globals;

public class MainActivity extends AppCompatActivity {

	private EditText mUsernameEdt;
	private Button mLoginBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		initDatas();
		initEvents();
	}

	//初始化控件
	private void initViews(){
		mUsernameEdt = (EditText) findViewById(R.id.edt_username);
		mLoginBtn = (Button) findViewById(R.id.btn_login);
	}

	//初始化数据
	private void initDatas(){
		//判断是否缓存了用户名，如果是的话，读取缓存的用户名并填充到输入框中
		initNamePwdFromCache();
	}

	//初始化事件
	private void initEvents(){
		mLoginBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String userName = mUsernameEdt.getText().toString();
				//缓存用户名
				ACache.get(MainActivity.this).put(Globals.USERNAME_KEY,userName);
				Toast.makeText(MainActivity.this,"已缓存用户名："+userName,Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * 从缓存中查询用户名是否保存，并加载用户名
	 * */
	private void initNamePwdFromCache() {
		//有缓存文件
		String userNameCache = ACache.get(this).getAsString(Globals.USERNAME_KEY);
		if (userNameCache != null) {
			mUsernameEdt.setText(userNameCache);
			Toast.makeText(MainActivity.this,"已加载缓存的用户名："+userNameCache,Toast.LENGTH_SHORT).show();
		}
	}
}
