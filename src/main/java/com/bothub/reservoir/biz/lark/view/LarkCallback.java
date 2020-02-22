package com.bothub.reservoir.biz.lark.view;

import lombok.Data;

@Data
public class LarkCallback {

  private String challenge;
  private String uuid;
  private String token;
  private String ts;
  private String type;
  private Object event;
//  {
//    "uuid": "41b5f371157e3d5341b38b20396e77e3",
//      "token": "2g7als3DgPW6Xp1xEpmcvgVhQG621bFY",//校验Token
//      "ts": "1550038209.428520",  //时间戳
//      "type": "event_callback",//事件回调此处固定为event_callback
//      "event": {
//    "type": "message", // 事件类型
//        "app_id": "cli_xxx",
//        "tenant_key": "xxx", //企业标识
//        "root_id": "",
//        "parent_id": "",
//        "open_chat_id": "oc_5ce6d572455d361153b7cb51da133945",
//        "chat_type": "private",//私聊private，群聊group
//        "msg_type": "text",    //消息类型
//        "open_id": "ou_18eac85d35a26f989317ad4f02e8bbbb",
//        "open_message_id": "om_36686ee62209da697d8775375d0c8e88",
//        "is_mention": false,
//        "text": "消息内容",      // 消息文本
//        "text_without_at_bot":"消息内容" //消息内容，会过滤掉at你的机器人的内容
//  }
//  }
}
