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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.easemob.EMCallBack;
import com.liulian.chatuidemo.R;
import com.liulian.chatuidemo.applib.controller.HXSDKHelper;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.liulian.chatuidemo.Constant;
import com.liulian.chatuidemo.LiuLianApplication;
import com.liulian.chatuidemo.DemoHXSDKHelper;
import com.liulian.chatuidemo.bean.LoginUser;
import com.liulian.chatuidemo.data.GsonRequest;
import com.liulian.chatuidemo.db.UserDao;
import com.liulian.chatuidemo.domain.EMUser;
import com.liulian.chatuidemo.LiuLian;
import com.liulian.chatuidemo.utils.CommonUtils;

/**
 * 登陆页面
 * 
 */
public class LoginActivity extends BaseActivity {
	private static final String TAG = "LoginActivity";
	public static final int REQUEST_CODE_SETNICK = 1;
	private EditText usernameEditText;
	private EditText passwordEditText;
	private ImageView iv_login;

	private boolean progressShow;
	private boolean autoLogin = false;

	private String currentUsername;
	private String currentPassword;

	Activity mContext;
	ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 如果用户名密码都有，直接进入主页面
		if (DemoHXSDKHelper.getInstance().isLogined()) {
			autoLogin = true;
			startActivity(new Intent(LoginActivity.this, MainActivity.class));

			return;
		}
		setContentView(R.layout.activity_login);
		mContext= this;

		usernameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);
		iv_login = (ImageView) findViewById(R.id.iv_login);


		setListener();
		if (LiuLianApplication.getInstance().getUserName() != null) {
			usernameEditText.setText(LiuLianApplication.getInstance().getUserName());
		}
	}

	private void setListener() {
		setUserNameTextChangedListener();
		setLoginClickListener();
	}
	/**
	 * 登录
	 *
	 */
	private void setLoginClickListener() {
			findViewById(R.id.iv_login).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (!CommonUtils.isNetWorkConnected(mContext)) {
						Toast.makeText(mContext, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
						return;
					}
					currentUsername = usernameEditText.getText().toString().trim();
					currentPassword = passwordEditText.getText().toString().trim();

					if (TextUtils.isEmpty(currentUsername)) {
						Toast.makeText(mContext, R.string.User_name_cannot_be_empty, Toast.LENGTH_SHORT).show();
						return;
					}
					if (TextUtils.isEmpty(currentPassword)) {
						Toast.makeText(mContext, R.string.Password_cannot_be_empty, Toast.LENGTH_SHORT).show();
						return;
					}

					setProgressShow();
					final long start = System.currentTimeMillis();
					// 调用sdk登陆方法登陆聊天服务器
					EMChatManager.getInstance().login(currentUsername, currentPassword, new EMCallBack() {

						@Override
						public void onSuccess() {
							if (!progressShow) {
								return;
							}
							Log.e(TAG, "login currentUsername" + currentUsername);
							Log.e(TAG, "login currentPassword" + currentPassword);
							loginAppServer();
							// 登陆成功，保存用户名密码
							LiuLianApplication.getInstance().setUserName(currentUsername);
							LiuLianApplication.getInstance().setPassword(currentPassword);

							try {
								// ** 第一次登录或者之前logout后再登录，加载所有本地群和回话
								// ** manually load all local groups and
								EMGroupManager.getInstance().loadAllGroups();
								EMChatManager.getInstance().loadAllConversations();
								// 处理好友和群组
								initializeContacts();
							} catch (Exception e) {
								e.printStackTrace();
								// 取好友或者群聊失败，不让进入主页面
								runOnUiThread(new Runnable() {
									public void run() {
										pd.dismiss();
										DemoHXSDKHelper.getInstance().logout(true,null);
										Toast.makeText(getApplicationContext(), R.string.login_failure_failed, Toast.LENGTH_LONG).show();
									}
								});
								return;
							}
							// 更新当前用户的nickname 此方法的作用是在ios离线推送时能够显示用户nick
							boolean updatenick = EMChatManager.getInstance().updateCurrentUserNick(
									LiuLianApplication.currentUserNick.trim());
							if (!updatenick) {
								Log.e("LoginActivity", "update current user nick fail");
							}
							if (!LoginActivity.this.isFinishing() && pd.isShowing()) {
								pd.dismiss();
							}
							// 进入主页面
							Intent intent = new Intent(LoginActivity.this,
									MainActivity.class);
							startActivity(intent);

							finish();
						}

						@Override
						public void onProgress(int progress, String status) {
						}

						@Override
						public void onError(final int code, final String message) {
							if (!progressShow) {
								return;
							}
							runOnUiThread(new Runnable() {
								public void run() {
									pd.dismiss();
									Toast.makeText(getApplicationContext(), getString(R.string.Login_failed) + message,
											Toast.LENGTH_SHORT).show();
								}
							});
						}
					});
				}
			});

	}

	private void loginAppServer() {
		String path = "http://api.durian.haomee.cn/?pf=1&android_version=19&app_version=215&app_channel=%E8%85%BE%E8%AE%AF%E5%BA%94%E7%94%A8%E5%AE%9D&app_language=zh&m=User&a=phoneLogin&mobile=13552005525&password=5319bf4ef8f5029ec32a4ad62a3f8eff";
		executeRequest(new GsonRequest<LoginUser>(path, LoginUser.class, responseListener(), errorListener()));
	}

	private Response.Listener<LoginUser> responseListener() {
		return new Response.Listener<LoginUser>() {
			@Override
			public void onResponse(LoginUser loginUser) {
				saveUser(loginUser);
				Log.e(TAG, "login loginuser" + loginUser);
			}
		};
	}

	private void saveUser(LoginUser loginUser) {
		LiuLian instance = LiuLian.getInstance();
		instance.setLoginUser(loginUser);
	}

	private void setProgressShow() {
		progressShow = true;
		pd = new ProgressDialog(LoginActivity.this);
		pd.setCanceledOnTouchOutside(false);
		pd.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				progressShow = false;
			}
		});
		pd.setMessage(getString(R.string.Is_landing));
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				pd.show();
			}
		});
	}

	private void setUserNameTextChangedListener() {
		// 如果用户名改变，清空密码
		usernameEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				passwordEditText.setText(null);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}



	private void initializeContacts() {
		Map<String, EMUser> userlist = new HashMap<String, EMUser>();
		// 添加user"申请与通知"
		EMUser newFriends = new EMUser();
		newFriends.setUsername(Constant.NEW_FRIENDS_USERNAME);
		String strChat = getResources().getString(
				R.string.Application_and_notify);
		newFriends.setNick(strChat);

		userlist.put(Constant.NEW_FRIENDS_USERNAME, newFriends);
		// 添加"群聊"
		EMUser groupUser = new EMUser();
		String strGroup = getResources().getString(R.string.group_chat);
		groupUser.setUsername(Constant.GROUP_USERNAME);
		groupUser.setNick(strGroup);
		groupUser.setHeader("");
		userlist.put(Constant.GROUP_USERNAME, groupUser);
		
		// 添加"Robot"
		EMUser robotUser = new EMUser();
		String strRobot = getResources().getString(R.string.robot_chat);
		robotUser.setUsername(Constant.CHAT_ROBOT);
		robotUser.setNick(strRobot);
		robotUser.setHeader("");
		userlist.put(Constant.CHAT_ROBOT, robotUser);
		
		// 存入内存
		((DemoHXSDKHelper) HXSDKHelper.getInstance()).setContactList(userlist);
		// 存入db
		UserDao dao = new UserDao(LoginActivity.this);
		List<EMUser> users = new ArrayList<EMUser>(userlist.values());
		dao.saveContactList(users);
	}
	
	/**
	 * 注册
	 * 
	 * @param view
	 */
	public void register(View view) {
		startActivityForResult(new Intent(this, RegisterActivity.class), 0);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (autoLogin) {
			return;
		}
	}
}
