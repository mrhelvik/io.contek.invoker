package io.contek.invoker.binancedelivery.api.websocket.user;

import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketEventMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

import static io.contek.invoker.binancedelivery.api.websocket.user.constants.UserEventTypeKeys._MARGIN_CALL;

@ThreadSafe
public final class MarginCallChannel
    extends UserWebSocketChannel<MarginCallChannel.Id, MarginCallChannel.Message> {

  MarginCallChannel() {
    super(Id.INSTANCE);
  }

  @Override
  protected Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends UserWebSocketChannelId<Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super(_MARGIN_CALL);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketEventMessage {

    public Double cw; // transaction time
    public List<PositionForMarginCall> p;

    @NotThreadSafe
    public static class PositionForMarginCall {

      public String s; // symbol
      public String ps; // position side
      public String mt; // margin type
      public Long iw; // isolated wallet
      public Double mp; // mark price
      public Double up; // unrealized PnL
      public Double mm; // maintenance margin required
    }
  }
}