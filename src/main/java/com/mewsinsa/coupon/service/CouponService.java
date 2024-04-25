package com.mewsinsa.coupon.service;


import static com.mewsinsa.global.config.ConstantConfig.*;

import com.mewsinsa.auth.jwt.repository.AccessTokenRepository;
import com.mewsinsa.coupon.controller.dto.AddCouponRequestDto;
import com.mewsinsa.coupon.domain.Coupon;
import com.mewsinsa.coupon.domain.IssuedCoupon;
import com.mewsinsa.coupon.exception.FailToIssueCouponException;
import com.mewsinsa.coupon.repository.CouponRepository;
import com.mewsinsa.global.response.DetailedStatus;
import com.mewsinsa.member.domain.Member;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CouponService {
  private final CouponRepository couponRepository;

  //==Constructor==//
  public CouponService(CouponRepository couponRepository) {
    this.couponRepository = couponRepository;
  }

  @Transactional
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

  @Transactional
  public IssuedCoupon issueCoupon(Long couponId, Long memberId) {

    // 쿠폰 수량 감소
    try {
      couponRepository.decreaseCouponRemaining(couponId);
    } catch(Exception e) { // 수량 부족. (remaining => Unsigned)
      throw new FailToIssueCouponException(DetailedStatus.OUT_OF_REMAINING);
    }

    // issuedAt(지금)이 startedAt과 expiredAt 사이에 있는지를 체크합니다.
    LocalDateTime issuedAt = LocalDateTime.now();
    isPossibleToIssue(couponId, issuedAt);

    // 중복 발급인지 체크합니다.
    isDuplicatedIssuedCoupon(couponId, memberId);

    IssuedCoupon issuedCoupon = new IssuedCoupon.Builder()
        .issuedAt(issuedAt)
        .isUsed(false)
        .couponId(couponId)
        .memberId(memberId)
        .build();

    couponRepository.saveIssuedCoupon(issuedCoupon);

    return issuedCoupon;
  }

  // 쿠폰 등록 시 유효한 쿠폰인지 검사합니다.
  private boolean isValidCoupon(Coupon coupon) {
    if(coupon.getCouponType() == FIXED_RATE
        && (coupon.getDiscountRate() == null || coupon.getDiscountRate() <= 0)) {
      return false; // 잘못된 쿠폰
    } else if(coupon.getCouponType() == FIXED_AMOUNT
        && (coupon.getDiscountAmount() == null || coupon.getDiscountAmount() <= 0)) {
      return false; // 잘못된 쿠폰
    }
    return true; // 유효한 쿠폰
  }


  //== 유효한 요청인지 체크 ==//

  // 발급되는 쿠폰의 수량과 기한이 적절한지 체크합니다.
  private void isPossibleToIssue(Long couponId, LocalDateTime issuedAt) {
    Coupon coupon = couponRepository.findOneCoupon(couponId);

    if(coupon == null) { // 없는 쿠폰
      throw new FailToIssueCouponException(DetailedStatus.NON_EXSISTENT_COUPON);
    }

    if(issuedAt.isBefore(coupon.getStartedAt())
    || issuedAt.isAfter(coupon.getExpiredAt())) {
      throw new FailToIssueCouponException(DetailedStatus.NOT_ISSUANCE_PERIOD);
    }
  }

  // 같은 회원이 같은 쿠폰을 중복 발급하는 것을 방지합니다.
  private void isDuplicatedIssuedCoupon(Long couponId, Long memberId) {
    IssuedCoupon issuedCoupon = couponRepository.findOneIssuedCoupon(couponId, memberId);

    if(issuedCoupon != null) {
      throw new FailToIssueCouponException(DetailedStatus.DUPLICATED_ISSUED_COUPON);
    }
  }

}
