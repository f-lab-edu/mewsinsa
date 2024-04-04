package com.mewsinsa.promotion.service;

import com.mewsinsa.promotion.controller.dto.AddPromotionRequestDto;
import com.mewsinsa.promotion.controller.dto.PromotionDto;
import com.mewsinsa.promotion.domain.Promotion;
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

  public void addPromotion(AddPromotionRequestDto promotionDto) {
    try {
      Promotion promotion = new Promotion(
          0L,
          promotionDto.getPromotionName(),
          promotionDto.getPromotionType(),
          promotionDto.getDiscountRate(),
          promotionDto.getDiscountAmount(),
          promotionDto.getStartedAt(),
          promotionDto.getExpiredAt()
      );

      // 프로모션을 저장
      promotionRepository.addPromotion(promotion);
      Long promotionId = promotion.getPromotionId();

      List<Long> promotionProduts = promotionDto.getPromotionProductList();
      for(long productId : promotionProduts) {
        promotionRepository.addPromotionProduct(productId, promotionId);
      }
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
