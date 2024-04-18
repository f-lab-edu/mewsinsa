package com.mewsinsa.promotion.repository;

import com.mewsinsa.promotion.controller.dto.AddPromotionRequestDto;
import com.mewsinsa.promotion.controller.dto.PromotionDto;
import com.mewsinsa.promotion.domain.Promotion;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PromotionRepository {
  void addPromotion(Promotion promotion);

  List<PromotionDto> findOngoingPromotions(Integer page);

  void addPromotionProduct(@Param("productId")Long productId, @Param("promotionId")Long promotionId);

  Promotion findOnePromotionByPromotionId(@Param("promotionId") Long promotionId);

  List<Promotion> findPromotionListByProductId(@Param("productId") Long productId);

}
