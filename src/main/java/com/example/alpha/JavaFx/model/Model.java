package com.example.alpha.JavaFx.model;

import com.example.alpha.JavaFx.view.viewFactory;
import lombok.Getter;

//@Component
@Getter
public class Model {
    private static Model model;
    private final viewFactory viewFactory;

    public Model(){
        this.viewFactory = new viewFactory();
    }

    public static synchronized Model getInstant(){
        if(model == null){
            model = new Model();
        }
        return model;
    }
}
