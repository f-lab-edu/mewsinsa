package com.mewsinsa.display.repository;

import com.mewsinsa.display.domain.ProductDisplay;
import java.util.HashMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface DisplayRepository {
  public HashMap<String, Object> displayProductDetail(@Param("productId") Long productId);

}
