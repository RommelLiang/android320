package com.tiemuyu.chuanchuan.activity.chat_tools.inter;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/5/20.
 */
public interface NetResponses {
    void success(int type, JSONObject jsonObject);
    void fail();
}
