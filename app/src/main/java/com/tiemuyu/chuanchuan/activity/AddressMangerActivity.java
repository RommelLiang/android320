package com.tiemuyu.chuanchuan.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tiemuyu.chuanchuan.activity.bean.Address;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.new_activities.BaseActivityG;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;

import org.xutils.http.RequestParams;

public class AddressMangerActivity extends BaseActivityG {

    private final String TAG_GET_ADDRESS = "TAG_GET_ADDRESS";
    private final String TAG_UPDATA_ADDRESS = "TAG_UPDATA_ADDRESS";
    private final String set_defaultaddr = "set_defaultaddr";
    private final String TAG_DELECT_addr = "TAG_DELECT_addr";
    private Address address;
    private ListView listview;
    private AddressAdapter addressAdapter;
    private RelativeLayout add_address;
    private final int code = 1;
    private int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manger);
        listview = (ListView) findViewById(R.id.lv_address);
        add_address = (RelativeLayout) findViewById(R.id.add_address);
        getAddress(TAG_GET_ADDRESS);
        Intent intent = getIntent();
        requestCode = intent.getIntExtra("requestCode", 0);
        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(AddressMangerActivity.this,AddNewAddressActivity.class),10);
            }
        });
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getAddress(String tag) {
        Log.e("getAddress:调用 ",tag);
        MyApplication.poolManager.addAsyncTask(
                new ThreadPoolTaskHttp(this,
                        tag,
                        Constant.REQUEST_GET,
                        new RequestParams(
                                UrlManager.GET_ADDRESS()),
                        this,
                        "获取收货地址",
                        false));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == resultCode && requestCode == 10) {
            getAddress(TAG_GET_ADDRESS);
            if (addressAdapter != null) {
                addressAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void successCallBack(String resultTag, BaseBean baseBean, String callBackMsg, boolean isShowDiolog) {
        super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
        Log.e("successCallBack: ", callBackMsg );
        if (resultTag.equals(TAG_GET_ADDRESS)) {
            address = (new Gson()).fromJson(callBackMsg,Address.class);
            Log.e("address", "successCallBack: " + address.getData().get(0).getContact());
            addressAdapter = new AddressAdapter();
            listview.setAdapter(addressAdapter);
            Log.e("requestCode", "successCallBack: "+requestCode);
            if (requestCode != 0) {
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent();
                        Log.e("requestCode", "successCallBack: "+position);
                        intent.putExtra("address",address.getData().get(position));
                        setResult(12,intent);
                        finish();
                    }
                });
            }
        } else if (resultTag.equals(set_defaultaddr)) {
            getAddress(TAG_UPDATA_ADDRESS);
            Log.e("set_defaultaddr", "successCallBack: " + callBackMsg);
        } else if(resultTag.equals(TAG_DELECT_addr)) {
            getAddress(TAG_UPDATA_ADDRESS);
        } else if (resultTag.equals(TAG_UPDATA_ADDRESS)) {
            address = (new Gson()).fromJson(callBackMsg,Address.class);
            addressAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failCallBack(Throwable arg0, String resultTag, boolean isShowDiolog) {
        super.failCallBack(arg0, resultTag, isShowDiolog);
        Log.e("failCallBack: ", arg0.getLocalizedMessage() );
    }

    private class AddressAdapter extends BaseAdapter{

        private LayoutInflater layoutInflater;

        public AddressAdapter() {
            layoutInflater = LayoutInflater.from(AddressMangerActivity.this);
        }

        @Override
        public int getCount() {
            return address.getData().size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                view = layoutInflater.inflate(R.layout.adapter_address_layout, parent, false);
                holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
                holder.tv_phone = (TextView) view.findViewById(R.id.tv_phone);
                holder.tv_address = (TextView) view.findViewById(R.id.tv_address);
                holder.tv_edit = (TextView) view.findViewById(R.id.tv_edit);
                holder.tv_delete = (TextView) view.findViewById(R.id.tv_delete);
                holder.im_select = (ImageView) view.findViewById(R.id.im_select);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
                holder.tv_name.setText(address.getData().get(position).getContact());
                holder.tv_phone.setText(address.getData().get(position).getMobile());
                holder.tv_address.setText(address.getData().get(position).getTotalAddress());
                holder.tv_name.setText(address.getData().get(position).getContact());
            if (address.getData().get(position).getIsDefault() == 1) {
                holder.im_select.setBackground(getResources().getDrawable(R.drawable.select));
            } else {
                holder.im_select.setBackground(getResources().getDrawable(R.drawable.noselect));
            }
            holder.im_select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (address.getData().get(position).getIsDefault() == 0) {
                        MyApplication.poolManager.addAsyncTask(
                                new ThreadPoolTaskHttp(AddressMangerActivity.this,
                                        set_defaultaddr,
                                        Constant.REQUEST_POST,
                                        ParamsTools.setDefaultAddress(
                                                UrlManager.set_defaultaddr(),
                                                address.getData().get(position).getId() + ""),
                                        AddressMangerActivity.this,                                        "设置默认地址",
                                        false));
                    }
                }
            });
            holder.tv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("getView: ", address.getData().get(position).getId() + "");
                    MyApplication.poolManager.addAsyncTask(
                            new ThreadPoolTaskHttp(AddressMangerActivity.this,
                                    TAG_DELECT_addr,
                                    Constant.REQUEST_POST,
                                    ParamsTools.removeAddress(
                                            UrlManager.REMOVE_ADDRESS(),
                                            String.valueOf(address.getData().get(position).getId())),
                                    AddressMangerActivity.this, "删除地址",
                                    false));
                }
            });
            holder.tv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AddressMangerActivity.this,AddNewAddressActivity.class);
                    intent.putExtra("address",address.getData().get(position));
                    startActivity(intent);
                }
            });
            return view;
        }

        private class ViewHolder {
            public TextView tv_name;
            public TextView tv_phone;
            public TextView tv_address;
            public TextView tv_edit;
            public TextView tv_delete;
            public ImageView im_select;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (addressAdapter != null) {
            addressAdapter.notifyDataSetChanged();
        }
    }
}
