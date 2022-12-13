package io.folivora.pushmessageserver.controller;

import io.folivora.pushmessageserver.model.PushMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PushMessageController{
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    // Mapped as /app/application
    @MessageMapping("/application")
    @SendTo("/all/messages")
    public PushMessage send(final PushMessage pushMessage) throws Exception {
        return pushMessage;
    }

    // Mapped as /app/private
    @MessageMapping("/private")
    public void sendToSpecificUser(@Payload PushMessage pushMessage) {
        simpMessagingTemplate.convertAndSendToUser(pushMessage.getTo(), "/specific", pushMessage);
    }

//    @MessageMapping("/private/{deviceId}")
//    public void sendToSpecificUser(@Payload PushMessage pushMessage, @DestinationVariable("deviceId") String deviceId) {
//        simpMessagingTemplate.convertAndSendToUser(pushMessage.getTo() + "/" + deviceId, "/specific", pushMessage);
//    }
}