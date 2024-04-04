package com.mewsinsa.coupon.controller;

import com.mewsinsa.coupon.controller.dto.AddCouponRequestDto;
import com.mewsinsa.coupon.domain.Coupon;
import com.mewsinsa.coupon.service.CouponService;
import com.mewsinsa.global.response.HttpStatusEnum;
import com.mewsinsa.global.response.SuccessResponse;
import com.mewsinsa.promotion.controller.dto.AddPromotionRequestDto;
import com.mewsinsa.promotion.controller.dto.PromotionDto;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/coupons")
@RestController
public class CouponController {
  private final CouponService couponService;

  //==Constructor==//
  public CouponController(CouponService couponService) {
    this.couponService = couponService;
  }


  @PostMapping
  SuccessResponse addPromotion(@Validated @RequestBody AddCouponRequestDto coupon) {
    couponService.addCoupon(coupon);

    return new SuccessResponse
        .Builder(HttpStatusEnum.CREATED)
        .message("쿠폰이 정상적으로 등록되었습니다.")
        .build();
  }

  @GetMapping
  SuccessResponse findOngoingCoupons(@RequestParam(value = "page", required = false) Integer page) {
    if(page == null) {
      page = 1;
    }
    List<Coupon> couponList = couponService.findOngoingCoupons(page);

    return new SuccessResponse
        .Builder(HttpStatusEnum.OK)
        .data(couponList)
        .build();
  }


}
