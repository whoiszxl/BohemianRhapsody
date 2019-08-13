package com.whoiszxl.market.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @description: 行情推送socket
 * @author: whoiszxl
 * @create: 2019-08-13
 **/
public class MarketHandler extends TextWebSocketHandler {


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("接收到了消息 >>>>>>" + message.getPayload());
        session.sendMessage(new TextMessage("接收到了消息"));
        if(message.getPayload().equals("10")) {
            for (int i=0; i<10; i++) {
                session.sendMessage(new TextMessage("消息体->>>" + i));
            }
            Thread.sleep(100);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("连接成功了喔"));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        session.sendMessage(new TextMessage("连接要关闭啦"));
    }
}
