package com.example.demo;

public enum SupportStrengthEnum {

  LOW("low"), MIDDLE("middle"), HIGH("high");
  private String supportStrength;

  SupportStrengthEnum(String supportStrength) {
    this.supportStrength = supportStrength;
  }

  public String getSupportStrength() {
    return supportStrength;
  }

}
