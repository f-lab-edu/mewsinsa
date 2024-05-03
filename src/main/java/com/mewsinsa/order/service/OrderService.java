package com.mewsinsa.order.service;

import com.mewsinsa.auth.jwt.service.JwtService;
import com.mewsinsa.coupon.domain.Coupon;
import com.mewsinsa.coupon.domain.IssuedCoupon;
import com.mewsinsa.coupon.exception.FailToIssueCouponException;
import com.mewsinsa.coupon.repository.CouponRepository;
import com.mewsinsa.delivery.domain.DeliveryAddress;
import com.mewsinsa.delivery.repository.DeliveryAddressRepository;
import com.mewsinsa.global.response.DetailedStatus;
import com.mewsinsa.member.domain.Member;
import com.mewsinsa.member.domain.Tier;
import com.mewsinsa.member.repository.MemberRepository;
import com.mewsinsa.member.service.MemberService;
import com.mewsinsa.order.controller.dto.OrderDeliveryAddressDto;
import com.mewsinsa.order.controller.dto.admin.OrderInfoResponseForAdminDto;
import com.mewsinsa.order.controller.dto.admin.OrderListResponseForAdminDto;
import com.mewsinsa.order.controller.dto.OrderListResponseForMemberDto;
import com.mewsinsa.order.controller.dto.OrderResponseDto;
import com.mewsinsa.order.controller.dto.OrderedProductDto;
import com.mewsinsa.order.controller.dto.form.OrderFormProductDto;
import com.mewsinsa.order.controller.dto.form.OrderFormRequestDto;
import com.mewsinsa.order.controller.dto.form.OrderFormResponseDto;
import com.mewsinsa.order.controller.dto.form.OrderedProductInfoDto;
import com.mewsinsa.order.domain.History;
import com.mewsinsa.order.domain.Order;
import com.mewsinsa.order.domain.OrderStatus;
import com.mewsinsa.order.domain.OrderedProduct;
import com.mewsinsa.order.exception.DeliveryAddressUpdateException;
import com.mewsinsa.order.exception.InvalidProductOptionException;
import com.mewsinsa.order.exception.NotApplicapableCouponException;
import com.mewsinsa.order.exception.OrderCancellationException;
import com.mewsinsa.order.exception.OrderException;
import com.mewsinsa.order.exception.OutOfStockException;
import com.mewsinsa.order.controller.dto.OrderRequestDto;
import com.mewsinsa.order.repository.HistoryRepository;
import com.mewsinsa.order.repository.OrderRepository;
import com.mewsinsa.order.repository.OrderedProductRepository;
import com.mewsinsa.payment.domain.Receipt;
import com.mewsinsa.payment.repository.ReceiptRepository;
import com.mewsinsa.product.domain.Product;
import com.mewsinsa.product.domain.ProductOption;
import com.mewsinsa.product.repository.ProductRepository;
import com.mewsinsa.promotion.domain.Promotion;
import com.mewsinsa.promotion.repository.PromotionRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
  Logger log = LoggerFactory.getLogger(getClass());
  private final ProductRepository productRepository;
  private final OrderRepository orderRepository;
  private final HistoryRepository historyRepository;
  private final OrderedProductRepository orderedProductRepository;
  private final CouponRepository couponRepository;
  private final PromotionRepository promotionRepository;
  private final MemberRepository memberRepository;
  private final DeliveryAddressRepository deliveryAddressRepository;
  private final ReceiptRepository receiptRepository;
  private final MemberService memberService;
  private final JwtService jwtService;


  public static final boolean FIXED_DISCOUNT = false;
  public static final boolean RATE_DISCOUNT = true;

  private static Map<Integer, Tier> tierMap = new HashMap<>();

  public OrderService(ProductRepository productRepository, OrderRepository orderRepository,
      HistoryRepository historyRepository, OrderedProductRepository orderedProductRepository,
      CouponRepository couponRepository, PromotionRepository promotionRepository,
      MemberRepository memberRepository, DeliveryAddressRepository deliveryAddressRepository, ReceiptRepository receiptRepository,
      MemberService memberService, JwtService jwtService) {
    this.productRepository = productRepository;
    this.orderRepository = orderRepository;
    this.historyRepository = historyRepository;
    this.orderedProductRepository = orderedProductRepository;
    this.couponRepository = couponRepository;
    this.promotionRepository = promotionRepository;
    this.memberRepository = memberRepository;
    this.deliveryAddressRepository = deliveryAddressRepository;
    this.receiptRepository = receiptRepository;
    this.memberService = memberService;
    this.jwtService = jwtService;

    for(Tier tier: Tier.values()) {
        tierMap.put(tier.getTierId(), tier);
    }
  }

  // readOnly
  public OrderFormResponseDto orderForm(String accessToken, OrderFormRequestDto orderFormRequestDto) {
    // 회원 기본 배송지 불러오기
    // 1. 액세스 토큰으로 회원 정보 찾아오기
    Member member = jwtService.findMemberByAccessToken(accessToken);
    Integer tierId = member.getTierId();
    String tierName = tierMap.get(tierId).getTierName();

    // 2. 회원의 기본 배송지 정보를 불러오기
    DeliveryAddress deliveryAddress = deliveryAddressRepository.findDefaultDeliveryAddressByMemberId(member.getMemberId());


    // 상품 정보 불러오기 (적용 가능한 쿠폰까지)
    Long totalPoints = 0L, totalTierDiscountAmount = 0L;
    List<OrderedProductInfoDto> productInfoList = new ArrayList<>();
    List<OrderFormProductDto> orderedProductList = orderFormRequestDto.getOrderedProductList();
    for(OrderFormProductDto orderedProduct : orderedProductList) {
      OrderedProductInfoDto tempOrderedProductInfo = productRepository.findOneOrderedProductInfo(orderedProduct.getProductOptionId());

      if(tempOrderedProductInfo == null) {
        throw new InvalidProductOptionException("해당 상품 옵션이 존재하지 않습니다.", orderedProduct.getProductOptionId());
      }

      // 찾아온 데이터를 꺼내기
      Long productId = tempOrderedProductInfo.getProductId();
      String productName = tempOrderedProductInfo.getProductName();
      Long productOptionId = tempOrderedProductInfo.getProductOptionId();
      String productOptionName = tempOrderedProductInfo.getProductOptionName();
      Long brandId = tempOrderedProductInfo.getBrandId();
      String brandName = tempOrderedProductInfo.getBrandName();
      Long unitOriginalPrice = tempOrderedProductInfo.getUnitOriginalPrice();
      Long quantity = orderedProduct.getQuantity();

      // 적용 가능한 쿠폰 리스트
      List<Coupon> couponList = couponRepository.findAvailableCouponsToProduct(productId);

      // 프로모션 단가 계산
      Long unitPromotionPrice = calculatePromotionPrice(productId, unitOriginalPrice);

      Long discountAmount = quantity * (unitOriginalPrice - unitPromotionPrice);
      Long pieceOriginalPrice = unitOriginalPrice * quantity;
      Long piecePromotionPrice = unitPromotionPrice * quantity;

      // 회원 할인금액, 적립금 계산
      Long unitTierDiscountAmount = calculateTierDiscountAmount(member.getTierId(), unitPromotionPrice);
      Long points = quantity * calcaulateUnitPoints(member.getTierId(), unitPromotionPrice);

      OrderedProductInfoDto orderedProductInfoDto = new OrderedProductInfoDto.Builder()
          .productId(productId)
          .productName(productName)
          .productOptionId(productOptionId)
          .productOptionName(productOptionName)
          .brandId(brandId)
          .brandName(brandName)
          .unitOriginalPrice(unitOriginalPrice)
          .unitPromotionPrice(unitPromotionPrice)
          .pieceOriginalPrice(pieceOriginalPrice)
          .piecePromotionPrice(piecePromotionPrice)
          .points(points)
          .discountAmount(discountAmount)
          .quantity(quantity)
          .couponList(couponList)
          .unitTierDiscountAmount(unitTierDiscountAmount)
          .pieceTierDiscountAmount(quantity * unitTierDiscountAmount)
          .build();

      productInfoList.add(orderedProductInfoDto);

      totalPoints += points;
      totalTierDiscountAmount += quantity * unitTierDiscountAmount;
    }

    return new OrderFormResponseDto(deliveryAddress, productInfoList, totalTierDiscountAmount, totalPoints, tierId, tierName);
  }

  /**
   * 주문 하기
   * @param orderRequestDto
   * @return orderId (주문을 생성한 뒤, 생성된 주문의 고유번호를 돌려준다.)
   */
  @Transactional // REQUIRED
  public OrderResponseDto makeOrder(OrderRequestDto orderRequestDto, Long memberId) {


    Member member = memberRepository.findMemberById(memberId);
    Integer tierId = member.getTierId();
    Long totalPrice = 0L;
    Long usedPoints = orderRequestDto.getUsedPoints();
    boolean usePointsInAdvance = orderRequestDto.getUsePointsInAdvance();

    // 주문이 들어온 시각
    LocalDateTime orderedAt = LocalDateTime.now();

    Order newOrder = new Order.Builder()
        .memberId(memberId)
        .receiverAddress(orderRequestDto.getReceiverAddress())
        .receiverPhone(orderRequestDto.getReceiverPhone())
        .receiverName(orderRequestDto.getReceiverName())
        .build();

    orderRepository.addOrder(newOrder);
    Long orderId = newOrder.getOrderId();


    List<OrderedProductDto> orderedProductList = orderRequestDto.getOrderedProductList();
    Map<Long, Long> productIdMap = new HashMap<>(); // key: 옵션 번호, value: productId

    // option의 내용이 올바른 부분인지 체크하는 부분:
    // 1. unitPromotion, piecePromotion의 가격이 일치하는가?
    // 2. 쿠폰과 쿠폰 할인 금액이 올바른가?

    Map<Long, Long> usedCouponMap = new HashMap<>(); // key: couponId, value: productOptionId
    List<Long> usedIssuedCouponIdList = new ArrayList<>(); // 사용된 쿠폰의 아이디를 저장
    Map<Long, Long> piecePriceMap = new HashMap<>(); // ket: productOptionId, value: piecePrice
    Long totalPoints = 0L, totalTierDiscountAmount = 0L;
    for(OrderedProductDto orderedProduct : orderedProductList) {
      // 옵션을 꺼내오기
      Long productOptionId = orderedProduct.getProductOptionId();
      OrderedProductInfoDto tempOrderedProductInfo = productRepository.findOneOrderedProductInfo(orderedProduct.getProductOptionId());

      // 주문에 포함된 옵션이 DB에 존재하지 않는 경우
      if(tempOrderedProductInfo == null) {
        throw new InvalidProductOptionException("해당 상품 옵션이 존재하지 않습니다.", productOptionId);
      }

      // 찾아온 데이터를 꺼내기
      Long productId = tempOrderedProductInfo.getProductId();
      String productName = tempOrderedProductInfo.getProductName();
      String productOptionName = tempOrderedProductInfo.getProductOptionName();
      Long brandId = tempOrderedProductInfo.getBrandId();
      String brandName = tempOrderedProductInfo.getBrandName();
      Long unitOriginalPrice = tempOrderedProductInfo.getUnitOriginalPrice();

      Long quantity = orderedProduct.getQuantity();


      //== 적립금, 할인 금액, piecePromotion(프로모션이 적용된 상품 옵션별 부분 가격)이 일치하는가? ==//
      // 프로모션 단가 계산
      Long unitPromotionPrice = calculatePromotionPrice(productId, unitOriginalPrice);
      Long discountAmount = quantity * (unitOriginalPrice - unitPromotionPrice);
      Long pieceOriginalPrice = unitOriginalPrice * quantity;
      Long piecePromotionPrice = unitPromotionPrice * quantity;

      // 회원 할인금액, 적립금 계산
      Long unitTierDiscountAmount = calculateTierDiscountAmount(member.getTierId(), unitPromotionPrice);
      Long points = quantity * calcaulateUnitPoints(member.getTierId(), unitPromotionPrice);

      // 검증 1. 부분 가격이 맞는지 확인
      // 프로모션가가 맞는지 확인
      if(!Objects.equals(orderedProduct.getPiecePromotionPrice(), piecePromotionPrice)) {
        throw new OrderException("상품의 프로모션가가 틀립니다. / productOptionId: " + productOptionId + ", client piecePromotionPrice: " + orderedProduct.getPiecePromotionPrice() + ", server piecePromotionPrice: " + piecePromotionPrice);
      }
      // 회원 등급 할인 금액이 맞는지 확인
      if(orderedProduct.getPieceTierDiscountAmount() != quantity * unitTierDiscountAmount) {
        throw new OrderException("회원 할인가가 틀립니다. / productOptionId: " + productOptionId + ", client pieceTierDiscountAmount: " + orderedProduct.getPieceTierDiscountAmount() + ", server pieceTierDiscountAmount: " + quantity * unitTierDiscountAmount);
      }
      // 적립금이 맞는지 확인
      if(!Objects.equals(orderedProduct.getPoints(), points)) {
        throw new OrderException("적립금이 틀립니다. / productOptionId: " + productOptionId + ", client points: " + orderedProduct.getPoints() + ", server points: " + points);
      }

      // 적립금 더하기
      totalPoints += points;


      //== 검증 2. 쿠폰이 맞는지 확인 ==//
      Long couponId = orderedProduct.getCouponId();
      // 유저가 해당 쿠폰을 발급 받았는가? -> issued coupon table에서 찾아오기
      IssuedCoupon issuedCoupon = couponRepository.findOneIssuedCoupon(couponId, memberId);
      if(couponId != null && issuedCoupon == null) {
        throw new OrderException("발급 받지 않은 쿠폰입니다. / couponId: " + couponId);
      } else if(issuedCoupon != null && issuedCoupon.getUsed()){
        throw new OrderException("이미 사용된 쿠폰입니다. / couponId: " + couponId);
      }

      // 쿠폰이 해당 상품에 적용 가능한가
      boolean applicableCoupon = isApplicableCoupon(productId, couponId);
      if(!applicableCoupon) {
        throw new NotApplicapableCouponException("해당 상품에 적용 가능한 쿠폰이 아닙니다.", productId, couponId);
      }
      // 쿠폰이 중복 적용됐는가
      if(couponId != null && usedCouponMap.get(couponId) != null) {
        throw new OrderException("쿠폰이 중복 적용되었습니다. / productOptionId: " + productOptionId + ", " + usedCouponMap.get(couponId) + ", couponId: " + couponId);
      }
      // 쿠폰이 존재하는가, 쿠폰의 기한이 남아있는가
      Coupon coupon = couponRepository.findOneCoupon(couponId);
      if(couponId != null && (coupon == null || coupon.getExpiredAt().isBefore(LocalDateTime.now()))) {
        throw new OrderException("존재하지 않거나, 기한이 지난 쿠폰입니다 / couponId: " + couponId);
      }
      // 쿠폰으로 할인 받은 비용이 맞는지 확인
      Long couponDiscountAmount = 0L;
      if(coupon!= null && coupon.getCouponType() == FIXED_DISCOUNT) {
        couponDiscountAmount = coupon.getDiscountAmount();
      } else if(coupon != null) {
        couponDiscountAmount = (long)(piecePromotionPrice * ((double)coupon.getDiscountRate() / 100.0));
      }
      if(couponId != null && !Objects.equals(orderedProduct.getCouponDiscountAmount(), couponDiscountAmount)) {
        throw new OrderException("쿠폰 할인 금액이 틀립니다 / couponId: " + couponId + ", client couponDiscountAmount: "
            + orderedProduct.getCouponDiscountAmount() + ", server couponDiscountAmount: " + couponDiscountAmount);
      }
      // 쿠폰 맵에 추가 -> 현재 주문에서 사용되었음을 의미
      if(couponId != null) {
        usedCouponMap.put(couponId, productOptionId);
        usedIssuedCouponIdList.add(issuedCoupon.getIssuedCouponId());
      }

      // 검증 3. 회원의 적립금이 충분한지
      if(member.getPoints() < usedPoints) {
        throw new OrderException("적립금이 부족합니다. / 회원의 잔여 포인트: " + member.getPoints() + ", 사용 포인트: " + usedPoints);
      } else { // 회원 적립금 업데이트
        memberRepository.updateMemberPoints(memberId, member.getPoints() - usedPoints);
      }

      // 재고 감소
      // 재고를 quantity만큼 감소 시키기 (음수 허용)
      for(int i=0; i<quantity; ++i) {
        reduceStock(productOptionId);
      }

      // piecePrice란: piecePromotionPrice - 쿠폰할인
      Long piecePrice = piecePromotionPrice - couponDiscountAmount;
      totalPrice += piecePrice;
      OrderedProduct orderedProductInfo = new OrderedProduct(orderedProduct.getProductOptionId(),
          orderedProduct.getQuantity(), orderedProduct.getCouponId(), piecePrice, orderId, false);

      // 주문된 상품을 저장
      orderedProductRepository.addOrderedProduct(orderId, orderedProductInfo);
      historyRepository.addHistory(orderedProductInfo.getOrderedProductId(), orderId, orderedAt, OrderStatus.BEFORE_PAYMENT.getStatusDescription());
    }

    // 쿠폰 사용
    useCoupons(memberId, usedIssuedCouponIdList, orderedAt);

    // 적립금 선할인, 적립금 선할인을 하지 않으면 적립금은 구매가 확정될 때 지급됩니다.
    if(usePointsInAdvance) {
      totalPrice -= totalPoints;
    }
    totalPrice -= usedPoints; // 적립금 할인

    // 영수증 생성 => 주문시에 생성해놓고 결제 기능 메소드 안에서 paidAt, paymentMethod 등의 값이 변경됩니다.
    Receipt receipt = new Receipt.Builder()
        .orderId(orderId)
        .totalPrice(totalPrice)
        .points(totalPoints)
        .usedPoints(usedPoints)
        .usePointsInAdvance(usePointsInAdvance)
        .refunded(false)
        .build();

    receiptRepository.saveReceipt(receipt);

    return new OrderResponseDto(orderId, orderedAt);
  }

  // 재고를 1개 감소 시킵니다.
  private void reduceStock(Long productOptionId) {
    productRepository.reduceProductOptionStock(productOptionId);
  }


  // 관리자 전용. 전체 주문 리스트 조회
  public List<OrderListResponseForAdminDto> allOrderList(int page, int count) {
    return orderRepository.findAllOrders(page, count);
  }

  // 관리자 전용. 특정 주문 정보 조회
  public OrderInfoResponseForAdminDto orderInfo(Long orderId) {
    List<OrderedProduct> orderedProductList = orderRepository.findOrderedProductsByOrderId(orderId);
    OrderInfoResponseForAdminDto orderInfo = orderRepository.findOrderInfoByOrderId(orderId);
    orderInfo.setOrderedProductList(orderedProductList);

    return orderInfo;
  }

  // 주문에서 배송지 수령자 정보 변경
  @Transactional
  public Order updateDeliveryAddressInOrder(Long orderId, String receiverName, String receiverPhone, String receiverAddress) {
    Order order = orderRepository.findOneOrderByOrderId(orderId);
    List<History> historyList = historyRepository.findHistoriesByOrderId(orderId);

    List<String> updatableList = new ArrayList<>(Arrays.asList(
        new String[]{OrderStatus.BEFORE_PAYMENT.getStatusDescription(),
            OrderStatus.CONFIRMED_PAYMENT.getStatusDescription(),
            OrderStatus.PREPARING_FOR_DELIVERY.getStatusDescription()}));

    // 배송지를 바꿀 수 있는지 검사해보기
    for (History history : historyList) {
      if(!updatableList.contains(history.getStatus())) {
        throw new DeliveryAddressUpdateException("배송지를 변경할 수 없습니다.");
      }
    }

    orderRepository.updateDeliveryAddressInOrder(orderId, receiverName, receiverPhone, receiverAddress);
    return orderRepository.findOneOrderByOrderId(orderId);
  }

  public List<OrderListResponseForMemberDto> orderListByMemberId(Long memberId, Integer page,
      Integer count, String dateFrom, String dateTo) {
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    String regex = "\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])";
    if(dateFrom == null || !Pattern.matches(regex, dateFrom)) {
      // 입력 값이 잘못 되었다면 1년 전으로 설정
      dateFrom = LocalDateTime.now().minusYears(1).format(dateTimeFormat);
    }

    if(dateTo == null || !Pattern.matches(regex, dateTo)) {
      // 입력 값이 잘못 되었다면 현재로 설정
      dateTo = LocalDateTime.now().format(dateTimeFormat);
    }

    return orderRepository.findAllMemberOrders(
        memberId, page, count, dateFrom, dateTo);
  }


  private Long findOriginalPrice(Long productId) {
    Product product = productRepository.findOneProduct(productId);
    return product.getPrice();
  }


  /**
   * @param originalPrice
   * @param tierId
   * @param couponId
   * @return 등급 할인과 쿠폰이 적용된 최종 상품의 가격
   */
  private Long calculateFinalPrice(Long originalPrice, Integer tierId, Long couponId, Long productId) {

    Long tierPrice = calculateTierPrice(tierId, originalPrice);
    Long promotionPrice = calculatePromotionPrice(productId, tierPrice);
    if(couponId != null) {
      return calculateCouponAppliedPrice(couponId, promotionPrice);
    }

    return promotionPrice;
  }

  private Long calculateTierPrice(Integer tierId, Long originalPrice) {
    int discountRate = tierMap.get(tierId).getDiscountRate();
    return (Long) (long)((double)originalPrice * (1.0 - (double)discountRate / 100.0));
  }

  private Long calculatePromotionPrice(Long productId, Long tierPrice) {
    List<Promotion> promotionList = promotionRepository.findPromotionListByProductId(productId);

    LocalDateTime now = LocalDateTime.now();
    // 가장 할인 비용이 높은 프로모션 찾기
    Long finalDiscountAmount = 0L;
    for(Promotion promotion : promotionList) {
      if(promotion.getExpiredAt().isBefore(now)) continue;

      Long discountAmount;
      if(promotion.getPromotionType() == FIXED_DISCOUNT) { //
        discountAmount = promotion.getDiscountAmount();
      } else {
        discountAmount = (long)((double)tierPrice * ((double)promotion.getDiscountRate() / 100.0));
      }
      if(discountAmount > finalDiscountAmount) finalDiscountAmount = discountAmount;
    }
    return (tierPrice-finalDiscountAmount);
  }

  private Long calculateCouponAppliedPrice(Long couponId, Long promotionPrice) {
    // 쿠폰을 찾아오기
    Coupon coupon = couponRepository.findOneCoupon(couponId);
    Long couponAppliedPrice;
    if(coupon.getCouponType() == FIXED_DISCOUNT) {
      couponAppliedPrice = promotionPrice - coupon.getDiscountAmount();
    } else {
      couponAppliedPrice = (long)((double)promotionPrice * (1.0 - (double)coupon.getDiscountRate() / 100.0));
    }

    return couponAppliedPrice;
  }

  private Integer getTierId(Long memberId) {
    Member member = memberRepository.findMemberById(memberId);

    return member.getTierId();
  }

  /**
   * 해당 상품(productId)에 적용할 수 있는 쿠폰(couponId)인지 검사합니다.
   * 적용 불가능하다면 예외를 발생시킵니다.
   * @param productId
   * @param couponId
   */
  private boolean isApplicableCoupon(Long productId, Long couponId) {
    if(couponId == null) return true;

    Coupon oneCouponProduct = couponRepository.findOneCouponProduct(productId, couponId);
    return oneCouponProduct != null;
  }

  @Transactional
  public OrderedProduct cancelOrder(Long orderedProductId, Long memberId) {
    // TODO: 인가에 대한 부분 구현

    // 삭제 -> 주문의 상태를 보고, "입금 전", "입금확인", "출고처리중"이면 바로 취소 가능하다.
    // 다른 케이스인 경우 취소가 불가하고 환불 신청을 해야한다.

    History history = historyRepository.findOneHistoryByOrderedProductId(orderedProductId);

    List<String> cancelableList = new ArrayList<>(Arrays.asList(
            OrderStatus.BEFORE_PAYMENT.getStatusDescription(),
            OrderStatus.CONFIRMED_PAYMENT.getStatusDescription(),
            OrderStatus.PREPARING_FOR_DELIVERY.getStatusDescription()));

    Long refundPrice = 0L;
    if(!cancelableList.contains(history.getStatus())) {
      throw new OrderCancellationException("주문을 취소할 수 없습니다.", history.getStatus());
    }

    // 주문의 상태 변경
    productRepository.updateIsCancelled(orderedProductId, true);
    historyRepository.updateStatus(orderedProductId, OrderStatus.ORDER_CANCELLATION_COMPLETED.getStatusDescription());

    // issued_coupon의 is_used를 다시 false로 설정
    OrderedProduct orderedProduct = orderedProductRepository.findOneOrderedProductByOrderedProductId(
        orderedProductId);

    IssuedCoupon issuedCoupon = couponRepository.findOneIssuedCoupon(
        orderedProduct.getCouponId(), memberId);

    couponRepository.updateUsedInIssuedCoupon(issuedCoupon.getIssuedCouponId(), false, null);

    // 영수증의 상태를 변경
    Receipt receipt = receiptRepository.findOneReceiptByOrderId(history.getOrderId());
    receiptRepository.updateReceiptIsRefunded(receipt.getReceiptId(), true);
    // TODO: 결제가 이미 진행되었을시에 환불하는 과정


    return orderedProductRepository.findOneOrderedProductByOrderedProductId(orderedProductId);
  }

  // 이력 테이블 조회
  public History lookUpOneHistoryTable(Long orderedProductId) {
    return historyRepository.findOneHistoryByOrderedProductId(orderedProductId);
  }


  //== private methods ==//

  private Long calcaulateUnitPoints(Integer tierId, Long promotionPrice) {
    int accumulationRate = tierMap.get(tierId).getAccumulationRate();
    return (long)((double) promotionPrice * ((double) accumulationRate / 100.0));
  }

  private Long calculateTierDiscountAmount(Integer tierId, Long promotionPrice) {
    int discountRate = tierMap.get(tierId).getDiscountRate();
    return (long)((double) promotionPrice * ((double) discountRate / 100.0));
  }



  // 쿠폰 사용
  private void useCoupons(Long memberId, List<Long> issuedCouponList, LocalDateTime orderedAt) {
    try {
      issuedCouponList
          .forEach(issuedCouponId -> couponRepository.updateUsedInIssuedCoupon(issuedCouponId, true, orderedAt));
    } catch (Exception e) {
      throw new FailToIssueCouponException(DetailedStatus.INTERNAL_SERER_ERROR);
    }
  }
}
