package io.contek.invoker.ftx.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._FutureStats;
import io.contek.invoker.ftx.api.common._Market;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetFutureStats extends MarketRestRequest<GetFutureStats.Response> {

  private String future_name;

  GetFutureStats(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetFutureStats setFutureName(String future_name) {
    this.future_name = future_name;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    requireNonNull(future_name);
    return format("/api/futures/{0}/stats", future_name);
  }

  @Override
  protected RestParams getParams() {
    return RestParams.empty();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_FutureStats> {}
}
