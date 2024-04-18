package com.mewsinsa.payment.repository;

import com.mewsinsa.payment.domain.Receipt;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReceiptRepository {
  public void saveReceipt(Receipt receipt);

}
