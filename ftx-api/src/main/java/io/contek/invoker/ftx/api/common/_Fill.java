package io.contek.invoker.ftx.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Fill {

  public Double fee;
  public String feeCurrency;  // Note: not in ws
  public Double feeRate;
  public String future;
  public Long id;
  public String liquidity;
  public String market;
  public String baseCurrency;  // Note: not in ws
  public String quoteCurrency;  // Note: not in ws
  public Long orderId;
  public Long tradeId;
  public Double price;
  public String side;
  public Double size;
  public String time;
  public String type;
}
