package com.tiemuyu.chuanchuan.activity.chat_tools.utils;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.QueryDirectionEnum;

import java.util.ArrayList;
import java.util.List;

import com.tiemuyu.chuanchuan.activity.chat_tools.adapter.MessageAdapter;
import com.tiemuyu.chuanchuan.activity.chat_tools.bean.MessageData;
import com.tiemuyu.chuanchuan.activity.chat_tools.fragment.TextMessageActivity;

/**
 * describe :
 * Created by SHILI on 2016/6/15.
 * PackageName com.tiemuyu.chuanchuan.activity.chat_tools.utils.
 * ProjectName chuanchuan
 */
public class HistoryMessage {
    int asd = 0;
    private String TAG = "HistoryMessage";
    private MessageAdapter adapter;
    int achour;
    private IMMessage immessage;
    boolean isLast;
    private PullToRefreshListView pullToRefreshListView;
    private ArrayList<IMMessage> imMessageArrayList;
    private Activity context;
    private MessageItemClick messageItemClick;
    private MessageShowUtil messageShowUtil;
    private String sessionId;
    ListView listViewShow;

    public HistoryMessage() {
    }

    public HistoryMessage(final Activity context, PullToRefreshListView listView, String sessionId) {
        this.pullToRefreshListView = listView;
        imMessageArrayList = new ArrayList<>();
        MessageData.setDataList(imMessageArrayList);
        this.context = context;
        messageItemClick = new MessageItemClick(context);
        messageShowUtil = new MessageShowUtil(context);
        this.sessionId = sessionId;
    }

    public boolean aaaa(List<IMMessage> imMessages, final int count, boolean isGET) {
        Log.e(TAG, "aaaa: " + isGET);
        Log.e(TAG, "aaaa: imMessages:" + imMessages.size());
        if (isGET)
            imMessageArrayList.clear();

        for (int i = imMessages.size() - 1; i >= 0; i--) {

            isLast = true;
            imMessageArrayList.add(imMessages.get(i));
        }
        if (isLast) {
            if (adapter == null) {
                for (int i = 0; i < imMessageArrayList.size(); i++) {
                    Log.e(TAG + "ArrayList", "aaaa: " + imMessageArrayList.get(i).getContent() + ":" + imMessageArrayList.get(i).getFromAccount());
                }
                adapter = new MessageAdapter(imMessageArrayList, context);
                adapter.setOnClickMessageItemListener(new MessageAdapter.OnClickMessageItemListener() {
                    @Override
                    public void onClickMessageItem(ImageView view, IMMessage imMessage, int d) {
                        //messageItemClick.itemOnClick(view, imMessage, d);
                    }
                });
                pullToRefreshListView.setAdapter(adapter);
            }
            adapter.notifyDataSetChanged();
            listViewShow = pullToRefreshListView.getRefreshableView();
            listViewShow.setSelection(pullToRefreshListView.getBottom());
        }
        System.out.println("====pullMessageFromLocal===" + imMessageArrayList.size());
        return imMessageArrayList.size() == 0;
    }

    public void addListData() {
        imMessageArrayList = MessageData.getDataList();
        if (adapter == null) {
            adapter = new MessageAdapter(imMessageArrayList, context);
            adapter.setOnClickMessageItemListener(new MessageAdapter.OnClickMessageItemListener() {
                @Override
                public void onClickMessageItem(ImageView view, IMMessage imMessage, int d) {
                    //messageItemClick.itemOnClick(view, imMessage, d);
                }
            });
            pullToRefreshListView.setAdapter(adapter);
        }
        adapter.notifyDataSetChanged();
        listViewShow = pullToRefreshListView.getRefreshableView();
        listViewShow.setSelection(pullToRefreshListView.getBottom());
    }

    public void pullMessageFromCloud(final IMMessage imMessage, final int count, final boolean isGET) {
        Log.e(TAG, "pullMessageFromCloud: " + imMessage.getContent());
        NIMClient.getService(MsgService.class).pullMessageHistoryEx(imMessage, System.currentTimeMillis(), count, QueryDirectionEnum.QUERY_NEW, true)
                .setCallback(new RequestCallback<List<IMMessage>>() {
                    @Override
                    public void onSuccess(List<IMMessage> imMessages) {
                        for (int i = 0; i < imMessages.size(); i++) {
                            Log.e(TAG + "FromCloud", "onSuccess: " + imMessages.get(i).getContent());
                        }
                        //aaaa(imMessages, count, isGET);
                    }

                    @Override
                    public void onFailed(int i) {
                        Log.e(TAG, "onFailed: " + i);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        Log.e(TAG, "onFailed: " + throwable.getLocalizedMessage());
                    }
                });
        /**
         * @param anchor IMMessage 查询锚点
         * @param direction QueryDirectionEnum 查询方向
         * @param limit int 查询结果的条数限制
         * @param asc boolean 查询结果的排序规则，如果为 true，结果按照时间升级排列，如果为 false，按照时间降序排列
         * @return 调用跟踪，可设置回调函数，接收查询结果
         */
       NIMClient
                .getService(MsgService.class)
                .queryMessageListEx(imMessage, QueryDirectionEnum.QUERY_NEW, count, true)
                .setCallback(new RequestCallback<List<IMMessage>>() {
                    @Override
                    public void onSuccess(List<IMMessage> param) {
                        for (int i = 0; i < param.size(); i++) {
                            Log.e("queryMessageListEx", "onSuccess: "+param.get(i).getContent());
                        }
                        //aaaa(param, count, isGET);
                    }

                    @Override
                    public void onFailed(int code) {
                        Log.e(TAG, "onFailed: "+code);
                    }

                    @Override
                    public void onException(Throwable exception) {
                        Log.e(TAG, "onException: "+exception.getLocalizedMessage());
                    }
                });
        NIMClient.getService(MsgService.class)
                .queryMessageList(sessionId, SessionTypeEnum.P2P, 0, count)
                .setCallback(new RequestCallbackWrapper<List<IMMessage>>() {
                    @Override
                    public void onResult(int i, List<IMMessage> imMessages, Throwable throwable) {

                        Log.e(TAG, "onResult:asd " + asd);
                        aaaa(imMessages, count, isGET);
                        asd++;

                    }
                });
    }

    // 史力：发送消息成功后执行的回调函数sendSuccessRefresh
    public IMMessage sendSuccessRefresh(IMMessage message) {
        imMessageArrayList.add(message);
        if (adapter == null) {
            adapter = new MessageAdapter(imMessageArrayList, context);
            adapter.setOnClickMessageItemListener(new MessageAdapter.OnClickMessageItemListener() {
                @Override
                public void onClickMessageItem(ImageView view, IMMessage imMessage, int d) {
                    //messageItemClick.itemOnClick(view, imMessage, d);
                }
            });
            pullToRefreshListView.setAdapter(adapter);
        }
        adapter.notifyDataSetChanged();
        listViewShow = pullToRefreshListView.getRefreshableView();
        listViewShow.setSelection(pullToRefreshListView.getBottom());
        return message;
    }

    public void receiveMessageRefresh(final List<IMMessage> imMessages) {
        for (IMMessage message : imMessages) {
            imMessageArrayList.add(message);
        }
        if (adapter == null) {
            adapter = new MessageAdapter(imMessageArrayList, context);
            pullToRefreshListView.setAdapter(adapter);
        }
        context.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        listViewShow = pullToRefreshListView.getRefreshableView();
                        listViewShow.setSelection(pullToRefreshListView.getBottom());
                    }
                }, 10);

            }
        });
    }

    private ShowImage showImage;

    public interface ShowImage {
        void showImage(IMMessage message, ImageView view, MessageAdapter adapter);
    }

    public void setShowImage(ShowImage showImage) {
        this.showImage = showImage;
    }

}
