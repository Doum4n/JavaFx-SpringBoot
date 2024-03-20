package com.example.alpha.JavaFx.model;

import com.example.alpha.JavaFx.view.viewFactory;

//@Component
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

    public viewFactory getViewFactory(){
        return viewFactory;
    }
}
