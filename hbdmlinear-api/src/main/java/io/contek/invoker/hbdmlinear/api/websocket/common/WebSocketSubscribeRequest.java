package io.contek.invoker.hbdmlinear.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketSubscribeRequest extends WebSocketRequest {

  public String sub;
}
