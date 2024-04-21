package com.mewsinsa.payment.repository;

import com.mewsinsa.payment.domain.Receipt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReceiptRepository {
  public void saveReceipt(Receipt receipt);
  public Receipt findOneReceiptByOrderId(@Param("orderId") Long orderId);

  public void updateReceiptIsRefunded(@Param("receiptId") Long receiptId, @Param("isRefunded") Boolean isRefunded);

}
