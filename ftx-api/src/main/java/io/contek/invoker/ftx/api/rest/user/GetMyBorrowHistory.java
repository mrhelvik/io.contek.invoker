package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._BorrowHistory;
import io.contek.invoker.ftx.api.common._LendingHistory;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.commons.rest.RestMethod.GET;

@NotThreadSafe
public final class GetMyBorrowHistory extends UserRestRequest<GetMyBorrowHistory.Response> {

  /** Start time in epoch-seconds */
  private Long start_time;

  /** End time in epoch-seconds */
  private Long end_time;

  GetMyBorrowHistory(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetMyBorrowHistory setStartTime(long start_time) {
    this.start_time = start_time;
    return this;
  }

  public GetMyBorrowHistory setEndTime(long end_time) {
    this.end_time = end_time;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/spot_margin/borrow_history";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (start_time != null) {
      builder.add("start_time", start_time);
    }
    if (end_time != null) {
      builder.add("end_time", end_time);
    }
    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_BorrowHistory>> {}
}
