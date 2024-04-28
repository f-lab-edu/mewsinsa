package com.mewsinsa.member.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.data.annotation.Id;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Member {
  private Long memberId;
  private String mewsinsaId;
  private String password;
  private String name;
  private String nickname;
  private String email;
  private String phone;
  private String profileImage;
  private Integer tierId;
  private Boolean isAdmin;
  private Long points;
  private Long defaultDeliveryAddressId;


  //==Constructor==//
  public Member() {
  }

  public Member(Long memberId, String mewsinsaId, String password, String name, String nickname,
      String email, String phone, String profileImage, Integer tierId, Boolean isAdmin, Long points,
      Long defaultDeliveryAddressId) {
    this.memberId = memberId;
    this.mewsinsaId = mewsinsaId;
    this.password = password;
    this.name = name;
    this.nickname = nickname;
    this.email = email;
    this.phone = phone;
    this.profileImage = profileImage;
    this.tierId = tierId;
    this.isAdmin = isAdmin;
    this.points = points;
    this.defaultDeliveryAddressId = defaultDeliveryAddressId;
  }

  public Member(Builder builder) {
    this.memberId = builder.memberId;
    this.mewsinsaId = builder.mewsinsaId;
    this.password = builder.password;
    this.name = builder.name;
    this.nickname = builder.nickname;
    this.email = builder.email;
    this.phone = builder.phone;
    this.profileImage = builder.profileImage;
    this.tierId = builder.tierId;
    this.isAdmin = builder.isAdmin;
    this.points = builder.points;
    this.defaultDeliveryAddressId = builder.defaultDeliveryAddressId;
  }


  //==Getter==//
  public Long getMemberId() {
    return memberId;
  }

  public String getMewsinsaId() {
    return mewsinsaId;
  }

  public String getPassword() {
    return password;
  }

  public String getName() {
    return name;
  }

  public String getNickname() {
    return nickname;
  }

  public String getPhone() {
    return phone;
  }

  public String getProfileImage() {
    return profileImage;
  }

  public Integer getTierId() {
    return tierId;
  }

  public Boolean getAdmin() {
    return isAdmin;
  }

  public Long getPoints() {
    return points;
  }

  public String getEmail() {
    return email;
  }

  public Long getDefaultDeliveryAddressId() {
    return defaultDeliveryAddressId;
  }

  //==Setter==//
  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public void setMewsinsaId(String mewsinsaId) {
    this.mewsinsaId = mewsinsaId;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setProfileImage(String profileImage) {
    this.profileImage = profileImage;
  }

  public void setTierId(Integer tierId) {
    this.tierId = tierId;
  }

  public void setAdmin(Boolean admin) {
    isAdmin = admin;
  }

  public void setPoints(Long points) {
    this.points = points;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setDefaultDeliveryAddressId(Long defaultDeliveryAddressId) {
    this.defaultDeliveryAddressId = defaultDeliveryAddressId;
  }

  //==Builder==//
  public static class Builder {
    private Long memberId;
    private String mewsinsaId;
    private String password;
    private String name;
    private String nickname;
    private String email;
    private String phone;
    private String profileImage;
    private Integer tierId;
    private Boolean isAdmin;
    private Long points;
    private Long defaultDeliveryAddressId;

    public Builder memberId(Long memberId) {
      this.memberId = memberId;
      return this;
    }

    public Builder mewsinsaId(String mewsinsaId) {
      this.mewsinsaId = mewsinsaId;
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder nickname(String nickname) {
      this.nickname = nickname;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public Builder phone(String phone) {
      this.phone = phone;
      return this;
    }

    public Builder profileImage(String profileImage) {
      this.profileImage = profileImage;
      return this;
    }

    public Builder tierId(Integer tierId) {
      this.tierId = tierId;
      return this;
    }

    public Builder isAdmin(Boolean admin) {
      isAdmin = admin;
      return this;
    }

    public Builder points(Long points) {
      this.points = points;
      return this;
    }

    public Builder defaultdeliveryAddressId(Long defaultDeliveryAddressId) {
      this.defaultDeliveryAddressId = defaultDeliveryAddressId;
      return this;
    }

    public Member build() {
      return new Member(this);
    }
  }
}
