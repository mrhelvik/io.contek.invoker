package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.rest.common.RestEmptyResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostLendingOffer extends UserRestRequest<RestEmptyResponse> {

  private String coin;
  private Double size;
  private Double rate;

  PostLendingOffer(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostLendingOffer setCoin(String coin) {
    this.coin = coin;
    return this;
  }

  public PostLendingOffer setSize(Double size) {
    this.size = size;
    return this;
  }

  public PostLendingOffer setRate(Double rate) {
    this.rate = rate;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/spot_margin/offers";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(coin);
    requireNonNull(size);
    requireNonNull(rate);
    builder.add("coin", coin);
    builder.add("size", size);
    builder.add("rate", rate);

    return builder.build();
  }

  @Override
  protected Class<RestEmptyResponse> getResponseType() {
    return RestEmptyResponse.class;
  }

}
