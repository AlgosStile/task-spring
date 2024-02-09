package com.example.tasklist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * Основной класс приложения для TaskList.
 */
@SpringBootApplication
public class TaskListApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskListApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("TaskList", r -> r.path("/tasks/")
                        .uri("http://localhost:8081/"))
                .build();
    }
}

