package com.booleanuk.api.counter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/counter")
public class CounterController {
    private int counter = 0;
    private final HashMap<String, Integer> counters= new HashMap<>();

    @GetMapping
    public int getCounter(){
        return this.counter;
    }

    @GetMapping("/increment")
    public int increment(){
        return ++this.counter;
    }

    @GetMapping("/decrement")
    public int decrement(){
        return --this.counter;
    }

    @GetMapping("/custom/{name}")
    public int getCustomCounter(@PathVariable String name){
        if (counters.getOrDefault(name, -1).equals(-1))
        {
            counters.put(name, 0);
        }
        return counters.get(name);
    }

    @GetMapping("/custom/{name}/increment")
    public int incrementCustomCounter(@PathVariable String name){
        return counters.merge(name, 1, Integer::sum);
    }

    @GetMapping("/custom/{name}/decrement")
    public int decrementCustomCounter(@PathVariable String name){
        return counters.merge(name, -1, Integer::sum);
    }
}
