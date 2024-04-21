package com.mewsinsa.order.repository;

import com.mewsinsa.order.controller.dto.OrderListResponseForAdminDto;
import com.mewsinsa.order.controller.dto.OrderListResponseForMemberDto;
import com.mewsinsa.order.domain.Order;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderRepository {

  public void addOrder(Order order);

  public List<OrderListResponseForAdminDto> findAllOrders(@Param("page") int page, @Param("count") int count);
  public List<OrderListResponseForMemberDto> findAllMemberOrders(
      @Param("memberId") Long memberId,
      @Param("page") int page,
      @Param("count") int count,
      @Param("dateFrom") String dataFrom,
      @Param("dateTo") String dataTo
  );

  public void deleteOrderByOrderId(@Param("orderId") Long orderId);

  public Order findOneOrderByOrderId(@Param("orderId") Long orderId);

  public void updateDeliveryAddressInOrder(
      @Param("orderId") Long orderId,
      @Param("receiverName") String receiverName,
      @Param("receiverPhone") String receiverPhone,
      @Param("receiverAddress") String receiverAddress
  );

}
