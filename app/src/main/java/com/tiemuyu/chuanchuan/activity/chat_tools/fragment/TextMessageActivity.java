package com.tiemuyu.chuanchuan.activity.chat_tools.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nimlib.sdk.InvocationFuture;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;


import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.netease.nimlib.sdk.msg.model.QueryDirectionEnum;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.chat_tools.adapter.MessageAdapter;
import com.tiemuyu.chuanchuan.activity.chat_tools.bean.MessageData;
import com.tiemuyu.chuanchuan.activity.chat_tools.bean.ModuleProxy;
import com.tiemuyu.chuanchuan.activity.chat_tools.utils.HistoryMessage;
import com.tiemuyu.chuanchuan.activity.chat_tools.video.VideoMessageHelper;
import com.tiemuyu.chuanchuan.activity.util.DataSharedPress;

/**
 * describe :
 * Created by FUYING on 2016/6/12.
 * PackageName com.guanjia800.clientApp.chat_tools.fragment.
 * ProjectName HouseKeeper_android.
 */
public class TextMessageActivity extends Activity implements View.OnClickListener, ModuleProxy {

    private LinearLayout ly_message_edit, ly_press_on_say;
    private EditText message_edit;
    private View convertView;
    protected String sessionId; // p2p对方Account或者群id
    PullToRefreshListView listView;
    private HistoryMessage historyMessage;
    public static IMMessage SSIMMESSAGE;
    private IMMessage imMessage;
    private TextView tv_title;
    private String title;
    private LinearLayout goBack;
    private DataSharedPress sharedPress;
    public static VideoMessageHelper videoMessageHelper;
    ListView listViewShow;
    private String TAG;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //史力：下面这句设置键盘上抬，非常重要！
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.nim_message_fragment);
        //设置状态栏为透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();
        //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        registerObservers(incomingMessageObserver, true);
        sessionId = getIntent().getStringExtra("sessionId");
        title = getIntent().getStringExtra("title");
        sharedPress = DataSharedPress.getSharedPress(this);
        tv_title.setText(title);
        historyMessage = new HistoryMessage(this, listView,sessionId);
        imMessage = MessageBuilder.createEmptyMessage(sessionId, SessionTypeEnum.None, 0);
        Log.e("TextMessageActivity", "onCreate: "+imMessage.getContent());
        historyMessage.pullMessageFromCloud(imMessage, 10, true);
        TAG = new String("sssssssssssssss");
        /*NIMClient.getService(MsgService.class)
                .pullMessageHistory(imMessage, *//*System.currentTimeMillis() + 30 * 60 * 1000,*//*
                        20*//*, QueryDirectionEnum.QUERY_NEW*//*, true).setCallback(new RequestCallback<List<IMMessage>>() {
            @Override
            public void onSuccess(List<IMMessage> imMessages) {
                for (int i = 0; i < imMessages.size(); i++) {
                    Log.e(TAG, "onSuccess: " + imMessages.get(i).getContent());
                }
            }

            @Override
            public void onFailed(int i) {
                Log.e(TAG, "onFailed: "+i);
            }

            @Override
            public void onException(Throwable throwable) {
                Log.e(TAG, "onException: "+throwable.getLocalizedMessage());
            }
        });*/
        /*NIMClient.getService(MsgService.class)
                .queryMessageList(sessionId,SessionTypeEnum.P2P,0,20)
                .setCallback(new RequestCallbackWrapper<List<IMMessage>>() {
                    @Override
                    public void onResult(int i, List<IMMessage> imMessages, Throwable throwable) {
                        for (int a = 0; a < imMessages.size(); a++) {
                            Log.e("AAAAAAAAAAA", "onSuccess: " + imMessages.get(a).getContent());
                        }
                    }
                });*/
        /*NIMClient.getService(MsgService.class)
                .queryMessageList()*/
    }

    @Override
    public void onStart() {
        super.onStart();
        historyMessage.setShowImage(new HistoryMessage.ShowImage() {
            @Override
            public void showImage(IMMessage message, ImageView view, MessageAdapter adapter) {
            }
        });

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if(MessageData.getData(0) == null) {
                    listView.onRefreshComplete();
                    Toast.makeText(TextMessageActivity.this,"您与"+title+"没有聊天记录",Toast.LENGTH_LONG).show();
                    return;
                }
                imMessage = MessageData.getData(0);
                System.out.println("@@@@@@imMessage====" + imMessage.getContent());
                NIMClient.getService(MsgService.class).pullMessageHistory(imMessage, 15, true)
                        .setCallback(new RequestCallback<List<IMMessage>>() {
                            @Override
                            public void onSuccess(List<IMMessage> imMessages) {
                                MessageData.addDataList(imMessages);
                                for (int i = 0; i < imMessages.size(); i++) {
                                    Log.e("pullMessageHistory", "onSuccess: "+ imMessages.get(i).getContent());
                                }
                                historyMessage.addListData();
                                listView.onRefreshComplete();
                            }

                            @Override
                            public void onFailed(int i) {
                            }

                            @Override
                            public void onException(Throwable throwable) {
                            }
                        });
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        NIMClient.getService(MsgService.class).setChattingAccount(MsgService.MSG_CHATTING_ACCOUNT_ALL, SessionTypeEnum.None);
    }

    @Override
    protected void onPause() {
        super.onPause();
        NIMClient.getService(MsgService.class).setChattingAccount(MsgService.MSG_CHATTING_ACCOUNT_NONE, SessionTypeEnum.None);
    }

    private void registerObservers(Observer<List<IMMessage>> immessage, boolean register) {
        MsgServiceObserve service = NIMClient.getService(MsgServiceObserve.class);
        service.observeReceiveMessage(immessage, register);
    }


    /**
     * 消息接收观察者
     */
    List<IMMessage> list = new ArrayList<>();
    Observer<List<IMMessage>> incomingMessageObserver = new Observer<List<IMMessage>>() {
        @Override
        public void onEvent(List<IMMessage> messages) {
            if (messages == null || messages.isEmpty()) {
                Log.e("onEvent:","messages.isEmpty");
                return;
            }
            Log.e("IMMessage", "onEvent: "+messages.get(0).getContent());
            for (int i = 0; i < messages.size(); i++) {
                Log.e("imMessageArrayList", "incomingMessageObserver: " + messages.get(i).getContent() + messages.get(i).getMsgType().getValue());
            }
           /* for (int i = 0; i < messages.size(); i++) {
                Log.e("imMessageArrayList", "incomingMessageObserver: clear" + list.get(i).getContent() + list.get(i).getMsgType().getValue());
            }*/
            list.clear();
            for(IMMessage message :  messages) {
                if (sessionId.equals(message.getSessionId())) {
                    list.add(message);
                    sharedPress.putLong(sessionId + "Time", list.get(list.size() - 1).getTime());
                }
            }
            for (int i = 0; i < messages.size(); i++) {
                Log.e("imMessageArrayList", "incomingMessageObserver: clear" + list.get(i).getContent() + list.get(i).getMsgType().getValue());
            }
            historyMessage.receiveMessageRefresh(list);
        }
    };


    private void initView() {
        convertView = LayoutInflater.from(this).inflate(R.layout.nim_message_activity_bottom_layout,null);
        tv_title = (TextView) findViewById(R.id.tv_title);
        goBack = (LinearLayout) findViewById(R.id.goBack);
        goBack.setOnClickListener(this);
        ly_message_edit = (LinearLayout)findViewById(R.id.ly_message_edit);
        message_edit = (EditText)findViewById(R.id.message_edit);
        //ly_press_on_say = (LinearLayout)findViewById(R.id.ly_press_on_say);
        listView = (PullToRefreshListView)findViewById(R.id.messageListView);
        findViewById(R.id.message_send).setOnClickListener(this);
        findViewById(R.id.message_image).setOnClickListener(this);
        message_edit.setOnClickListener(this);
        message_edit.addTextChangedListener(watcher);
        convertView.findViewById(R.id.message_image).setOnClickListener(this);
//        convertView.findViewById(R.id.message_audio).setOnClickListener(this);
//        convertView.findViewById(R.id.message_phone).setOnClickListener(this);
//        convertView.findViewById(R.id.message_video).setOnClickListener(this);
    }

    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goBack:
                Intent intent = getIntent();
                intent.putExtra("sessionId",sessionId);
                setResult(100,intent);
                finish();
                break;
            case R.id.message_send:
                if ("".equals(message_edit.getText().toString().trim())) {
                    Toast.makeText(this, "不能发送空字符串", Toast.LENGTH_LONG).show();
                    break;
                }
                IMMessage imMessage = MessageBuilder.createTextMessage(sessionId, SessionTypeEnum.P2P, message_edit.getText().toString().trim());
                sendMessage(imMessage);
                message_edit.setText("");
                // 史力：以下为点击了发送按钮自动隐藏软键盘的代码
               /* Timer timer2 = new Timer();
                timer2.schedule(new TimerTask() {
                    public void run() {
                        EditText edit = (EditText) findViewById(R.id.message_edit);
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
                    }
                }, 100);*/
//                Timer timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        tv.setVisibility(View.GONE);
//                        iv.setVisibility(View.VISIBLE);
//                    }
//                }, 100);
                break;
            case R.id.message_edit:
                listViewShow = listView.getRefreshableView();
                listViewShow.setSelection(listView.getBottom());
                message_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        //TODO 这里做"回车"响应处理
                        switch (actionId) {
                            case EditorInfo.IME_ACTION_SEND:
                                if ("".equals(message_edit.getText().toString().trim())) {
                                    Toast.makeText(TextMessageActivity.this, "不能发送空字符串", Toast.LENGTH_LONG).show();
                                    break;
                                }
                                IMMessage imMessage = MessageBuilder.createTextMessage("haha", SessionTypeEnum.P2P, message_edit.getText().toString());
                                sendMessage(imMessage);
                                message_edit.setText("");
//                                    MessageActivity.checkVisibility(ly_message_edit, true);
//                                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                                    imm.hideSoftInputFromWindow(message_edit.getWindowToken(), 0);
                                break;
                        }
                        return false;
                    }
                });
                break;
            case R.id.message_image:
                //史力：点击加号弹出对话框，选择“相册”或者“照相机”
                AlertDialog.Builder multiDia = new AlertDialog.Builder(this);
                multiDia.setTitle("选择照片：");
                multiDia.setPositiveButton("相册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openPhoto();
                    }
                });

                multiDia.setNeutralButton("照相机", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openCamera();
                    }
                });
                multiDia.create().show();
                break;
//            case R.id.message_audio:
//                //隐藏软键盘
//                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(message_edit.getWindowToken(), 0);
//                MessageActivity.checkVisibility(ly_message_edit, false);
//                MessageActivity.checkVisibility(ly_press_on_say, true);
//                break;
//            case R.id.message_phone:
//                openCamera();
//                break;
//            case R.id.message_video:
//                if (videoMessageHelper == null) {
//                    initVideoMessageHelper();
//                }
//                //2是本地视频，1是拍摄视频
//                videoMessageHelper.showVideoSource(2, 1);
//
//                break;
        }
    }

    public void openPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 100);
    }

    File tempFile;

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            tempFile = new File(Environment.getExternalStorageDirectory() + "/" + getPackageName() + "/image", System.currentTimeMillis() / 1000 * 60 + ".jpg");
            if (isHadFilePath(tempFile)) {
                Uri uri = Uri.fromFile(tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, 300);
            } else {
                Toast.makeText(TextMessageActivity.this, "路径出错", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean isHadFilePath(File file) {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    File file;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                if (data != null) {
                    Uri uri = data.getData();
                    String path = getRealPathFromURI(uri);
                    file = new File(path);
                    System.out.println("path of pic in IM: " + path);// 史力：显示图片路径
                    IMMessage imMessage = MessageBuilder.createImageMessage(sessionId, SessionTypeEnum.P2P, file);
                    sendMessage(imMessage);
                    // 史力：注释以下代码是为了取消掉选择图片之后的裁剪功能，但是用不了
//                    Intent intent = new Intent("com.android.camera.action.CROP");
//                    intent.setDataAndType(uri, "image/*");
//                    intent.putExtra("crop", false);
//                    intent.putExtra("outputFormat", "JPEG");
//                    intent.putExtra("return-data", false);
//                    startActivityForResult(intent, 200);
                }
                break;
            case 200:
                if (data != null) {
                    System.out.println("$$$$$$$$$$$$$$fileSize===" + file.length());
                    IMMessage imMessage = MessageBuilder.createImageMessage(sessionId, SessionTypeEnum.P2P, file);
                    sendMessage(imMessage);
                }
                break;
            case 300:
                System.out.println("$$$$$$$$$$$$$$resultCode==" + requestCode + "@@@" + data);
                IMMessage imMessage = MessageBuilder.createImageMessage(sessionId, SessionTypeEnum.P2P, tempFile);
                sendMessage(imMessage);
                break;
        }
    }

    @Override
    public boolean sendMessage(final IMMessage message) {
        NIMClient.getService(MsgService.class).sendMessage(message, false).setCallback(new RequestCallback<Void>() {

            @Override
            public void onSuccess(Void aVoid) {
                System.out.println("$$$$$$$$$$$$$$message sent!$$$$$$$$$$$");
                Log.e("onSuccess: ","Success");
                historyMessage.sendSuccessRefresh(message);
            }

            @Override
            public void onFailed(int i) {
                System.out.println("$$$$$$$$$$$$$$f ailed sending message$$$$$$$$$$$" + i);
            }

            @Override
            public void onException(Throwable throwable) {
                Log.e("Exception: ",throwable.toString());
            }
        });
        return true;
    }

    @Override
    public boolean isLongClickEnabled() {
        return false;
    }

    public void receiveMessage(List<IMMessage> messages) {
        historyMessage.receiveMessageRefresh(messages);
    }

    private String filePathFromIntent(Intent data) {
        Uri uri = data.getData();

        try {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor == null) {
                //miui 2.3 有可能为null
                return uri.getPath();
            } else {
                cursor.moveToFirst();
                return cursor.getString(cursor.getColumnIndex("_data")); // 文件路径
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("###登出了");
//        registerObservers(incomingMessageObserver, false);
    }
}
