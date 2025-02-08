package com.sahi4j.springbootdemo.controller;


import com.sahi4j.springbootdemo.controller.datamodel.TaskModel;
import com.sahi4j.springbootdemo.controller.model.TaskDto;
import com.sahi4j.springbootdemo.data.TaskDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin//allow all origin
@RestController
@RequestMapping("/tasks") //RestController + RequestMapping(val)

@Validated // Enable validation for method parameters
@AllArgsConstructor
@Slf4j
public class TaskController {

    private TaskDao taskDao;//injected by constructor via allargsconstructor
    @GetMapping()
    public ResponseEntity<List<TaskModel>> getAllTasks(){
        var res = taskDao.findAll();
        return ResponseEntity.ok(res);
    }
    @GetMapping("/{id}")//declare path and path variable named {name}
    public ResponseEntity<TaskDto> getTask(@PathVariable("id") Long id){
        var model = taskDao.findById(id);
        if(model.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        TaskModel t = model.get();
        var dto = new TaskDto(null, t.getText(),t.getDay(),t.isHasReminder());
        return ResponseEntity.ok(dto);// never NEW for ResponseEntity, .ok .accepted .badResquest .status ...
    }

    @PostMapping()
    public ResponseEntity<TaskDto> postTask(@RequestBody TaskDto taskDtoIn){
log.info(taskDtoIn.toString());
        TaskModel model = TaskModel.builder()
                .withDay(taskDtoIn.day())
                .withText(taskDtoIn.text())
                .withHasReminder(taskDtoIn.reminder())
                .build();
        TaskModel t = taskDao.save(model);
        TaskDto d = new TaskDto(null, t.getText(),t.getDay(),t.isHasReminder());
        return ResponseEntity.ok(d);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id){
        if(taskDao.existsById(id)){
            taskDao.deleteById(id);
            return ResponseEntity.noContent().build();// Return a 204 No Content response
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
