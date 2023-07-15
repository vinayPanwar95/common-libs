package com.learning.common.useractions.create;

import com.learning.common.entity.BaseEntity;
import com.learning.common.model.OrderStatusCode;
import com.learning.common.useractions.ActionRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.EnumSet;
import java.util.List;

import static com.learning.common.model.OrderStatusCode.*;

@Slf4j
public abstract class CreateNewService<Request extends ActionRequest,Order extends BaseEntity> {

    public static final EnumSet<OrderStatusCode> PROHIBITED_STATUS_CODES_FOR_NEW_ORDER_CREATION = EnumSet.of(DRAFT, REQUESTED, REQUEST_FOR_CLOSURE,ACTIVATED);

    public Order createNew(Request newOrderRequest){
        log.info("Attempting to create new Order");
        validateRequest(newOrderRequest);
        // find relevant Contracts based on type of contract
        List<Order> orders = getContracts(newOrderRequest);
        validateNewOrderCreationAllowed(orders);

        Order newOrder = newOrderRequest.submitForApproval() ? createNewRequestedOrder(newOrderRequest) : createNewDraftOrder(newOrderRequest);

        newOrder = saveOrderVersion(newOrder);
        log.info("new Order {} created successfully." , newOrder.getDetailsAsString());

        return newOrder;
    }




    private Order createNewDraftOrder(Request newOrderRequest) {
        Order order = createOrder(newOrderRequest);
        order.setStatus(DRAFT);
        return order ;
    }




    private Order createNewRequestedOrder(Request newOrderRequest) {
        Order order = createOrder(newOrderRequest);
        order.setStatus(REQUESTED);
        return order ;
    }

    private void validateNewOrderCreationAllowed(List<Order> orders) {
        orders.stream().filter(order->PROHIBITED_STATUS_CODES_FOR_NEW_ORDER_CREATION.contains(order.getStatus()))
                .findFirst()
                .ifPresent(prohibitedOrderVersion ->{
                    log.error("Failed to create new order. One of the exiting orders version {} has one of the prohibited status {} ",
                            prohibitedOrderVersion.getDetailsAsString(), joinWithOrDelimiter(PROHIBITED_STATUS_CODES_FOR_NEW_ORDER_CREATION) );
                    // TODO: write and throw validationException from  here
//                    throw new Exception();
                });
    }

    protected abstract List<Order> getContracts(Request newOrderRequest);

    protected abstract void validateRequest(Request newOrderRequest) ;

    protected abstract Order createOrder(Request newOrderRequest);
    protected abstract Order saveOrderVersion(Order newOrder);
}
