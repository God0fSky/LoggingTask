package com.example.loggingtask;

import com.example.loggingtask.models.Order;
import com.example.loggingtask.services.CoffeeOrderBoard;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;

@SpringBootApplication
public class LoggingTaskApplication {

    @Autowired
    private CoffeeOrderBoard coffeeOrderBoard;

    public static void main(String[] args) {
        SpringApplication.run(LoggingTaskApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        Order order1 = new Order("order1");
        Order order2 = new Order("order2");
        Order order3 = new Order("order3");

        coffeeOrderBoard.add(order1, "User1", LocalDate.now());
        coffeeOrderBoard.add(order2, "User1", LocalDate.now());
        coffeeOrderBoard.add(order3, "User1", LocalDate.now());
        coffeeOrderBoard.add(order1, "Admin", LocalDate.now());
        coffeeOrderBoard.add(order2, "Admin", LocalDate.now());
        //coffeeOrderBoard.add(null, "BadUser", LocalDate.now());

        coffeeOrderBoard.draw("User1", LocalDate.now());

        coffeeOrderBoard.deliverLastOrder();
        coffeeOrderBoard.draw("User1", LocalDate.now());

        coffeeOrderBoard.deliverKeyOrder(4);
        coffeeOrderBoard.draw("Admin", LocalDate.now());

        coffeeOrderBoard.add(null, "BadUser", LocalDate.now());

        coffeeOrderBoard.add(order2, "NewUser", LocalDate.now());
        coffeeOrderBoard.add(order3, "NewUser", LocalDate.now());
        coffeeOrderBoard.draw("Admin", LocalDate.now());
    }

}
