package brand_new.Service;

import brand_new.model.Visitor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitorService {


    private final List<Visitor> visitors = new ArrayList<>();
    private Long nextId = 1L;

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
        visitor.setId(nextId++);
        visitors.add(visitor);
        return visitor;
    }

    public Visitor updateVisitor(Long id, Visitor updatedVisitor) {

        Visitor visitor = getVisitorById(id);

        if (visitor == null) {
            return null;
        }

        visitor.setName(updatedVisitor.getName());
        visitor.setCompany(updatedVisitor.getCompany());
        visitor.setPurpose(updatedVisitor.getPurpose());

        return visitor;
    }

    public boolean deleteVisitor(Long id) {
        return visitors.removeIf(v -> v.getId().equals(id));
    }

    public long countVisitors() {
        return visitors.size();
    }

    public List<Visitor> getVisitorsByPurpose(String purpose) {

        List<Visitor> result = new ArrayList<>();

        for (Visitor visitor : visitors) {
            if (visitor.getPurpose().equalsIgnoreCase(purpose)) {
                result.add(visitor);
            }
        }

        return result;
    }
}
