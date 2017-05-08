package com.tiemuyu.chuanchuan.activity.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梁文硕 on 2017/4/26.
 */

public class MoreBean {

	/**
	 * Code : 1
	 * Msg : OK
	 * Data : [{"id":125,"name":"04.25  定制成品","img":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115192316688CABHWQVILV.jpg","createtime":"2017-04-25 19:24:21","appdingzhilist":[{"id":965,"daohangid":125,"proid":73832,"mianliao":"聚酯纤维","xijieimg":"http://f1.myappcc.com/zfs/7E1/1115/UKL/115201223422CABHKCPZNU.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201230875CABHDEKBIU.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201233125CABHTNGVHT.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201235188CABHPZBMPC.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201237297CABHXXUFQB.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201239063CABHCAUIKP.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201241078CABHXNCAGV.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201245219CABHHVXMMI.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201247281CABHTMCBHT.jpg","status":0,"proname":"女装顶级聚酯纤维秋冬长袖中长款西装外套","promianpic":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115195339938CABHCGBZVK.jpg","createtime":"2017-04-25 19:55:44","firstXiJieImg":"http://f1.myappcc.com/zfs/7E1/1115/UKL/115201223422CABHKCPZNU.jpg","price":1000,"username":null,"userimg":null,"imgList":null,"fatushuo":null,"IsFav":false,"plList":null},{"id":964,"daohangid":125,"proid":73834,"mianliao":"梭织棉","xijieimg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115195241250CABHLWQONW.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195248453CABHFIQFVQ.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195250703CABHPOZEUN.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195252813CABHPFBMCR.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195255266CABHOLDXYX.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195257969CABHJUXKTT.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195300078CABHUFEHXB.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195304906CABHOEVXPM.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195306860CABHBCHNXQ.jpg","status":0,"proname":"男装高级梭织棉-春夏长袖短款衬衫梭织衬衫","promianpic":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115195244391CABHALYUYU.jpg","createtime":"2017-04-25 19:54:23","firstXiJieImg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115195241250CABHLWQONW.jpg","price":400,"username":null,"userimg":null,"imgList":null,"fatushuo":null,"IsFav":false,"plList":null},{"id":963,"daohangid":125,"proid":73833,"mianliao":"聚酯纤维","xijieimg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115195023172CABHHQZMNP.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195044438CABHMQCLCA.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195104922CABHSFLKXW.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195125938CABHHVZQJI.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195127828CABHEHECTQ.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195146969CABHKEKNYF.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195149047CABHHDEOKC.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195153610CABHPLIGYY.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195155719CABHTAVJQT.jpg","status":0,"proname":"女装轻工打揽高级聚酯纤维春夏短袖长款连衣裙","promianpic":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115195025453CABHVMZWRP.jpg","createtime":"2017-04-25 19:53:12","firstXiJieImg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115195023172CABHHQZMNP.jpg","price":576,"username":null,"userimg":null,"imgList":null,"fatushuo":null,"IsFav":false,"plList":null},{"id":962,"daohangid":125,"proid":67000,"mianliao":"聚酯纤维","xijieimg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115194924563CABHRXZPMV.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194930000CABHIAQLGA.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194932110CABHDRAWZL.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194935328CABHVUKIIG.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194937313CABHATHOAS.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194939547CABHAOIXDV.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194941828CABHOVICKJ.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194945578CABHLCDESH.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194947516CABHJWYIYT.jpg","status":0,"proname":"男装重工数码印花高级聚酯纤维双层中款外套","promianpic":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115194926922CABHOKGNPT.jpg","createtime":"2017-04-25 19:51:04","firstXiJieImg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115194924563CABHRXZPMV.jpg","price":948,"username":null,"userimg":null,"imgList":null,"fatushuo":null,"IsFav":false,"plList":null},{"id":961,"daohangid":125,"proid":65600,"mianliao":"欧根纱","xijieimg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115194812672CABHHFWWTW.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194819688CABHQHNOZZ.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194821750CABHSUGFHY.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194823656CABHNZERBA.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194825938CABHEBZEGJ.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194828891CABHXKAYPD.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194831219CABHJAYSCW.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194836438CABHAVPLED.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194839485CABHTNOXIK.jpg","status":0,"proname":"女装轻工钉珠高级混纺单层短款套头","promianpic":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115194815313CABHJVCAEH.jpg","createtime":"2017-04-25 19:49:56","firstXiJieImg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115194812672CABHHFWWTW.jpg","price":324,"username":null,"userimg":null,"imgList":null,"fatushuo":null,"IsFav":false,"plList":null},{"id":960,"daohangid":125,"proid":63251,"mianliao":"聚酯纤维","xijieimg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115193139563CABHOAEHTS.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115193143125CABHJUKLGM.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115193145235CABHHNBVRS.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115193147235CABHCZECBP.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115193150016CABHUXFVKK.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115193152969CABHSBLUOB.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115193155141CABHFBGCFQ.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115193200125CABHXUVXIJ.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115193202453CABHPLMIAG.jpg","status":0,"proname":"女装品质聚酯纤维拼接短款一字肩上衣","promianpic":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115193137094CABHAEHNTY.jpg","createtime":"2017-04-25 19:33:19","firstXiJieImg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115193139563CABHOAEHTS.jpg","price":351,"username":null,"userimg":null,"imgList":null,"fatushuo":null,"IsFav":false,"plList":null}]}]
	 */

	private int Code;
	private String Msg;
	private List<DataBean> Data;

	public static MoreBean objectFromData(String str) {

		return new Gson().fromJson(str, MoreBean.class);
	}

	public static MoreBean objectFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);

			return new Gson().fromJson(jsonObject.getString(str), MoreBean.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<MoreBean> arrayMoreBeanFromData(String str) {

		Type listType = new TypeToken<ArrayList<MoreBean>>() {
		}.getType();

		return new Gson().fromJson(str, listType);
	}

	public static List<MoreBean> arrayMoreBeanFromData(String str, String key) {

		try {
			JSONObject jsonObject = new JSONObject(str);
			Type listType = new TypeToken<ArrayList<MoreBean>>() {
			}.getType();

			return new Gson().fromJson(jsonObject.getString(str), listType);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return new ArrayList();


	}

	public int getCode() {
		return Code;
	}

	public void setCode(int Code) {
		this.Code = Code;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String Msg) {
		this.Msg = Msg;
	}

	public List<DataBean> getData() {
		return Data;
	}

	public void setData(List<DataBean> Data) {
		this.Data = Data;
	}

	public static class DataBean {
		/**
		 * id : 125
		 * name : 04.25  定制成品
		 * img : http://f1.myappcc.com/zfs/7E1/1115/TJK/115192316688CABHWQVILV.jpg
		 * createtime : 2017-04-25 19:24:21
		 * appdingzhilist : [{"id":965,"daohangid":125,"proid":73832,"mianliao":"聚酯纤维","xijieimg":"http://f1.myappcc.com/zfs/7E1/1115/UKL/115201223422CABHKCPZNU.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201230875CABHDEKBIU.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201233125CABHTNGVHT.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201235188CABHPZBMPC.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201237297CABHXXUFQB.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201239063CABHCAUIKP.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201241078CABHXNCAGV.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201245219CABHHVXMMI.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201247281CABHTMCBHT.jpg","status":0,"proname":"女装顶级聚酯纤维秋冬长袖中长款西装外套","promianpic":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115195339938CABHCGBZVK.jpg","createtime":"2017-04-25 19:55:44","firstXiJieImg":"http://f1.myappcc.com/zfs/7E1/1115/UKL/115201223422CABHKCPZNU.jpg","price":1000,"username":null,"userimg":null,"imgList":null,"fatushuo":null,"IsFav":false,"plList":null},{"id":964,"daohangid":125,"proid":73834,"mianliao":"梭织棉","xijieimg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115195241250CABHLWQONW.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195248453CABHFIQFVQ.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195250703CABHPOZEUN.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195252813CABHPFBMCR.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195255266CABHOLDXYX.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195257969CABHJUXKTT.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195300078CABHUFEHXB.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195304906CABHOEVXPM.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195306860CABHBCHNXQ.jpg","status":0,"proname":"男装高级梭织棉-春夏长袖短款衬衫梭织衬衫","promianpic":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115195244391CABHALYUYU.jpg","createtime":"2017-04-25 19:54:23","firstXiJieImg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115195241250CABHLWQONW.jpg","price":400,"username":null,"userimg":null,"imgList":null,"fatushuo":null,"IsFav":false,"plList":null},{"id":963,"daohangid":125,"proid":73833,"mianliao":"聚酯纤维","xijieimg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115195023172CABHHQZMNP.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195044438CABHMQCLCA.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195104922CABHSFLKXW.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195125938CABHHVZQJI.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195127828CABHEHECTQ.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195146969CABHKEKNYF.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195149047CABHHDEOKC.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195153610CABHPLIGYY.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115195155719CABHTAVJQT.jpg","status":0,"proname":"女装轻工打揽高级聚酯纤维春夏短袖长款连衣裙","promianpic":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115195025453CABHVMZWRP.jpg","createtime":"2017-04-25 19:53:12","firstXiJieImg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115195023172CABHHQZMNP.jpg","price":576,"username":null,"userimg":null,"imgList":null,"fatushuo":null,"IsFav":false,"plList":null},{"id":962,"daohangid":125,"proid":67000,"mianliao":"聚酯纤维","xijieimg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115194924563CABHRXZPMV.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194930000CABHIAQLGA.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194932110CABHDRAWZL.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194935328CABHVUKIIG.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194937313CABHATHOAS.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194939547CABHAOIXDV.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194941828CABHOVICKJ.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194945578CABHLCDESH.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194947516CABHJWYIYT.jpg","status":0,"proname":"男装重工数码印花高级聚酯纤维双层中款外套","promianpic":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115194926922CABHOKGNPT.jpg","createtime":"2017-04-25 19:51:04","firstXiJieImg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115194924563CABHRXZPMV.jpg","price":948,"username":null,"userimg":null,"imgList":null,"fatushuo":null,"IsFav":false,"plList":null},{"id":961,"daohangid":125,"proid":65600,"mianliao":"欧根纱","xijieimg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115194812672CABHHFWWTW.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194819688CABHQHNOZZ.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194821750CABHSUGFHY.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194823656CABHNZERBA.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194825938CABHEBZEGJ.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194828891CABHXKAYPD.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194831219CABHJAYSCW.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194836438CABHAVPLED.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115194839485CABHTNOXIK.jpg","status":0,"proname":"女装轻工钉珠高级混纺单层短款套头","promianpic":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115194815313CABHJVCAEH.jpg","createtime":"2017-04-25 19:49:56","firstXiJieImg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115194812672CABHHFWWTW.jpg","price":324,"username":null,"userimg":null,"imgList":null,"fatushuo":null,"IsFav":false,"plList":null},{"id":960,"daohangid":125,"proid":63251,"mianliao":"聚酯纤维","xijieimg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115193139563CABHOAEHTS.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115193143125CABHJUKLGM.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115193145235CABHHNBVRS.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115193147235CABHCZECBP.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115193150016CABHUXFVKK.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115193152969CABHSBLUOB.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115193155141CABHFBGCFQ.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115193200125CABHXUVXIJ.jpg,http://f1.myappcc.com/zfs/7E1/1115/TJK/115193202453CABHPLMIAG.jpg","status":0,"proname":"女装品质聚酯纤维拼接短款一字肩上衣","promianpic":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115193137094CABHAEHNTY.jpg","createtime":"2017-04-25 19:33:19","firstXiJieImg":"http://f1.myappcc.com/zfs/7E1/1115/TJK/115193139563CABHOAEHTS.jpg","price":351,"username":null,"userimg":null,"imgList":null,"fatushuo":null,"IsFav":false,"plList":null}]
		 */

		private int id;
		private String name;
		private String img;
		private String createtime;
		private List<AppdingzhilistBean> appdingzhilist;

		public static DataBean objectFromData(String str) {

			return new Gson().fromJson(str, DataBean.class);
		}

		public static DataBean objectFromData(String str, String key) {

			try {
				JSONObject jsonObject = new JSONObject(str);

				return new Gson().fromJson(jsonObject.getString(str), DataBean.class);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		public static List<DataBean> arrayDataBeanFromData(String str) {

			Type listType = new TypeToken<ArrayList<DataBean>>() {
			}.getType();

			return new Gson().fromJson(str, listType);
		}

		public static List<DataBean> arrayDataBeanFromData(String str, String key) {

			try {
				JSONObject jsonObject = new JSONObject(str);
				Type listType = new TypeToken<ArrayList<DataBean>>() {
				}.getType();

				return new Gson().fromJson(jsonObject.getString(str), listType);

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return new ArrayList();


		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

		public String getCreatetime() {
			return createtime;
		}

		public void setCreatetime(String createtime) {
			this.createtime = createtime;
		}

		public List<AppdingzhilistBean> getAppdingzhilist() {
			return appdingzhilist;
		}

		public void setAppdingzhilist(List<AppdingzhilistBean> appdingzhilist) {
			this.appdingzhilist = appdingzhilist;
		}

		public static class AppdingzhilistBean {
			/**
			 * id : 965
			 * daohangid : 125
			 * proid : 73832
			 * mianliao : 聚酯纤维
			 * xijieimg : http://f1.myappcc.com/zfs/7E1/1115/UKL/115201223422CABHKCPZNU.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201230875CABHDEKBIU.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201233125CABHTNGVHT.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201235188CABHPZBMPC.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201237297CABHXXUFQB.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201239063CABHCAUIKP.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201241078CABHXNCAGV.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201245219CABHHVXMMI.jpg,http://f1.myappcc.com/zfs/7E1/1115/UKL/115201247281CABHTMCBHT.jpg
			 * status : 0
			 * proname : 女装顶级聚酯纤维秋冬长袖中长款西装外套
			 * promianpic : http://f1.myappcc.com/zfs/7E1/1115/TJK/115195339938CABHCGBZVK.jpg
			 * createtime : 2017-04-25 19:55:44
			 * firstXiJieImg : http://f1.myappcc.com/zfs/7E1/1115/UKL/115201223422CABHKCPZNU.jpg
			 * price : 1000
			 * username : null
			 * userimg : null
			 * imgList : null
			 * fatushuo : null
			 * IsFav : false
			 * plList : null
			 */

			private int id;
			private int daohangid;
			private int proid;
			private String mianliao;
			private String xijieimg;
			private int status;
			private String proname;
			private String promianpic;
			private String createtime;
			private String firstXiJieImg;
			private double price;
			private Object username;
			private Object userimg;
			private Object imgList;
			private Object fatushuo;
			private boolean IsFav;
			private Object plList;

			public static AppdingzhilistBean objectFromData(String str) {

				return new Gson().fromJson(str, AppdingzhilistBean.class);
			}

			public static AppdingzhilistBean objectFromData(String str, String key) {

				try {
					JSONObject jsonObject = new JSONObject(str);

					return new Gson().fromJson(jsonObject.getString(str), AppdingzhilistBean.class);
				} catch (JSONException e) {
					e.printStackTrace();
				}

				return null;
			}

			public static List<AppdingzhilistBean> arrayAppdingzhilistBeanFromData(String str) {

				Type listType = new TypeToken<ArrayList<AppdingzhilistBean>>() {
				}.getType();

				return new Gson().fromJson(str, listType);
			}

			public static List<AppdingzhilistBean> arrayAppdingzhilistBeanFromData(String str, String key) {

				try {
					JSONObject jsonObject = new JSONObject(str);
					Type listType = new TypeToken<ArrayList<AppdingzhilistBean>>() {
					}.getType();

					return new Gson().fromJson(jsonObject.getString(str), listType);

				} catch (JSONException e) {
					e.printStackTrace();
				}

				return new ArrayList();


			}

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public int getDaohangid() {
				return daohangid;
			}

			public void setDaohangid(int daohangid) {
				this.daohangid = daohangid;
			}

			public int getProid() {
				return proid;
			}

			public void setProid(int proid) {
				this.proid = proid;
			}

			public String getMianliao() {
				return mianliao;
			}

			public void setMianliao(String mianliao) {
				this.mianliao = mianliao;
			}

			public String getXijieimg() {
				return xijieimg;
			}

			public void setXijieimg(String xijieimg) {
				this.xijieimg = xijieimg;
			}

			public int getStatus() {
				return status;
			}

			public void setStatus(int status) {
				this.status = status;
			}

			public String getProname() {
				return proname;
			}

			public void setProname(String proname) {
				this.proname = proname;
			}

			public String getPromianpic() {
				return promianpic;
			}

			public void setPromianpic(String promianpic) {
				this.promianpic = promianpic;
			}

			public String getCreatetime() {
				return createtime;
			}

			public void setCreatetime(String createtime) {
				this.createtime = createtime;
			}

			public String getFirstXiJieImg() {
				return firstXiJieImg;
			}

			public void setFirstXiJieImg(String firstXiJieImg) {
				this.firstXiJieImg = firstXiJieImg;
			}

			public double getPrice() {
				return price;
			}

			public void setPrice(double price) {
				this.price = price;
			}

			public Object getUsername() {
				return username;
			}

			public void setUsername(Object username) {
				this.username = username;
			}

			public Object getUserimg() {
				return userimg;
			}

			public void setUserimg(Object userimg) {
				this.userimg = userimg;
			}

			public Object getImgList() {
				return imgList;
			}

			public void setImgList(Object imgList) {
				this.imgList = imgList;
			}

			public Object getFatushuo() {
				return fatushuo;
			}

			public void setFatushuo(Object fatushuo) {
				this.fatushuo = fatushuo;
			}

			public boolean isIsFav() {
				return IsFav;
			}

			public void setIsFav(boolean IsFav) {
				this.IsFav = IsFav;
			}

			public Object getPlList() {
				return plList;
			}

			public void setPlList(Object plList) {
				this.plList = plList;
			}
		}
	}
}
