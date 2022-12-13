package io.folivora.pushmessageserver.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    final private static List<WebSocketSession> sessionList = new ArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws  Exception{
        String payload = message.getPayload();
        System.out.println("payload : " + payload);

        for(WebSocketSession sess: sessionList) {
            sess.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionList.add(session);
        System.out.println(session + "Client Connected");
        TextMessage message = new TextMessage(session + "Client Connected" );
        session.sendMessage(message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println(session + "Client Disconnected");
        TextMessage message = new TextMessage(session + "Client Disconnected" );
        sessionList.remove(session);
    }
}
