package com.mewsinsa.coupon.controller;

import com.mewsinsa.coupon.controller.dto.AddCouponRequestDto;
import com.mewsinsa.coupon.domain.Coupon;
import com.mewsinsa.coupon.service.CouponService;
import com.mewsinsa.global.response.HttpStatusEnum;
import com.mewsinsa.global.response.SuccessResult;
import com.mewsinsa.global.response.SuccessResult.Builder;
import com.mewsinsa.promotion.controller.dto.AddPromotionRequestDto;
import com.mewsinsa.promotion.controller.dto.PromotionDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  ResponseEntity<SuccessResult> addCoupon(@Validated @RequestBody AddCouponRequestDto coupon) {
    couponService.addCoupon(coupon);

    SuccessResult result = new Builder(HttpStatusEnum.CREATED)
        .message("쿠폰이 정상적으로 등록되었습니다.")
        .build();

    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @GetMapping
  ResponseEntity<SuccessResult> findOngoingCoupons(@RequestParam(value = "page", required = false) Integer page) {
    if(page == null) {
      page = 1;
    }
    List<Coupon> couponList = couponService.findOngoingCoupons(page);

    SuccessResult result = new Builder(HttpStatusEnum.OK)
        .data(couponList)
        .build();

    return new ResponseEntity<>(result, HttpStatus.OK);
  }


  @GetMapping("/products/{productId}")
  ResponseEntity<SuccessResult> findAvailableCouponsToProduct(@PathVariable("productId") @Positive Long productId) {
    List<Coupon> availableCouponList = couponService.findAvailableCouponsToProduct(productId);

    SuccessResult result = new Builder(HttpStatusEnum.OK)
        .data(availableCouponList)
        .build();

    return new ResponseEntity<>(result, HttpStatus.OK);
  }


}
