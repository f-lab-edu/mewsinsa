package com.mewsinsa.auth.jwt.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SignUpRequestDto {
  @NotEmpty
  private String mewsinsaId;
  @NotEmpty
  private String password;

  @NotEmpty
  private String name;
  private String nickname;
  @Email
  @NotEmpty
  private String email;
  @NotEmpty
  private String phone;
  private String profileImage;
  @NotEmpty
  private Integer tierId;
  @NotEmpty
  private Boolean isAdmin;
  private Long points;

  //==Constructor==//
  public SignUpRequestDto() {
  }

  public SignUpRequestDto(String mewsinsaId, String password, String name, String nickname,
      String email, String phone, String profileImage, Integer tierId, Boolean isAdmin,
      Long points) {
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
  }

  //==Getter==//
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

  public String getEmail() {
    return email;
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

  //==Setter==//
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

  public void setEmail(String email) {
    this.email = email;
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
}
