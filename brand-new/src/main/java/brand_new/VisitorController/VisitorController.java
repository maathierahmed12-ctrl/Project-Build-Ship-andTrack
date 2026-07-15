package brand_new.VisitorController;

import brand_new.Service.VisitorService;
import brand_new.model.Visitor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/visitors")

public class VisitorController {

     private  VisitorService visitorService;
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping
    public List<Visitor> getAllVisitors(
            @RequestParam(required = false) String purpose) {

        if (purpose != null) {
            return visitorService.getVisitorsByPurpose(purpose);
        }

        return visitorService.getAllVisitors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visitor> getVisitor(@PathVariable Long id) {

        Visitor visitor = visitorService.getVisitorById(id);

        if (visitor == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(visitor);
    }

    @PostMapping
    public ResponseEntity<Visitor> addVisitor(@RequestBody Visitor visitor) {

        Visitor saved = visitorService.addVisitor(visitor);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visitor> updateVisitor(
            @PathVariable Long id,
            @RequestBody Visitor visitor) {

        Visitor updated = visitorService.updateVisitor(id, visitor);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitor(@PathVariable Long id) {

        boolean deleted = visitorService.deleteVisitor(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public Map<String, Long> countVisitors() {
        return Map.of("total", visitorService.countVisitors());
    }


    @GetMapping("/api/health")
    public Map<String, String> health() {

        return Map.of(
                "status", "UP",
                "developer", "Your Name"
        );
    }
}





