package com.tiemuyu.chuanchuan.activity.chat_tools.bean;

import com.netease.nimlib.sdk.msg.model.IMMessage;

/**
 * 会话窗口提供给子模块的代理接口。
 */
public interface ModuleProxy {
    // 发送消息
    boolean sendMessage(IMMessage msg);

    // 是否正在录音
    boolean isLongClickEnabled();
}
