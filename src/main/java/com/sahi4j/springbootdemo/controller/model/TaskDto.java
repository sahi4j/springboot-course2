package com.sahi4j.springbootdemo.controller.model;

public record TaskDto(Long id, String text, String day, boolean reminder) {
}
