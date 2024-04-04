package com.mewsinsa.promotion.service;

import com.mewsinsa.promotion.controller.dto.AddPromotionRequestDto;
import com.mewsinsa.promotion.controller.dto.PromotionDto;
import com.mewsinsa.promotion.repository.PromotionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {
  private final PromotionRepository promotionRepository;

  //==Constructor==//
  public PromotionService(PromotionRepository promotionRepository) {
    this.promotionRepository = promotionRepository;
  }

  public void addPromotion(AddPromotionRequestDto promotion) {
    try {
      promotionRepository.addPromotion(promotion);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("프로모션 등록에 실패하였습니다.", e);
    }
  }

  public List<PromotionDto> findOngoingPromotions(Integer page) {
    List<PromotionDto> promotionList;
    try {
      promotionList = promotionRepository.findOngoingPromotions(page);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("프로모션 조회에 실패하였습니다.", e);
    }

    return promotionList;
  }
}
