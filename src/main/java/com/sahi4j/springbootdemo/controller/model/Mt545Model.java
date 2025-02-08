package com.sahi4j.springbootdemo.controller.model;

public record Mt545Model(String name, int price) {
    public Mt545Model{
        if(name.isBlank()) throw new IllegalArgumentException();
    }
}
