package com.mewsinsa.display.repository;

import com.mewsinsa.display.controller.dto.DisplayProductResponseDto;
import com.mewsinsa.display.domain.ProductDisplay;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface DisplayRepository {
  public HashMap<String, Object> displayProductDetail(@Param("productId") Long productId);
  public List<DisplayProductResponseDto> displayProductListBySubcategory(@Param("subcategory") String subcategory,
      @Param("page") Integer page,
      @Param("count") Integer count);

  public HashMap<String, Object> displayProductInfo(@Param("productId") Long productId);

}
