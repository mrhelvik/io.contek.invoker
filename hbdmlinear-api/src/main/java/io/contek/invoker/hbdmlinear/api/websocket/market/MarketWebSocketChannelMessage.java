package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketInboundMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class MarketWebSocketChannelMessage extends WebSocketInboundMessage {

  public String ch;
}
