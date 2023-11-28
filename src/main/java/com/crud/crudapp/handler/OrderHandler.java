package com.crud.crudapp.handler;

import com.crud.crudapp.request.OrderRequest;
import com.crud.crudapp.response.OrderResponse;
import com.crud.crudapp.service.OrderService;
import com.crud.crudapp.util.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OrderHandler {

    private final OrderService orderService;

    public Mono<ServerResponse> getAllOrders(ServerRequest request){
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(
                        orderService.getAllOrders()
                                .map(o -> OrderMapper.orderToOrderResponse(o))
                        , OrderResponse.class);
    }

    public Mono<ServerResponse> getOrderById(ServerRequest request){
        Long id = Long.parseLong(request.pathVariable("id"));//path variable
//        Long id = Long.parseLong(request.queryParam("id").get());//query param
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderService.getOrderById(id)
                        .map(o -> OrderMapper.orderToOrderResponse(o)),
                        OrderResponse.class
                        );
    }

    public Mono<ServerResponse> createOrder(ServerRequest request){
        Mono<OrderRequest> response = request.bodyToMono(OrderRequest.class);//for object
//        Flux<OrderRequest> response = request.bodyToFlux(OrderRequest.class);//for list of objects

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        response
                                .map(OrderMapper::orderRequestToOrder)
                                .flatMap(orderService::saveOrder)
                                .map(OrderMapper::orderToOrderResponse),
                        OrderResponse.class

                );
    }

    public Mono<ServerResponse> updateOrder(ServerRequest request){
        Mono<OrderRequest> response = request.bodyToMono(OrderRequest.class);//can update multiple using Flux
        Long id = Long.parseLong(request.pathVariable("id"));

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        orderService.getOrderById(id)//retrieve order
                                .flatMap( o -> response.map(req -> orderService.updateOrder(o,req)))//get Entity, update values
                                .flatMap(o-> orderService.saveOrder(o))//persist changes
                                .map(OrderMapper::orderToOrderResponse),//map entity to response dto
                        OrderResponse.class
                );

    }

    public Mono<ServerResponse> deleteOrder(ServerRequest request){
        Long id = Long.parseLong(request.pathVariable("id"));

        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(
                        orderService.deleteOrder(id)
                                .map(o -> "Order has been deleted"),
                        String.class
                );

    }




}
