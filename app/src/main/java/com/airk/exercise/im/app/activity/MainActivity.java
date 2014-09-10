package com.airk.exercise.im.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.airk.exercise.im.app.R;
import com.airk.exercise.im.app.YunApplication;
import com.airk.exercise.im.app.adapter.HolderAdapter;
import com.airk.exercise.im.app.bean.ChatUtil;
import com.airk.exercise.im.app.util.AccountUtil;
import com.airk.exercise.im.app.util.LogWrapper;
import com.hisun.phone.core.voice.CCPCall;
import com.hisun.phone.core.voice.Device;
import com.hisun.phone.core.voice.DeviceListener;
import com.hisun.phone.core.voice.listener.OnIMListener;
import com.hisun.phone.core.voice.model.CloopenReason;
import com.hisun.phone.core.voice.model.im.IMTextMsg;
import com.hisun.phone.core.voice.model.im.InstanceMsg;
import com.hisun.phone.core.voice.model.setup.UserAgentConfig;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends Activity implements OnIMListener {
    @InjectView(R.id.msg_input)
    EditText mInput;
    @InjectView(R.id.msg_send)
    Button mSend;
    @InjectView(R.id.recycler)
    RecyclerView mRecycler;
    private HolderAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Device mDevice;
    private final int MSG_CODE = 0x41;
    private final String MSG_KEY = "msg";
    private android.os.Handler mUIHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG_CODE) {
                String m = msg.getData().getString(MSG_KEY);
                ChatUtil chatUtil = new ChatUtil(MainActivity.this, m, true);
                int newPos = mAdapter.addChatMsg(chatUtil);
                mRecycler.smoothScrollToPosition(newPos);
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new HolderAdapter();
        mRecycler.setAdapter(mAdapter);

        CCPCall.init(getApplicationContext(), new CCPCall.InitListener() {
            @Override
            public void onInitialized() {
                LogWrapper.e("SUCCESS");
                System.loadLibrary("serphone");
                Map<String, String> dataMap = new HashMap<String, String>();
                dataMap.put(UserAgentConfig.KEY_IP, "sandboxapp.cloopen.com");
                dataMap.put(UserAgentConfig.KEY_PORT, "8883");
                dataMap.put(UserAgentConfig.KEY_SID, AccountUtil.USER1);
                dataMap.put(UserAgentConfig.KEY_PWD, AccountUtil.USER1_PWD);
                dataMap.put(UserAgentConfig.KEY_SUBID, AccountUtil.USER1_ACCOUNT);
                dataMap.put(UserAgentConfig.KEY_SUBPWD, AccountUtil.USER1_ACC_PWD);
                dataMap.put(UserAgentConfig.KEY_UA, ((YunApplication) getApplication())
                        .getUser_Agent());

                mDevice = CCPCall.createDevice(new DeviceListener() {
                    @Override
                    public void onReceiveEvents(CCPEvents ccpEvents) {
                        LogWrapper.e(ccpEvents.toString());
                    }

                    @Override
                    public void onConnected() {
                        LogWrapper.e("Connected");
                        mSend.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String msg = mInput.getText().toString();
                                if (TextUtils.isEmpty(msg)) {
                                    return;
                                }
                                ChatUtil chat = new ChatUtil(MainActivity.this, msg, false);
                                mDevice.sendInstanceMessage(AccountUtil.USER2,
                                        msg,
                                        null, null);
                                int newPos = mAdapter.addChatMsg(chat);
                                mRecycler.smoothScrollToPosition(newPos);
                                mInput.setText("");
                            }
                        });
                        mDevice.setOnIMListener(MainActivity.this);
                    }

                    @Override
                    public void onDisconnect(Reason reason) {
                        LogWrapper.e("DISCONNECT", reason.toString());
                    }

                    @Override
                    public void onFirewallPolicyEnabled() {
                        LogWrapper.e("Firewall");
                    }
                }, dataMap);
            }

            @Override
            public void onError(Exception e) {
            }
        });
    }

    @Override
    public void onRecordingTimeOut(long l) {

    }

    @Override
    public void onRecordingAmplitude(double v) {

    }

    @Override
    public void onFinishedPlaying() {

    }

    @Override
    public void onSendInstanceMessage(CloopenReason cloopenReason, InstanceMsg instanceMsg) {

    }

    @Override
    public void onDownloadAttached(CloopenReason cloopenReason, String s) {

    }

    @Override
    public void onReceiveInstanceMessage(InstanceMsg instanceMsg) {
        if (instanceMsg instanceof IMTextMsg) {
            ChatUtil chat = new ChatUtil(this, ((IMTextMsg) instanceMsg).getMessage(), true);
            Message msg = new Message();
            msg.what = MSG_CODE;
            Bundle arg = new Bundle();
            arg.putString(MSG_KEY, chat.msg);
            msg.setData(arg);
            mUIHandler.sendMessage(msg);
            LogWrapper.e(chat.msg);
        }
    }

    @Override
    public void onConfirmIntanceMessage(CloopenReason cloopenReason) {

    }
}
