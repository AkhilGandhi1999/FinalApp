package com.example.finalapp;

import java.util.Comparator;

public class Model {

    private String title,description,start_e,type;
    private int img;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


    public String getStart_e() {
        return start_e;
    }

    public void setStart_e(String start_e) {
        this.start_e = start_e;
    }
    public static final Comparator<Model> MORNING = new Comparator<Model>() {
        @Override
        public int compare(Model model, Model t1) {
            return model.getTitle().compareTo(t1.getTitle());
        }
    };
}
