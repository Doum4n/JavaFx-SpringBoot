package com.example.alpha;

import com.example.alpha.JavaFx.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Main extends Application {
    private ConfigurableApplicationContext applicationContext;
    @Override
    public void start(Stage stage) {
        Model.getInstant().getViewFactory().showLoginWindow();
    }

    @Override
    public void init() {
        applicationContext = SpringApplication.run(Main.class);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void stop() {
        applicationContext.stop();
    }
}