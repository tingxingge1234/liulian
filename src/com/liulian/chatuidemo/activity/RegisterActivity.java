/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liulian.chatuidemo.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.EMError;
import com.easemob.chat.EMChatManager;
import com.liulian.chatuidemo.LiuLianApplication;
import com.easemob.exceptions.EaseMobException;
import com.liulian.chatuidemo.R;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * 注册页
 *
 */
public class RegisterActivity extends BaseActivity {
	private EditText mobileEditText;
	private EditText passwordEditText;
	EditText etSms;
	ImageView iv_back;
	TextView tv_submit;
	Button btn_sms;
	Activity mContext;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		initView();
		setListener();
	}

	private void setListener() {
		register();
		setSMSListener();
	}

	private void setSMSListener() {
		btn_sms.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendMessage();
			}
		});
	}

	private void register() {

	}


	private void initView() {
		mContext = this;
		mobileEditText = (EditText) findViewById(R.id.mobile);
		passwordEditText = (EditText) findViewById(R.id.password);
		etSms = (EditText) findViewById(R.id.code);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		tv_submit = (TextView) findViewById(R.id.tv_submit);
		btn_sms = (Button) findViewById(R.id.btn_sms);
		SMSSDK.initSDK(this, "13aabb2cc6d03", "5381e5b21e6e1fd4003fcd6efe38f9c8");

	}

	/**
	 * 注册
	 *
	 * @param view
	 */
	public void EMRegister(View view) {
		final String username = mobileEditText.getText().toString().trim();
		final String pwd = passwordEditText.getText().toString().trim();
		if (TextUtils.isEmpty(username)) {
			Toast.makeText(this, getResources().getString(R.string.User_name_cannot_be_empty), Toast.LENGTH_SHORT).show();
			mobileEditText.requestFocus();
			return;
		} else if (TextUtils.isEmpty(pwd)) {
			Toast.makeText(this, getResources().getString(R.string.Password_cannot_be_empty), Toast.LENGTH_SHORT).show();
			passwordEditText.requestFocus();
			return;
		}

		if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd)) {
			final ProgressDialog pd = new ProgressDialog(this);
			pd.setMessage(getResources().getString(R.string.Is_the_registered));
			pd.show();

			new Thread(new Runnable() {
				public void run() {
					try {
						// 调用sdk注册方法
						EMChatManager.getInstance().createAccountOnServer(username, pwd);
						runOnUiThread(new Runnable() {
							public void run() {
								if (!RegisterActivity.this.isFinishing())
									pd.dismiss();
								// 保存用户名
								LiuLianApplication.getInstance().setUserName(username);
								Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registered_successfully), Toast.LENGTH_SHORT).show();
								finish();
							}
						});
					} catch (final EaseMobException e) {
						runOnUiThread(new Runnable() {
							public void run() {
								if (!RegisterActivity.this.isFinishing())
									pd.dismiss();
								int errorCode=e.getErrorCode();
								if(errorCode==EMError.NONETWORK_ERROR){
									Toast.makeText(getApplicationContext(), getResources().getString(R.string.network_anomalies), Toast.LENGTH_SHORT).show();
								}else if(errorCode == EMError.USER_ALREADY_EXISTS){
									Toast.makeText(getApplicationContext(), getResources().getString(R.string.User_already_exists), Toast.LENGTH_SHORT).show();
								}else if(errorCode == EMError.UNAUTHORIZED){
									Toast.makeText(getApplicationContext(), getResources().getString(R.string.registration_failed_without_permission), Toast.LENGTH_SHORT).show();
								}else if(errorCode == EMError.ILLEGAL_USER_NAME){
								    Toast.makeText(getApplicationContext(), getResources().getString(R.string.illegal_user_name),Toast.LENGTH_SHORT).show();
								}else{
									Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registration_failed) + e.getMessage(), Toast.LENGTH_SHORT).show();
								}
							}
						});
					}
				}
			}).start();

		}
	}

	public void back(View view) {
		finish();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//注销短信回调
		SMSSDK.unregisterAllEventHandler();
	}
	private void sendMessage() {
		String phoneNumber = mobileEditText.getText().toString();

		SMSSDK.getVerificationCode("86",phoneNumber);
		EventHandler eh=new EventHandler(){

			@Override
			public void afterEvent(int event, int result, final Object data) {

				if (result == SMSSDK.RESULT_COMPLETE) {
					//回调完成
					if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {

						//提交验证码成功
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(mContext,"验证成功", Toast.LENGTH_SHORT).show();
							}
						});
					}else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
						//获取验证码成功
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(mContext,"发送成功", Toast.LENGTH_SHORT).show();
							}
						});
					}else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
						//返回支持发送验证码的国家列表
					}
				}else{
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(mContext,((Throwable) data).getMessage(), Toast.LENGTH_SHORT).show();
						}
					});
				}
			}
		};
		SMSSDK.registerEventHandler(eh); //注册短信回调
	}



}
