package com.tiemuyu.chuanchuan.activity.chat_tools.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import java.util.ArrayList;
import java.util.List;

import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.chat_tools.adapter.ContactsAdapter;
import com.tiemuyu.chuanchuan.activity.chat_tools.bean.Contacts;
import com.tiemuyu.chuanchuan.activity.chat_tools.fragment.TextMessageActivity;
import com.tiemuyu.chuanchuan.activity.chat_tools.utils.HistoryMessage;
import com.tiemuyu.chuanchuan.activity.chat_tools.video.VideoMessageHelper;
import com.tiemuyu.chuanchuan.activity.util.DataSharedPress;

/**
 * describe :
 * Created by FUYING on 2016/6/8.
 * PackageName com.guanjia800.clientApp.chat_tools.activity.
 * ProjectName HouseKeeper_android.
 */
public class MessageActivity extends Activity implements AdapterView.OnItemClickListener {

    private View rootView;
    public static String HEADER_URL = "";
    private LinearLayout goBack;
    private ListView kefu_list;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    TextMessageActivity fragment;
    //    private UnReadChange unReadChange;
    private boolean isLogin = true;
    private HistoryMessage historyMessage = new HistoryMessage();

    private ArrayList<Contacts> dataList = new ArrayList<>();
    Contacts contacts;
    private DataSharedPress sharedPress;
    // 聊天对象
    protected String sessionId; // p2p对方Account或者群id
    protected SessionTypeEnum sessionType;
    private VideoMessageHelper videoMessageHelper;
    private ContactsAdapter adapter;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_activity);//设置状态栏为透明
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        Bundle bu = getIntent().getBundleExtra("bundle");
        dataList = bu.getParcelableArrayList("data");
        sharedPress = DataSharedPress.getSharedPress(this);
        registerObservers(incomingMessageObserver, true);
        adapter = new ContactsAdapter(this, dataList);
        kefu_list = (ListView) findViewById(R.id.kefu_list);
        kefu_list.setAdapter(adapter);
        kefu_list.setOnItemClickListener(this);
        goBack = (LinearLayout) findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void registerObservers(Observer<List<IMMessage>> immessage, boolean register) {
        MsgServiceObserve service = NIMClient.getService(MsgServiceObserve.class);
        service.observeReceiveMessage(immessage, register);
    }

    /**
     * 消息接收观察者
     */
    Observer<List<IMMessage>> incomingMessageObserver = new Observer<List<IMMessage>>() {
        @Override
        public void onEvent(List<IMMessage> messages) {
            if (messages == null || messages.isEmpty()) {
                return;
            }
            kefu_list.setAdapter(adapter);
        }
    };

    public static void checkVisibility(View view, boolean is) {
        if (!view.isShown() && is) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        NIMClient.getService(MsgService.class)
                .setChattingAccount(MsgService.MSG_CHATTING_ACCOUNT_ALL, SessionTypeEnum.None);
    }

    @Override
    protected void onPause() {
        super.onPause();
        NIMClient.getService(MsgService.class)
                .setChattingAccount(MsgService.MSG_CHATTING_ACCOUNT_NONE, SessionTypeEnum.None);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == 100) {
            String sessionId = data.getStringExtra("sessionId");
            for (int i = 0; i < dataList.size(); i++) {
                if (dataList.get(i).getAccid().equals(sessionId)) {
                    sharedPress.putInt(sessionId + "unread", 0);
                }
            }
            adapter.notifyDataSetChanged();
        }
        switch (resultCode) {
            case 1:
                this.videoMessageHelper = fragment.videoMessageHelper;
                if (this.videoMessageHelper == null) {
                    return;
                }
                this.videoMessageHelper.onCaptureVideoResult(data);
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (isLogin) {
            String sessionId = dataList.get(position).getAccid();
            int unread = sharedPress.getInt(dataList.get(position).getAccid() + "unread");
            int total = sharedPress.getInt("unreadTotal");
            if (total - unread < 0)
                sharedPress.putInt("unreadTotal", 0);
            else
                sharedPress.putInt("unreadTotal", total - unread);
            sharedPress.putInt(sessionId + "unread", 0);
            sharedPress.putLong(sessionId + "Time", sharedPress.getLong(sessionId + "Time"));
            Intent intent = new Intent(MessageActivity.this, TextMessageActivity.class);
            Log.e("onItemClick: ","" + sessionId );
            intent.putExtra("sessionId", sessionId);
            intent.putExtra("title", dataList.get(position).getName());
            HEADER_URL = dataList.get(position).getHeader();
            if (unread > 0)
                startActivityForResult(intent, 1000);
            else
                startActivity(intent);
            sharedPress.putInt(sessionId + "unread", 0);
        }
    }
}
