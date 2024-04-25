package com.mewsinsa.coupon.repository;


import com.mewsinsa.coupon.domain.Coupon;
import com.mewsinsa.coupon.domain.IssuedCoupon;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface CouponRepository {
  void addCoupon(Coupon coupon);

  List<Coupon> findOngoingCoupons(Integer page);

  void addCouponProduct(@Param("productId")Long productId, @Param("couponId") Long couponId);

  List<Coupon> findAvailableCouponsToProduct(@Param("productId") Long productId);

  Coupon findOneCoupon(@Param("couponId") Long couponId);

  Coupon findOneCouponProduct(@Param("productId") Long productId, @Param("couponId") Long couponId);

  IssuedCoupon findOneIssuedCoupon(@Param("couponId") Long couponId, @Param("memberId") Long memberId);
  void saveIssuedCoupon(IssuedCoupon issuedCoupon);

  void decreaseCouponRemaining(@Param("couponId") Long couponId);

  void updateUsedInIssuedCoupon(
      @Param("issuedCouponId") Long issuedCouponId,
      @Param("isUsed") Boolean isUsed,
      @Param("usedAt")LocalDateTime usedAt
      );
}
