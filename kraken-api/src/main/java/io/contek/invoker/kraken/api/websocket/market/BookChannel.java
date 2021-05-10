package io.contek.invoker.kraken.api.websocket.market;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;
import io.contek.invoker.kraken.api.common._OrderBook;
import io.contek.invoker.kraken.api.common._OrderBookLevel;
import io.contek.invoker.kraken.api.websocket.WebSocketChannel;
import io.contek.invoker.kraken.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.kraken.api.websocket.common.Subscription;
import io.contek.invoker.kraken.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static io.contek.invoker.kraken.api.common._OrderBookLevel.toOrderBookLevel;
import static io.contek.invoker.kraken.api.websocket.common.constants.WebSocketChannelKeys._book;

@ThreadSafe
public final class BookChannel extends WebSocketChannel<BookChannel.Message> {

  public static final String BID_SNAPSHOT_KEY = "bs";
  public static final String ASK_SNAPSHOT_KEY = "as";
  public static final String BID_INCREMENTAL_KEY = "b";
  public static final String ASK_INCREMENTAL_KEY = "a";

  private final Topic topic;

  BookChannel(Topic topic, WebSocketRequestIdGenerator requestIdGenerator) {
    super(requestIdGenerator);
    this.topic = topic;
  }

  private static List<_OrderBookLevel> toOrderBookEntries(JsonElement jsonArray) {
    List<_OrderBookLevel> orderBookEntries = new ArrayList<>();
    for (JsonElement jsonElement : jsonArray.getAsJsonArray()) {
      orderBookEntries.add(toOrderBookLevel(jsonElement.getAsJsonArray()));
    }
    return orderBookEntries;
  }

  @Override
  protected Subscription getSubscription() {
    Subscription subscription = new Subscription();
    subscription.name = _book;
    subscription.depth = topic.getDepth();
    return subscription;
  }

  @Override
  protected String getPair() {
    return topic.getPair();
  }

  @Override
  protected BaseWebSocketChannelId getId() {
    return topic.toString();
  }

  @Override
  protected Class<BookChannel.Message> getMessageType() {
    return BookChannel.Message.class;
  }

  @Override
  protected boolean accepts(BookChannel.Message message) {
    return topic.getPair().equals(message.pair);
  }

  @Immutable
  public static final class Topic {

    private final String pair;
    private final int depth;

    private String value;

    private Topic(String pair, int depth) {
      this.pair = pair;
      this.depth = depth;
    }

    public static Topic of(String pair, int depth) {
      return new Topic(pair, depth);
    }

    public String getPair() {
      return pair;
    }

    public int getDepth() {
      return depth;
    }

    @Override
    public boolean equals(@Nullable Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Topic topic = (Topic) o;
      return depth == topic.depth && Objects.equals(pair, topic.pair);
    }

    @Override
    public int hashCode() {
      return Objects.hash(pair, depth);
    }

    @Override
    public String toString() {
      if (value == null) {
        value = _book + "." + pair + "." + depth;
      }
      return value;
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<_OrderBook> {

    public static Message fromJsonArray(JsonArray jsonArray) {
      Message res = new Message();
      res.channelID = jsonArray.get(0).getAsInt();
      res.channelName = jsonArray.get(jsonArray.size() - 2).getAsString();
      res.pair = jsonArray.get(jsonArray.size() - 1).getAsString();

      res.params = new _OrderBook();
      res.params.bs = ImmutableList.of();
      res.params.as = ImmutableList.of();
      res.params.b = ImmutableList.of();
      res.params.a = ImmutableList.of();

      JsonObject jsonObject = jsonArray.get(1).getAsJsonObject();

      if (jsonObject.has(BID_SNAPSHOT_KEY)) {
        res.params.bs = toOrderBookEntries(jsonObject.get(BID_SNAPSHOT_KEY));
      }

      if (jsonObject.has(ASK_SNAPSHOT_KEY)) {
        res.params.as = toOrderBookEntries(jsonObject.get(ASK_SNAPSHOT_KEY));
      }

      if (jsonObject.has(BID_INCREMENTAL_KEY)) {
        res.params.b = toOrderBookEntries(jsonObject.get(BID_INCREMENTAL_KEY));
      }

      if (jsonObject.has(ASK_INCREMENTAL_KEY)) {
        res.params.a = toOrderBookEntries(jsonObject.get(ASK_INCREMENTAL_KEY));
      }

      if (jsonArray.size() == 5) {
        jsonObject = jsonArray.get(2).getAsJsonObject();
        if (jsonObject.has(BID_INCREMENTAL_KEY)) {
          res.params.b = toOrderBookEntries(jsonObject.get(BID_INCREMENTAL_KEY));
        }
      }

      return res;
    }
  }
}