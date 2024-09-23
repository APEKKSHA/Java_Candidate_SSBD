package earthquakeapi.model;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO class for encapsulates the API response details.
 * 
 * @author Apeksha Patel
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
	private String status;
	private String message;
	private Optional<EarthquakeRecords> data;

}
