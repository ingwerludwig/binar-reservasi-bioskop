package org.BinarAcademy.Challenge_4.controller;

import org.BinarAcademy.Challenge_4.model.order.Order;
import org.BinarAcademy.Challenge_4.model.seats.Seat;
import org.BinarAcademy.Challenge_4.service.OrderService.OrderService;
import org.BinarAcademy.Challenge_4.service.SeatService.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping()
    public List<Order> getAllOrder(){
        return orderService.getAllOrder();
    }

    @PostMapping
    public void addNewOrder(@RequestBody Order newOrder) throws IOException {
        orderService.addNewOrder(newOrder);
    }

    @DeleteMapping(path = "{orderId}")
    public void deleteOrder(
            @PathVariable("orderId") Integer orderId){
        orderService.deleteOrder(orderId);
    }

    @PutMapping(path = "{orderId}/lunas")
    public void updateOrderToLunas(
            @PathVariable("orderId") Integer orderId
    ){
        orderService.updateOrderToLunas(orderId);
    }
}
