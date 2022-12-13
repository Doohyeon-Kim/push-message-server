package io.folivora.pushmessageserver.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushMessage {
    private String message;
    private String to;
}
