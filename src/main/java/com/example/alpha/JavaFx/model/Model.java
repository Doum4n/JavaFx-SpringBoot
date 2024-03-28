package com.example.alpha.JavaFx.model;

import com.example.alpha.JavaFx.view.viewFactory;
import com.example.alpha.JavaFx.view.viewQuanLy;
import lombok.Getter;

//@Component
@Getter
public class Model {
    private static Model model;
    private final viewFactory viewFactory;
    private final viewQuanLy viewQuanLy;

    public Model(){
        this.viewQuanLy = new viewQuanLy();
        this.viewFactory = new viewFactory();
    }

    public static synchronized Model getInstant(){
        if(model == null){
            model = new Model();
        }
        return model;
    }
}
