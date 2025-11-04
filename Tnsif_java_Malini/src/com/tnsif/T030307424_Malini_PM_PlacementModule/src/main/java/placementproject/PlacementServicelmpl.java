package placementproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlacementServicelmpl implements PlacementService {

    @Autowired
    private PlacementRespository placementRepository;

    @Override
    public Placement addPlacement(Placement placement) {
        return placementRepository.save(placement);
    }

    @Override
    public Placement updatePlacement(Integer id, Placement placement) {
        Placement existing = placementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Placement not found with id: " + id));

        existing.setName(placement.getName());
        existing.setCollegeId(placement.getCollegeId());
        existing.setDate(placement.getDate());
        existing.setQualification(placement.getQualification());
        existing.setYear(placement.getYear());

        return placementRepository.save(existing);
    }

    @Override
    public void deletePlacement(Integer id) {
        placementRepository.deleteById(id);
    }

    @Override
    public Placement getPlacementById(Integer id) {
        return placementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Placement not found with id: " + id));
    }

    @Override
    public List<Placement> getAllPlacements() {
        return placementRepository.findAll();
    }
}
