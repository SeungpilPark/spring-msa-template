package com.msa.template.order.application.controller;


import com.msa.template.core.web.api.ApiResponse;
import com.msa.template.core.web.api.ApiResponseGenerator;
import com.msa.template.order.application.request.OrderSearchRequestDto;
import com.msa.template.order.application.response.OrderResponseDto;
import com.msa.template.order.application.service.OrderApplicationService;
import com.msa.template.order.domain.command.OrderCreateCommand;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderApplicationService orderApplicationService;


	@GetMapping("search")
	@Tag(name = "주문")
	public ApiResponse<Page<OrderResponseDto>> search(
		final OrderSearchRequestDto request,
		final @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		return ApiResponseGenerator.success(orderApplicationService.search(request, pageable));
	}

	@Tag(name = "주문")
	@PostMapping("create")
	public ApiResponse<OrderResponseDto> create(@RequestBody OrderCreateCommand command) {
		return ApiResponseGenerator.success(orderApplicationService.create(command));
	}

	@Tag(name = "주문")
	@PutMapping("update/{id}")
	public ApiResponse<OrderResponseDto> update(@PathVariable Long id, @RequestBody OrderCreateCommand command) {
		return ApiResponseGenerator.success(orderApplicationService.update(id, command));
	}

	@Tag(name = "주문")
	@DeleteMapping("delete/{id}")
	public ApiResponse<Void> delete(@PathVariable Long id) {
		orderApplicationService.delete(id);
		return ApiResponseGenerator.success();
	}
}
