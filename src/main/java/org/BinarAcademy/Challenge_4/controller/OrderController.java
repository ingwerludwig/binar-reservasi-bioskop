package org.BinarAcademy.Challenge_4.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get All Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @GetMapping()
    public List<Order> getAllOrder(){
        return orderService.getAllOrder();
    }

    @Operation(summary = "Add new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PostMapping
    public void addNewOrder(@RequestBody Order newOrder) throws IOException, FirebaseMessagingException {
        orderService.addNewOrder(newOrder);
    }
//test
@Operation(summary = "Delete Order By Id")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "success", content = {
                @Content(mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "400", description = "Request Error Message"),
        @ApiResponse(responseCode = "500", description = "Server Error Message")
})
    @DeleteMapping(path = "{orderId}")
    public void deleteOrder(
            @PathVariable("orderId") Integer orderId){
        orderService.deleteOrder(orderId);
    }

    @Operation(summary = "Update order to lunas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PutMapping(path = "{orderId}/lunas")
    public void updateOrderToLunas(
            @PathVariable("orderId") Integer orderId
    ){
        orderService.updateOrderToLunas(orderId);
    }
}
