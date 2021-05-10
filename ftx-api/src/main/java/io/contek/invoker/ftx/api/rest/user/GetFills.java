package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Fill;
import io.contek.invoker.ftx.api.common._Order;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.commons.rest.RestMethod.GET;

@NotThreadSafe
public final class GetFills extends UserRestRequest<GetFills.Response> {

  private String market;

  /** Start time in epoch-seconds */
  private Long start_time;

  /** End time in epoch-seconds */
  private Long end_time;

  private String order;

  GetFills(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetFills setMarket(String market) {
    this.market = market;
    return this;
  }

  public GetFills setStartTime(long start_time) {
    this.start_time = start_time;
    return this;
  }

  public GetFills setEndTime(long end_time) {
    this.end_time = end_time;
    return this;
  }

  public GetFills setOrder(String order) {
    this.order = order;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/fills";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (market != null) {
      builder.add("market", market);
    }
    if (start_time != null) {
      builder.add("start_time", start_time);
    }
    if (end_time != null) {
      builder.add("end_time", end_time);
    }
    if (order != null) {
      builder.add("order", order);
    }
    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_Fill>> {}
}
