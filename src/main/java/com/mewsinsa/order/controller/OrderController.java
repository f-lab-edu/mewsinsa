package com.mewsinsa.order.controller;


import com.mewsinsa.auth.jwt.JwtProvider;
import com.mewsinsa.auth.jwt.interceptor.Auth;
import com.mewsinsa.global.response.DetailedStatus;
import com.mewsinsa.global.response.SuccessResult;
import com.mewsinsa.member.service.MemberService;
import com.mewsinsa.order.controller.dto.OrderListResponseForAdminDto;
import com.mewsinsa.order.controller.dto.OrderListResponseForMemberDto;
import com.mewsinsa.order.controller.dto.OrderRequestDto;
import com.mewsinsa.order.controller.dto.OrderResponseDto;
import com.mewsinsa.order.controller.dto.form.OrderFormRequestDto;
import com.mewsinsa.order.controller.dto.form.OrderFormResponseDto;
import com.mewsinsa.order.domain.History;
import com.mewsinsa.order.exception.NonExsistentOrderException;
import com.mewsinsa.order.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/orders")
@RestController
public class OrderController {
  private final MemberService memberService;
  private final OrderService orderService;

  public OrderController(MemberService memberService, OrderService orderService) {
    this.memberService = memberService;
    this.orderService = orderService;
  }

  @Auth
  @PostMapping("/form")
  public ResponseEntity<SuccessResult> orderForm(
      @RequestHeader(value= JwtProvider.ACCESS_HEADER_STRING, required=false) String accessToken,
      @RequestBody OrderFormRequestDto orderFormRequestDto) {
    OrderFormResponseDto orderFormResponseDto = orderService.orderForm(accessToken,
        orderFormRequestDto);

    SuccessResult result = new SuccessResult.Builder(DetailedStatus.CREATED)
        .data(orderFormResponseDto)
        .build();

    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  @Auth
  @PostMapping
  public ResponseEntity<SuccessResult> makeOrder(
      @RequestHeader(value= JwtProvider.ACCESS_HEADER_STRING, required=false) String accessToken,
      @Valid @RequestBody OrderRequestDto orderRequestDto) {
    Long memberId = memberService.getMemberIdByAccessToken(accessToken);

    OrderResponseDto orderResponseDto = orderService.makeOrder(orderRequestDto, memberId);

    SuccessResult result = new SuccessResult.Builder(DetailedStatus.CREATED)
        .message("주문에 성공하였습니다.")
        .data(orderResponseDto)
        .build();

    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }


  @Auth
  @GetMapping
  public ResponseEntity<SuccessResult> allOrderList(@Positive @PathVariable(value = "page", required = false) Integer page,
      @Positive @PathVariable(value = "count", required = false) Integer count) {

    if(page == null) page = 1;
    if(count == null) count = 10;

    List<OrderListResponseForAdminDto> orders = orderService.allOrderList(page, count);

    SuccessResult result = new SuccessResult.Builder(DetailedStatus.OK)
        .data(orders)
        .build();

    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Auth
  @GetMapping("/members/{memberId}")
  public ResponseEntity<SuccessResult> memberOrderList(
      @PathVariable("memberId") Long memberId,
      @Positive @PathVariable(value = "page", required = false) Integer page,
      @Positive @PathVariable(value = "count", required = false) Integer count,
      @PathVariable(value = "dt_from_input", required = false) String dateFrom,
      @PathVariable(value = "dt_to_input", required = false) String dateTo) {
    if(page == null) page = 1;
    if(count == null) count = 10;

    List<OrderListResponseForMemberDto> orders = orderService.orderListByMemberId(memberId, page, count, dateFrom, dateTo);

    SuccessResult result = new SuccessResult.Builder(DetailedStatus.OK)
        .data(orders)
        .build();

    return new ResponseEntity<>(result, HttpStatus.OK);
  }


  @Auth
  @GetMapping("/{orderId}")
  public ResponseEntity<SuccessResult> cancelOrder(@PathVariable("orderedProductId") Long orderId) {
//    orderService.cancelOrder(orderedProductId);

    SuccessResult result = new SuccessResult.Builder(DetailedStatus.OK)
        .message("주문이 정상적으로 취소 되었습니다.")
//        .data()
        .build();

    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  // TODO: 확인 필요
  @Auth
  @GetMapping("/ordered-products/{orderedProductId}/history")
  public ResponseEntity<SuccessResult> lookUpOneHistoryTable(@PathVariable("orderedProductId") Long orderedProductId) {
    History history = orderService.lookUpOneHistoryTable(orderedProductId);

    if(history == null) {
      throw new NonExsistentOrderException("주문이 존재하지 않습니다. orderedProductId: " + orderedProductId);
    }
    SuccessResult result = new SuccessResult.Builder(DetailedStatus.OK)
        .message("주문이 정상적으로 취소 되었습니다.")
        .data(history)
        .build();

    return new ResponseEntity<>(result, HttpStatus.OK);
  }



}
