package com.tiemuyu.chuanchuan.activity.chat_tools.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.chat_tools.bean.Contacts;
import com.tiemuyu.chuanchuan.activity.chat_tools.view.CircleImageView;
import com.tiemuyu.chuanchuan.activity.util.DataSharedPress;

/**
 * Created by Lonze on 2016/9/6.
 */
public class ContactsAdapter extends BaseAdapter {

    List<Contacts> data;
    private LayoutInflater layoutInflater;
    private ViewH viewH;
    private DataSharedPress sharedPress;
    View view;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    public ContactsAdapter(Context context, List<Contacts> data) {
        layoutInflater = LayoutInflater.from(context);
        viewH = new ViewH();
        this.data = data;
        sharedPress = DataSharedPress.getSharedPress(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            convertView = layoutInflater.inflate(R.layout.kefu_list_layout, null);
            viewH.header = (CircleImageView) convertView.findViewById(R.id.hear);
            viewH.name = (TextView) convertView.findViewById(R.id.name);
            viewH.total = (TextView) convertView.findViewById(R.id.message_total);
            viewH.content = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(viewH);
            view = convertView;
			
        viewH.name.setText(data.get(position).getName());
        if (data.get(position).getHeader() != null || !"".equals(data.get(position).getHeader()))
            imageLoader.displayImage(data.get(position).getHeader(), viewH.header);
        String sessionId = data.get(position).getAccid();
        int unread = sharedPress.getInt(sessionId + "unread");
        if (unread > 0) {
            viewH.total.setVisibility(View.VISIBLE);
            viewH.total.setText(unread + "");
            viewH.content.setVisibility(View.VISIBLE);
            String content = sharedPress.getString(sessionId + "content");
            if (content.length() > 15) {
                String sub = content.substring(0, 15);
                viewH.content.setText(sub + "......");
            } else
                viewH.content.setText(content);
        }
//        viewH.name.setText(data.get(position).get("name").toString());

        return view;
    }

    public void changeListData(int sum, String content) {

    }

    class ViewH {
        public CircleImageView header;
        public TextView name;
        public TextView total;
        public TextView content;
    }
}
