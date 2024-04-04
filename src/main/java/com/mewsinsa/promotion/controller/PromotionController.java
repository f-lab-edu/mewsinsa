package com.mewsinsa.promotion.controller;

import com.mewsinsa.global.response.HttpStatusEnum;
import com.mewsinsa.global.response.SuccessResponse;
import com.mewsinsa.promotion.controller.dto.AddPromotionRequestDto;
import com.mewsinsa.promotion.controller.dto.PromotionDto;
import com.mewsinsa.promotion.service.PromotionService;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/promotions")
@RestController
public class PromotionController {
  private final PromotionService promotionService;

  //==Constructor==//
  public PromotionController(PromotionService promotionService) {
    this.promotionService = promotionService;
  }

  @PostMapping
  SuccessResponse addPromotion(@Validated @RequestBody AddPromotionRequestDto promotion) {
    promotionService.addPromotion(promotion);

    return new SuccessResponse
        .Builder(HttpStatusEnum.CREATED)
        .message("프로모션이 정상적으로 등록되었습니다.")
        .build();
  }

  @GetMapping
  SuccessResponse findOngoingPromotions(@RequestParam(value = "page", required = false) Integer page) {
    if(page == null) {
      page = 1;
    }
    List<PromotionDto> promotionList = promotionService.findOngoingPromotions(page);

    return new SuccessResponse
        .Builder(HttpStatusEnum.OK)
        .data(promotionList)
        .build();
  }


}
