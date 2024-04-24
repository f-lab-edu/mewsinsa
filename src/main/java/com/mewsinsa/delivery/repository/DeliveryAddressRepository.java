package com.mewsinsa.delivery.repository;

import com.mewsinsa.delivery.domain.DeliveryAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DeliveryAddressRepository {
  public DeliveryAddress findOneDeliveryAddressByDeliveryAddressId(@Param("deliveryAddressId") Long deliveryAddressId);
  public DeliveryAddress findDefaultDeliveryAddressByMemberId(@Param("memberId") Long memberId);
}
