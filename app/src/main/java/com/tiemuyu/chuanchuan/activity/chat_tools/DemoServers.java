package com.tiemuyu.chuanchuan.activity.chat_tools;

public class DemoServers {

    //
    // 好友列表信息服务器地址
    //
    private static final String API_SERVER_TEST = "https://apptest.netease.im/api/"; // 测试
    private static final String API_SERVER = "https://app.netease.im/api/"; // 线上

    public static final String apiServer() {
//        return ServerConfig.testServer() ? API_SERVER_TEST : API_SERVER;
        return API_SERVER_TEST;
    }

    public static final String chatRoomAPIServer() {
        return apiServer() + "chatroom/";
    }
//
    static class ServerConfig {

        protected enum ServerEnv {
            TEST("t"),
            PRE_REL("p"),
            REL("r"),

            ;
            String tag;

            ServerEnv(String tag) {
                this.tag = tag;
            }
        }

        static class ServerEnvs {
            static final ServerConfig.ServerEnv SERVER = ServerConfig.ServerEnv.REL;

        }
        public static boolean testServer() {
            return ServerEnvs.SERVER == ServerEnv.TEST;
        }
    }


}
