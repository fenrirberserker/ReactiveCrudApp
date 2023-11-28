package com.crud.crudapp.service;

import com.crud.crudapp.entity.Order;
import com.crud.crudapp.repository.OrderRepository;
import com.crud.crudapp.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Flux<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Mono<Order> getOrderById(Long id){
        return orderRepository.findById(id);
    }

    public Mono<Order> saveOrder(Order order){
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order, OrderRequest orderRequest){
        order.setAmount(orderRequest.amount());
        order.setDate(orderRequest.date());
        return order;
    }

    public Mono<Void> deleteOrder(Long id){
        return orderRepository.deleteById(id);
    }



}
