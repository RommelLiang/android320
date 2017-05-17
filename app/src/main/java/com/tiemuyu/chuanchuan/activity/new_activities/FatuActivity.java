package com.tiemuyu.chuanchuan.activity.new_activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.squareup.picasso.Picasso;
import com.tiemuyu.chuanchuan.activity.MainActivity;
import com.tiemuyu.chuanchuan.activity.MyApplication;
import com.tiemuyu.chuanchuan.activity.R;
import com.tiemuyu.chuanchuan.activity.bean.BaseBean;
import com.tiemuyu.chuanchuan.activity.bean.GetPassKey;
import com.tiemuyu.chuanchuan.activity.bean.ImageUrlBean;
import com.tiemuyu.chuanchuan.activity.bean.User;
import com.tiemuyu.chuanchuan.activity.constant.Constant;
import com.tiemuyu.chuanchuan.activity.constant.UrlManager;
import com.tiemuyu.chuanchuan.activity.db.DBTools;
import com.tiemuyu.chuanchuan.activity.helper.ProgressHelper;
import com.tiemuyu.chuanchuan.activity.http.HttpTools;
import com.tiemuyu.chuanchuan.activity.inter.ProgressOfImage;
import com.tiemuyu.chuanchuan.activity.util.AppManager;
import com.tiemuyu.chuanchuan.activity.util.ClassJumpTool;
import com.tiemuyu.chuanchuan.activity.util.DataContoler;
import com.tiemuyu.chuanchuan.activity.util.GsonUtils;
import com.tiemuyu.chuanchuan.activity.util.JsonTools;
import com.tiemuyu.chuanchuan.activity.util.ParamsTools;
import com.tiemuyu.chuanchuan.activity.util.PicassoImageLoader;
import com.tiemuyu.chuanchuan.activity.util.PreferenceUtils;
import com.tiemuyu.chuanchuan.activity.util.SocketHttpRequester;
import com.tiemuyu.chuanchuan.activity.util.StringUtil;
import com.tiemuyu.chuanchuan.activity.util.ThreadPoolTaskHttp;
import com.tiemuyu.chuanchuan.activity.util.ToastHelper;
import com.tiemuyu.chuanchuan.activity.view.HorizontalListVIew;
import com.tiemuyu.chuanchuan.activity.view.URL;

import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CC2.0 on 2017/1/16.
 */

public class FatuActivity extends BaseActivityG implements ProgressOfImage{
	private static final int IMAGE_PICKER = 255;

//两个按钮


	@ViewInject(R.id.post_pic)
	private Button fabu_bt;// 发布按钮


	@ViewInject(R.id.PostPicGoBack)
	private LinearLayout bt_back;// 回退


	@ViewInject(R.id.customize_description)
	private TextView et_text;// 说明输入框

	//private ImageView add_img1;
	private HorizontalListVIew horizontalListVIew;
	ArrayList<ImageItem> mImages;

	private EditText tv_message;
	public static final int TAKE_PHOTO = 1;
	public static final int SELECT_FROM_ALBUM = 2;
	public Uri imageUri;
	public String result = "";


	private List<String> imagelist = new ArrayList<>();


	private String TAG_FABU_MOMENT = "TAG_FABU_MOMENT";


	private ImagePicker imagePicker;
	private ImageView add_img1;
	private MyAdapter adapter;
	private String mPost;
	private String tag = "上装";
	//private LoadingProxy mInstance;
	private ProgressHelper mProgressHelper;
	private String login_v;
	String oauthid = "";
	private String mPassKey;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.post_pic_layout);

		imagePicker = ImagePicker.getInstance();
		imagePicker.setImageLoader(new PicassoImageLoader());   //设置图片加载器
		imagePicker.setShowCamera(true);  //显示拍照按钮
		imagePicker.setCrop(true);        //允许裁剪（单选才有效）
		imagePicker.setSaveRectangle(true); //是否按矩形区域保存
		imagePicker.setSelectLimit(9);    //选中数量限制
		imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
		imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
		imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
		imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
		imagePicker.setOutPutY(1000);//保存文件的高度。单位像素

		fabu_bt = (Button) findViewById(R.id.post_pic);

		bt_back = (LinearLayout) findViewById(R.id.PostPicGoBack);

		et_text = (TextView) findViewById(R.id.customize_description);

		add_img1 = (ImageView) findViewById(R.id.add_img1);
		add_img1.setOnClickListener(this);
		horizontalListVIew = (HorizontalListVIew) findViewById(R.id.listview_horizon);
		MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
				HttpTools.TAG_GETPASSKEY, Constant.REQUEST_GET, new RequestParams(UrlManager
				.GET_PASSKEY()), this, "获取passkey", false));
		AppManager.getAppManager().addActivity(this);
		tv_message = (EditText) findViewById(R.id.message);
		initProcess();
		//mInstance = LoadingProxy.getInstance(this);

	}


	/**
	 * 加载的流程
	 */
	protected void initProcess() {
		initData();
		initAppAccess();
		initListener();
	}


	/**
	 * 实例化访问记录
	 */
	protected void initAppAccess() {

	}

	protected void initListener() {
		// TODO Auto-generated method stub
		fabu_bt.setOnClickListener(this);
		bt_back.setOnClickListener(this);
		/*Intent intent = new Intent(this, ImageGridActivity.class);
		startActivityForResult(intent, IMAGE_PICKER);
*/
		//todo   上下装套装选择。

	}

	protected void initData() {
//        // TODO Auto-generated method stub
//        c_type = getIntent().getStringExtra(ClassJumpTool.DATA_PACKET_NAME);
//        if (c_type != null)
//            coming_type = c_type;
//
//        isCollection = false;
	}


	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {


			case R.id.post_pic:
				//todo 调用发布的函数
				System.out.println("#####调用发布按钮啊");

				exFabu();
				break;

			case R.id.PostPicGoBack:
				ClassJumpTool.startToBackActivity(this, MainActivity.class, null, 10);
				break;
			case R.id.add_img1:
				if (mImages != null && mImages.size() == 9) {
					Toast.makeText(this, "你已经选择了九张图片", Toast.LENGTH_SHORT).show();
					return;
				}
				Intent intent = new Intent(this, ImageGridActivity.class);
				startActivityForResult(intent, IMAGE_PICKER);
				break;

		}
	}


	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println("#####   回退到minefragment");

	}


	/**
	 * 发布图片
	 */
	public void exFabu() {
		if (mImages == null) {
			Toast.makeText(this, "请选择图片", Toast.LENGTH_SHORT).show();
			return;
		}
		if (mImages.size() == 0) {
			ToastHelper.show(this, "请先上传图片");
			return;
		}
		sendImage();


	}

	private void sendImage() {
		//mInstance.show();
		mProgressHelper = new ProgressHelper(this,mImages.size());
		mProgressHelper.openGuide();
		new Thread(new Runnable() {
			@Override
			public void run() {

				List<String> imageUrl = new ArrayList<>();
				for ( ImageItem image : mImages ) {
					imageUrl.add(image.path);
				}
				try {
					SocketHttpRequester socketHttpRequester = new SocketHttpRequester(FatuActivity.this);
					mPost = socketHttpRequester.post(URL.UPLOAD_URLMoment, imageUrl,FatuActivity.this);
					if (!TextUtils.isEmpty(mPost)) {
						System.out.println("******进入非空判断");
						handler.sendEmptyMessage(1);
					} else {
						handler.sendEmptyMessage(2);//失败
					}
				} catch (Exception mE) {
					mProgressHelper.closeGuide();
					ToastHelper.show(FatuActivity.this,"发送图片失败");
					Log.e("runmE: ", mE.getLocalizedMessage());
				}
			}
		}).start();
	}


	@SuppressWarnings("deprecation")
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.e("onActivityResult", requestCode + "onActivityResult: " + resultCode + data);
		if (resultCode == 1004) {
			Log.e("resultCode", "onActivityResult: " + "1111111");
			if (data != null && requestCode == 255) {
				ArrayList<ImageItem> list = new ArrayList<>();
				list = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
				if (mImages == null) {
					mImages = new ArrayList<>();
					mImages.addAll(list);
				} else {
					boolean isSelect;
					for ( int i = 0; i < list.size(); i++ ) {
						isSelect = false;
						for ( int i1 = 0; i1 < mImages.size(); i1++ ) {
							if (mImages.get(i1).path.equals(list.get(i).path)) {
								isSelect = true;
							}
						}
						if (!isSelect) {
							mImages.add(list.get(i));
						}
					}
				}
				Log.e("ArrayList", "onActivityResult: " + mImages.size());
				adapter = new MyAdapter(mImages);
				horizontalListVIew.setAdapter(adapter);
			} else {
				Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
			}
		}
		if (mImages != null && mImages.size() != 0) {
			add_img1.setVisibility(View.GONE);
		}
	}

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			mProgressHelper.closeGuide();
			switch (msg.what) {
				case 1:
					System.out.println("*******图片上传成功");
					addPageLoad();
					break;
				case 2:
					Log.e("图片上传失败", "handleMessage: ");
					System.out.println("*******图片上传失败");
					//mInstance.dismiss();
					break;
				default:
					break;
			}
		}
	};

	public void addPageLoad() {
		if (!TextUtils.isEmpty(mPost)) {
			//String str = "{\"Code\":1,\"Msg\":\"OK\",\"Data\":{\"ImageUrl\":\"http://f1.myappcc.com/zfs/7E0/1240/RIC/240174344351CABGTOTZGT.jpg\"}}";
			ImageUrlBean imageUrlBean = GsonUtils.fromData(mPost, ImageUrlBean.class);
			int code = imageUrlBean.getCode();
			if (code == 0) {
				//mInstance.dismiss();
				ToastHelper.show(FatuActivity.this, "上传图片失败，请重试");
				return;
			}
			String[] split = imageUrlBean.getData().getImageUrl().split(",");
			for ( String s : split ) {
				imagelist.add(s);
			}
			String text = "";
			String imgs = "";
			String s = String.valueOf(tv_message.getText());
			text = s.toString().trim() + "";
			if (imagelist.size() == 1)
				imgs += imagelist.get(0);
			else {
				for ( int i = 0; i < imagelist.size() - 1; i++ )
					imgs += imagelist.get(i) + ",";
				imgs += imagelist.get(imagelist.size() - 1);
			}
			Log.e("测试发图: ", imgs);
			MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
					TAG_FABU_MOMENT, Constant.REQUEST_POST, ParamsTools.fabu(
					UrlManager.Fabu_Moment(), tag, text, imgs), FatuActivity.this,
					"发图中...", false));
		}
	}


	/**********************
	 * 继承的接口
	 ****************************/

	private String TAG_SENDMESSAGE = "TAG_SENDMESSAGE";

	@Override
	public void reLoginCallBack(String resultTag, boolean isShowDiolog) {
		super.reLoginCallBack(resultTag, isShowDiolog);
		if (resultTag.equals(TAG_FABU_MOMENT)) {
			//mInstance.dismiss();
			ToastHelper.show(this, "图片发布失败,请重新发布");
		}
	}

	@Override
	public void successCallBack(String resultTag, BaseBean baseBean,
	                            String callBackMsg, boolean isShowDiolog) {
		// TODO Auto-generated method stub
//        dissMissDialog(isShowDiolog);
		super.successCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);

		User user = DBTools.getUser();
		if (resultTag.equals(TAG_FABU_MOMENT)) {
			Log.e("测试发图:", "发布完成");
			System.out.println("######成功了callback");
			//mInstance.dismiss();
			MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(this,
					TAG_SENDMESSAGE, Constant.REQUEST_GET,
					new RequestParams(UrlManager.sendMessage(user.getUserId() + "")), FatuActivity.this,
					"发图中...", false));
			Log.e("successCallBack: ", UrlManager.sendMessage(user.getUserId() + ""));
			new AlertView.Builder().setContext(this)
					.setStyle(AlertView.Style.Alert)
					.setTitle("")
					.setMessage("图片发送成功")
					.setDestructive("完成")
					.setOthers(null)
					.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(Object o, int position) {
							Log.e("onItemClick: ", position + "");
							switch (position) {
								case 0:
									ClassJumpTool.startToBackActivity(FatuActivity.this, MainActivity.class, null, 10);
									break;
							}
						}
					})
					.build().show();
		} else if (resultTag.equals(TAG_SENDMESSAGE)) {
			Log.e("successCallBack: ", TAG_SENDMESSAGE + callBackMsg);

		} else if (resultTag.equals(HttpTools.TAG_GETPASSKEY)) {
			GetPassKey key = JsonTools.fromJson(callBackMsg, GetPassKey.class);
			if (key != null) {
				// LogHelper.d("---获取pass1111111->");

				mPassKey = key.getData().getPassKey();
				PreferenceUtils.setPrefString(FatuActivity.this, Constant.PASSKEY,
						mPassKey);
			}
			User mUser = DBTools.getUser();
			login_v = DataContoler.getLoginV(mUser.getUsername(), mUser.getPass(), mPassKey);
			if (!StringUtil.isNull(login_v)) {
				MyApplication.poolManager.addAsyncTask(new ThreadPoolTaskHttp(
						this, HttpTools.TAG_AULOGIN, Constant.REQUEST_POST,
						ParamsTools.login(UrlManager.LOGIN(), login_v, oauthid),
						this, "正在登录...", false));
			}

		} else if (resultTag.equals(HttpTools.TAG_AULOGIN)) {
			ToastHelper.show(FatuActivity.this,"自动登陆成功");
		}


	}

	@Override
	public void failShowCallBack(String resultTag, BaseBean baseBean,
	                             String callBackMsg, boolean isShowDiolog) {
		// TODO Auto-generated method stub
		super.failShowCallBack(resultTag, baseBean, callBackMsg, isShowDiolog);
		if (resultTag.equals(TAG_FABU_MOMENT)) {
			System.out.println("######failShowCallBack ");
			ToastHelper.show(this, "图片发布失败");
			Log.e("测试发图:", "发布完成");
			//mInstance.dismiss();
		}
	}

//    public void cancelCallBack(String resultTag) {
//        // TODO Auto-generated method stub
//    }

	public void failCallBack(Throwable arg0, String resultTag,
	                         boolean isShowDiolog) {
		// TODO Auto-generated method stub
		super.failCallBack(arg0, resultTag, isShowDiolog);
		Log.e("failCallBack: ", resultTag + ":" + arg0.getLocalizedMessage());
		if (resultTag.equals(TAG_FABU_MOMENT)) {
			System.out.println("#####失败:" + arg0.getMessage());
			ToastHelper.show(this, "失败");
			//.dismiss();
		}


	}

	@Override
	public void getProgress(int page, int progress) {
		Log.e("发图图层显示: ", page+":"+progress);
		//fabu_bt.setText(page+":"+progress);
		Message message = new Message();
		message.arg1 = page;
		message.arg2 = progress;
		mHandler.sendMessage(message);
	}

	Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Log.e("handleMessage: ",msg.toString() );
			Log.e( "handleMessage:",msg.arg1+"" );
			int page = msg.arg1;
			int progress = msg.arg2;
			//fabu_bt.setText(page+":"+progress);
			mProgressHelper.setView(page,progress);
		}
	};

	/*************************************************/

	private class MyAdapter extends BaseAdapter {

		private List<ImageItem> items;

		public MyAdapter(List<ImageItem> items) {
			this.items = items;
		}

		public void setData(List<ImageItem> items) {
			this.items = items;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return items.size() + 1;
		}

		@Override
		public ImageItem getItem(int position) {
			return items.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ChildHolder childHolder;
			int width = add_img1.getWidth();
			Log.e("width", "getView: " + width);
			if (convertView == null) {
				childHolder = new ChildHolder();
				convertView = LayoutInflater.from(FatuActivity.this).inflate(R.layout.image_layout, parent, false);
				childHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
				childHolder.delect = (ImageView) convertView.findViewById(R.id.delect);
				convertView.setTag(childHolder);
			} else {
				childHolder = (ChildHolder) convertView.getTag();
			}
			if (position == items.size()) {
				Picasso.with(FatuActivity.this)
						.load(R.drawable.add_image)
						.resize(width - 30, width - 30)
						.into(childHolder.imageView);
				childHolder.delect.setVisibility(View.GONE);
				childHolder.imageView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (mImages.size() != 9) {
							Intent intent = new Intent(FatuActivity.this, ImageGridActivity.class);
							startActivityForResult(intent, IMAGE_PICKER);
						} else {
							Toast.makeText(FatuActivity.this, "您已选择了九张图片", Toast.LENGTH_SHORT).show();
						}
					}
				});
				return convertView;

			}
			if (position < items.size()) {
				//Picasso.
				(new PicassoImageLoader()).displayImage(FatuActivity.this, items.get(position).path, childHolder.imageView, width);
				Log.e("getView: ", items.get(position).path);
				childHolder.delect.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Log.e("delect", "getView: " + mImages.size());
						if (mImages.size() == 1) {
							mImages.clear();
							//add_img1.setVisibility(View.VISIBLE);
							adapter.notifyDataSetChanged();
						} else if (mImages.size() > 1 && mImages.size() <= 9) {
							mImages.remove(position);
							adapter.notifyDataSetChanged();
						}

					}
				});
			}
			return convertView;
		}


	}

	private static class ChildHolder {
		ImageView imageView, delect;
	}

}