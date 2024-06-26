package com.mewsinsa.promotion.service;
import com.mewsinsa.promotion.controller.dto.AddPromotionRequestDto;
import com.mewsinsa.promotion.controller.dto.PromotionDto;
import com.mewsinsa.promotion.domain.Promotion;
import com.mewsinsa.promotion.domain.PromotionType;
import com.mewsinsa.promotion.repository.PromotionRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PromotionService {
  private final PromotionRepository promotionRepository;

  //==Constructor==//
  public PromotionService(PromotionRepository promotionRepository) {
    this.promotionRepository = promotionRepository;
  }

  //==유효한 프로모션 검사 로직==//
  boolean isValidPromotion(Promotion promotion) {
    if(promotion.getPromotionType().equals(PromotionType.FIXED_RATE.getType())
        && (promotion.getDiscountRate() == null || promotion.getDiscountRate() <= 0)) {
      return false; // 잘못된 프로모션
    } else if(promotion.getPromotionType().equals(PromotionType.FIXED_AMOUNT.getType())
        && (promotion.getDiscountAmount() == null || promotion.getDiscountAmount() <= 0)) {
      return false; // 잘못된 프로모션
    }

    return true; // 유효한 프로모션
  }

  @Transactional
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

      if(!isValidPromotion(promotion)) {
        throw new IllegalArgumentException();
      }

      // 프로모션을 저장
      promotionRepository.addPromotion(promotion);
      Long promotionId = promotion.getPromotionId();

      List<Long> promotionProduts = promotionDto.getPromotionProductList();
      for(long productId : promotionProduts) {
        promotionRepository.addPromotionProduct(productId, promotionId);
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("프로모션 등록에 실패하였습니다.", e);
    }
  }

  public List<PromotionDto> findOngoingPromotions(Integer page) {
    List<PromotionDto> promotionList;
    try {
      promotionList = promotionRepository.findOngoingPromotions(page);
    } catch (Exception e) {
      throw new IllegalArgumentException("프로모션 조회에 실패하였습니다.", e);
    }

    return promotionList;
  }
}
