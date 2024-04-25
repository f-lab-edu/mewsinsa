package com.mewsinsa.auth.kakao.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoUserInfoDto {
  private String id;
  @JsonProperty("connected_at")
  private String connectedAt;
  @JsonProperty("kakao_account")
  private KakaoAccount kakaoAccount;

  //==Getter==//
  public String getId() {
    return id;
  }

  public String getConnectedAt() {
    return connectedAt;
  }

  public KakaoAccount getKakaoAccount() {
    return kakaoAccount;
  }

  //==Setter==//

  public void setId(String id) {
    this.id = id;
  }

  public void setConnectedAt(String connectedAt) {
    this.connectedAt = connectedAt;
  }

  public void setKakaoAccount(
      KakaoAccount kakaoAccount) {
    this.kakaoAccount = kakaoAccount;
  }


  public class KakaoAccount {
    @JsonProperty("name_needs_agreement")
    private boolean nameNeedsAgreement;
    private String name;
    private String email;
    @JsonProperty("has_email")
    private boolean hasEmail;
    @JsonProperty("email_needs_agreement")
    private boolean emailNeedsAgreement;
    @JsonProperty("is_email_valid")
    private boolean isEmailValid;
    @JsonProperty("is_email_verified")
    private boolean isEmailVerified;

    //==Getter==//
    public String getName() {
      return name;
    }

    public String getEmail() {
      return email;
    }

    public boolean isNameNeedsAgreement() {
      return nameNeedsAgreement;
    }

    public boolean isHasEmail() {
      return hasEmail;
    }

    public boolean isEmailNeedsAgreement() {
      return emailNeedsAgreement;
    }

    public boolean isEmailValid() {
      return isEmailValid;
    }

    public boolean isEmailVerified() {
      return isEmailVerified;
    }

    //==Setter==//
    public void setName(String name) {
      this.name = name;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public void setNameNeedsAgreement(boolean nameNeedsAgreement) {
      this.nameNeedsAgreement = nameNeedsAgreement;
    }

    public void setHasEmail(boolean hasEmail) {
      this.hasEmail = hasEmail;
    }

    public void setEmailNeedsAgreement(boolean emailNeedsAgreement) {
      this.emailNeedsAgreement = emailNeedsAgreement;
    }

    public void setEmailValid(boolean emailValid) {
      isEmailValid = emailValid;
    }

    public void setEmailVerified(boolean emailVerified) {
      isEmailVerified = emailVerified;
    }
  }
}
