package com.mewsinsa.coupon.service;


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


  public void addCoupon(AddCouponRequestDto couponDto) {
    try {
      Coupon coupon = new Coupon(
          0L,
          couponDto.getCouponName(),
          couponDto.getCouponType(),
          couponDto.getDiscountRate(),
          couponDto.getDiscountAmount(),
          couponDto.getStartedAt(),
          couponDto.getExpiredAt()
      );

      // 프로모션을 저장
      couponRepository.addCoupon(coupon);
      Long couponId = coupon.getCouponId();

      List<Long> couponProduts = couponDto.getCouponProductList();
      for(long productId : couponProduts) {
        couponRepository.addCouponProduct(productId, couponId);
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("쿠폰 등록에 실패하였습니다.", e);
    }
  }

  public List<Coupon> findOngoingCoupons(Integer page) {
    List<Coupon> couponList;
    try {
      couponList = couponRepository.findOngoingCoupons(page);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("쿠폰 조회에 실패하였습니다.", e);
    }

    return couponList;
  }

}
