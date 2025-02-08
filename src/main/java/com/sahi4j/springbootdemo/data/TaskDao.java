package com.sahi4j.springbootdemo.data;

import com.sahi4j.springbootdemo.controller.datamodel.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TaskDao extends JpaRepository<TaskModel, Long> {//interface & extends
    //JpaRepository = CrudRepository + pagination and sorting
}
