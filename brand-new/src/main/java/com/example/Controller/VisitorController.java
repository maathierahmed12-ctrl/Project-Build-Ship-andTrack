package com.example.Controller;
import com.example.visitorlog.model.Visitor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class VisitorController {


    private  List<Visitor> visitors = new ArrayList<>();

    private long idCounter = 1;


    @GetMapping("/visitors")
    public List<Visitor> getVisitors(
            @RequestParam(required = false) String purpose
    ){

        if(purpose != null){

            return visitors.stream()
                    .filter(v -> v.getPurpose()
                            .equalsIgnoreCase(purpose))
                    .toList();
        }

        return visitors;
    }


    @GetMapping("/visitors/{id}")
    public Visitor getVisitor(
            @PathVariable Long id
    ){

        return visitors.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Visitor not found"
                        )
                );
    }


    @PostMapping("/visitors")
    @ResponseStatus(HttpStatus.CREATED)
    public Visitor createVisitor(
            @RequestBody Visitor visitor
    ){

        visitor.setId(idCounter++);

        visitors.add(visitor);

        return visitor;
    }


    @DeleteMapping("/visitors/{id}")
    public void deleteVisitor(
            @PathVariable Long id
    ){

        boolean removed = visitors.removeIf(
                v -> v.getId().equals(id)
        );


        if(!removed){

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Visitor not found"
            );
        }
    }




    @GetMapping("/visitors/count")
    public Map<String, Integer> count(){

        return Map.of(
                "total",
                visitors.size()
        );
    }




    @GetMapping("/health")
    public Map<String,String> health(){

        return Map.of(
                "status",
                "UP",
                "developer",
                "Your Name"
        );
    }




    @PutMapping("/visitors/{id}")
    public Visitor update(
            @PathVariable Long id,
            @RequestBody Visitor updatedVisitor
    ){

        Visitor visitor = visitors.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Visitor not found"
                        )
                );


        visitor.setName(updatedVisitor.getName());
        visitor.setCompany(updatedVisitor.getCompany());
        visitor.setPurpose(updatedVisitor.getPurpose());


        return visitor;
    }

}