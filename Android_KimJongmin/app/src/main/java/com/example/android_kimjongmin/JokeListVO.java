package com.example.android_kimjongmin;

import java.util.ArrayList;

public class JokeListVO {
    private String type;
    private ArrayList<Joke> value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Joke> getValue() {
        return value;
    }

    public void setValue(ArrayList<Joke> value) {
        this.value = value;
    }

    public class Joke {
        private String id;
        private String joke;
        private ArrayList<String> categories;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getJoke() {
            return joke;
        }

        public void setJoke(String joke) {
            this.joke = joke;
        }

        public ArrayList<String> getCategories() {
            return categories;
        }

        public void setCategories(ArrayList<String> categories) {
            this.categories = categories;
        }
    }

    public class Categories
    {
        private String categories;

        public String getCategories() {
            return categories;
        }

        public void setCategories(String categories) {
            this.categories = categories;
        }
    }
}