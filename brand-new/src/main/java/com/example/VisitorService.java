package com.example;

import com.example.visitorlog.model.Visitor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitorService {

    private final List<Visitor> visitors = new ArrayList<>();

    private Long idCounter = 1L;


    public List<Visitor> getAllVisitors() {
        return visitors;
    }


    public Visitor getVisitorById(Long id) {

        return visitors.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    public Visitor addVisitor(Visitor visitor) {

        visitor.setId(idCounter++);
        visitors.add(visitor);

        return visitor;
    }


    public boolean deleteVisitor(Long id) {

        return visitors.removeIf(v -> v.getId().equals(id));
    }


    public int countVisitors() {

        return visitors.size();
    }
    public Visitor updateVisitor(Long id, Visitor updatedVisitor){

        Visitor visitor = getVisitorById(id);

        if(visitor == null){
            return null;
        }

        visitor.setName(updatedVisitor.getName());
        visitor.setCompany(updatedVisitor.getCompany());
        visitor.setPurpose(updatedVisitor.getPurpose());

        return visitor;
    }


    public List<Visitor> filterByPurpose(String purpose){

        return visitors.stream()
                .filter(v -> v.getPurpose().equalsIgnoreCase(purpose))
                .toList();
    }
}