package com.crud.crudapp.util.mapper;

import com.crud.crudapp.entity.Order;
import com.crud.crudapp.request.OrderRequest;
import com.crud.crudapp.response.OrderResponse;

public class OrderMapper {

    public static OrderResponse orderToOrderResponse(Order order){
        return new OrderResponse(order.getId(), order.getAmount(), order.getDate());
    }

    public static Order orderRequestToOrder(OrderRequest request){
        return Order.builder()
                .amount(request.amount())
                .date(request.date())
                .build();
    }
}
