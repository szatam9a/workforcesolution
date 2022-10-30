package dev.workforce.workforce.repository;

import dev.workforce.workforce.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    public List<Position> findByTitleContainingIgnoreCaseAndLocationIgnoreCase(String title, String location);
}
