package com.mewsinsa.coupon.repository;


import com.mewsinsa.coupon.domain.Coupon;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface CouponRepository {
  void addCoupon(Coupon coupon);

  List<Coupon> findOngoingCoupons(Integer page);

  void addCouponProduct(@Param("productId")Long productId, @Param("couponId")Long couponId);
}
