package com.example.alpha;

import com.example.alpha.JavaFx.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.*;

import java.io.IOException;

@Configuration
@SpringBootApplication
@EnableTransactionManagement
public class Main extends Application {
    private ConfigurableApplicationContext applicationContext;
    @Override
    public void start(Stage stage) {
        Model.getInstant().getViewFactory().showLoginWindow();
    }

    @Override
    public void init(){
        this.applicationContext = SpringApplication.run(Main.class);
    }

    @Override
    public void stop(){
        applicationContext.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}