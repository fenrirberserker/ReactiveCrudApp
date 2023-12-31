package com.crud.crudapp.router;

import com.crud.crudapp.handler.OrderHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class OrderRouter {

    @Autowired
    OrderHandler orderHandler;

    @Bean
    public RouterFunction<ServerResponse> orderRoutes(){
        return RouterFunctions
                .route(RequestPredicates.GET("/api/order/getAll"),
                        orderHandler::getAllOrders)
                .andRoute(RequestPredicates.GET("/api/order/{id}"),//for path variable
//                .andRoute(RequestPredicates.GET("/api/order"),//for query param
                        orderHandler::getOrderById)
                .andRoute(RequestPredicates.POST("/api/order/create")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        orderHandler::createOrder)
                .andRoute(RequestPredicates.PUT("/api/order/update/{id}")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        orderHandler::updateOrder)
                .andRoute(RequestPredicates.DELETE("/api/order/delete/{id}"),
                        orderHandler::deleteOrder);
    }
}
