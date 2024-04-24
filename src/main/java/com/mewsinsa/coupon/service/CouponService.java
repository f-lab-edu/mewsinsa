package com.mewsinsa.coupon.service;


import static com.mewsinsa.global.config.ConstantConfig.*;

import com.mewsinsa.coupon.controller.dto.AddCouponRequestDto;
import com.mewsinsa.coupon.domain.Coupon;
import com.mewsinsa.coupon.repository.CouponRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CouponService {
  private final CouponRepository couponRepository;

  //==Constructor==//
  public CouponService(CouponRepository couponRepository) {
    this.couponRepository = couponRepository;
  }

  //==유효한 쿠폰 검사 로직==//
  boolean isValidCoupon(Coupon coupon) {
    if(coupon.getCouponType() == FIXED_RATE
        && (coupon.getDiscountRate() == null || coupon.getDiscountRate() <= 0)) {
      return false; // 잘못된 쿠폰
    } else if(coupon.getCouponType() == FIXED_AMOUNT
      && (coupon.getDiscountAmount() == null || coupon.getDiscountAmount() <= 0)) {
      return false; // 잘못된 쿠폰
    }

    return true; // 유효한 쿠폰
  }


  public Coupon addCoupon(AddCouponRequestDto couponDto) {
    Coupon coupon;
    try {
      coupon = new Coupon(
          0L,
          couponDto.getCouponName(),
          couponDto.getCouponType(),
          couponDto.getDiscountRate(),
          couponDto.getDiscountAmount(),
          couponDto.getStartedAt(),
          couponDto.getExpiredAt(),
          couponDto.getRemaining()
      );

      // 유효한 쿠폰인지 검사
      if(!isValidCoupon(coupon)) {
        throw new IllegalArgumentException();
      }

      // 쿠폰을 저장
      couponRepository.addCoupon(coupon);
      Long couponId = coupon.getCouponId();

      List<Long> couponProducts = couponDto.getCouponProductList();
      for(long productId : couponProducts) {
        couponRepository.addCouponProduct(productId, couponId);
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("쿠폰 등록에 실패하였습니다.", e);
    }

    return coupon;
  }

  public List<Coupon> findOngoingCoupons(Integer page) {
    List<Coupon> couponList;
    try {
      couponList = couponRepository.findOngoingCoupons(page);
    } catch (Exception e) {
      throw new IllegalArgumentException("쿠폰 조회에 실패하였습니다.", e);
    }

    return couponList;
  }

  public List<Coupon> findAvailableCouponsToProduct(Long productId) {
    List<Coupon> availableCouponList;
    try {
      availableCouponList = couponRepository.findAvailableCouponsToProduct(productId);
    } catch (Exception e) {
      throw new IllegalArgumentException("상품에 적용 가능한 쿠폰 조회에 실패하였습니다.", e);
    }

    return availableCouponList;
  }
}
