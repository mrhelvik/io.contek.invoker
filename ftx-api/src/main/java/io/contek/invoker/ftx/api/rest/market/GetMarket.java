package io.contek.invoker.ftx.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Market;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetMarket extends MarketRestRequest<GetMarket.Response> {

  private String market_name;

  GetMarket(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetMarket setMarketName(String market_name) {
    this.market_name = market_name;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    requireNonNull(market_name);
    return format("/api/markets/{0}", market_name);
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
  public static final class Response extends RestResponse<_Market> {}
}
