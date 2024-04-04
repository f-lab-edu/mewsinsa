package com.mewsinsa.promotion.repository;

import com.mewsinsa.promotion.controller.dto.AddPromotionRequestDto;
import com.mewsinsa.promotion.controller.dto.PromotionDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PromotionRepository {
  void addPromotion(AddPromotionRequestDto promotion);

  List<PromotionDto> findOngoingPromotions(Integer page);

}
