package ua.com.owu.sep2022springboot.configs;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;

public class WebSocketConnection extends TextWebSocketHandler {

    private Map<String, WebSocketSession> sessionMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        sessionMap.put(session.getId(), session);
        sessionMap.entrySet().forEach(stringWebSocketSessionEntry -> System.out.println(stringWebSocketSessionEntry));

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        sessionMap.forEach((s, webSocketSession) -> {
            try {
                webSocketSession.sendMessage(new TextMessage(message + "  " + new Date(System.currentTimeMillis())));
            } catch (IOException e) {
                System.out.println("session " + session.getId() + " already closed");
                throw new RuntimeException(e);
            }
        });


    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Set<Map.Entry<String, WebSocketSession>> entries = sessionMap.entrySet();
        Iterator<Map.Entry<String, WebSocketSession>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, WebSocketSession> next = iterator.next();
            if (next.getKey().equals(session.getId())) {
                System.out.println("found session to close " + session.getId());
                iterator.remove();
                session.close();
            }
        }
    }
}
