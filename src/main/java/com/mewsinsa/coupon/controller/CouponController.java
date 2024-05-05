package com.mewsinsa.coupon.controller;

import com.mewsinsa.auth.jwt.JwtProvider;
import com.mewsinsa.coupon.controller.dto.AddCouponRequestDto;
import com.mewsinsa.coupon.domain.Coupon;
import com.mewsinsa.coupon.domain.IssuedCoupon;
import com.mewsinsa.coupon.service.CouponService;
import com.mewsinsa.global.response.DetailedStatus;
import com.mewsinsa.global.response.SuccessResult;
import com.mewsinsa.global.response.SuccessResult.Builder;
import com.mewsinsa.member.service.MemberService;
import com.mewsinsa.promotion.controller.dto.AddPromotionRequestDto;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/coupons")
@RestController
public class CouponController {
  private final CouponService couponService;
  private final MemberService memberService;

  //==Constructor==//
  public CouponController(CouponService couponService, MemberService memberService) {
    this.couponService = couponService;
    this.memberService = memberService;
  }


  /**
   * 새로운 쿠폰을 등록
   * @param coupon 등록할 쿠폰 정보
   * @return ResponseEntity. 등록된 쿠폰의 정보
   */
  @PostMapping
  ResponseEntity<SuccessResult> addCoupon(@Validated @RequestBody AddCouponRequestDto coupon) {
    Coupon registeredCoupon = couponService.addCoupon(coupon);

    SuccessResult result = new Builder(DetailedStatus.CREATED)
        .data(registeredCoupon)
        .message("쿠폰이 정상적으로 등록되었습니다.")
        .build();

    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  /**
   * 전체 쿠폰을 페이지 단위로 조회 (30개씩)
   * @param page 페이지 정보
   * @return 해당 페이지의 쿠폰 리스트
   */
  @GetMapping
  ResponseEntity<SuccessResult> findOngoingCoupons(@RequestParam(value = "page", required = false) Integer page) {
    if(page == null) {
      page = 1;
    }
    List<Coupon> couponList = couponService.findOngoingCoupons(page);

    SuccessResult result = new Builder(DetailedStatus.OK)
        .data(couponList)
        .build();

    return new ResponseEntity<>(result, HttpStatus.OK);
  }


  /**
   * 특정 상품에 적용할 수 있는 전체 쿠폰 리스트 조회
   * @param productId 특정 상품의 Id
   * @return 상품에 적용 가능한 쿠폰 리스트
   */
  @GetMapping("/products/{productId}")
  ResponseEntity<SuccessResult> findAvailableCouponsToProduct(@PathVariable("productId") @Positive Long productId) {
    List<Coupon> availableCouponList = couponService.findAvailableCouponsToProduct(productId);

    SuccessResult result = new Builder(DetailedStatus.OK)
        .data(availableCouponList)
        .build();

    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PostMapping("/{couponId}/issue")
  ResponseEntity<SuccessResult> issueCoupon(@PathVariable("couponId") Long couponId,
      @RequestHeader(value= JwtProvider.ACCESS_HEADER_STRING, required=false) String accessToken) {
    Long memberId = memberService.getMemberIdByAccessToken(accessToken);
    IssuedCoupon issuedCoupon = couponService.issueCoupon(couponId, memberId);

    SuccessResult result = new Builder(DetailedStatus.CREATED)
        .message("쿠폰이 발급되었습니다.")
        .data(issuedCoupon)
        .build();

    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

}
