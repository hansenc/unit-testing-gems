package com.overstock.unittesting;

public enum Medal {
  GOLD(true),
  SILVER(true),
  BRONZE(true),
  NONE_TRY_AGAIN_NEXT_TIME(false);

  private final boolean metal;

  private Medal(boolean metal) {
    this.metal = metal;
  }

  public boolean isMetal() {
    return this.metal;
  }

  public static Medal getMedal(int index) {
    if (index < Medal.values().length) {
      return Medal.values()[index];
    }
    else {
      return Medal.values()[Medal.values().length - 1];
    }
  }

}
