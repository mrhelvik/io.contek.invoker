package io.contek.invoker.hbdmlinear.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _PositionInfo {

  public String symbol;
  public String contract_code;
  public double volume;
  public double available;
  public double frozen;
  public double cost_open;
  public double cost_hold;
  public double profit_unreal;
  public double profit_rate;
  public int lever_rate;
  public double position_margin;
  public String direction;
  public double profit;
  public double last_price;
  public String margin_asset;
  public String margin_mode;
  public String margin_account;
}
