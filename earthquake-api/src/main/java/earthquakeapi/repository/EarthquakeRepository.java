package earthquakeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import earthquakeapi.model.EarthquakeRecords;
/**
 * Repository interface for accessing data.
 */
public interface EarthquakeRepository extends JpaRepository<EarthquakeRecords, String> {
}
