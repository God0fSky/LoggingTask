package com.example.loggingtask.services;

import com.example.loggingtask.models.Order;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class CoffeeOrderBoard {
    
    private final Map<Integer, Order> listOrder = new LinkedHashMap<>();
    private Integer lastKeyValue = 0;


    public void add(Order order, String userName, LocalDate localDate) {
        try {
            if (order == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            MDC.put("clientName", userName);
            MDC.put("date", localDate.toString());
            log.error("NullPointerException, class: CoffeeOrderBoard, method: add(), date: {}", LocalDate.now(), e);
        }
        listOrder.put(++lastKeyValue, order);
        order.setNumber(lastKeyValue);
    }

    public Order deliverLastOrder() {
        return listOrder.remove(lastKeyValue);
    }

    public Order deliverKeyOrder(Integer integer) {
        return listOrder.remove(integer);
    }

    public void draw(String userName, LocalDate localDate) {
        Set<Integer> intSet = listOrder.keySet();
        MDC.put("clientName", userName);
        MDC.put("date", localDate.toString());
        log.info("Num  |  Name");
        for (Integer integer : intSet) {
            log.info(integer + "  |  " + listOrder.get(integer).getNameOfCustomer());
        }
    }

}
