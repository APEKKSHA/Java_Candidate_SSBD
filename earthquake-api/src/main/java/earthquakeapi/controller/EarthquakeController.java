package earthquakeapi.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import earthquakeapi.model.ApiResponse;
import earthquakeapi.model.EarthquakeRecords;
import earthquakeapi.service.EarthquakeService;

/**
 * Controller Class for retrieving records
 * 
 * @author Apeksha Patel
 */
@RestController
public class EarthquakeController {

	@Autowired
	private EarthquakeService service;

	/**
	 * Retrieve an event by its ID.
	 * 
	 * @param eventID the ID of the record
	 * @return ResponseEntity with ApiResponse for found record or a not found
	 *         status
	 */
	@GetMapping("/{eventID}")
	public ResponseEntity<ApiResponse> getEarthquakeRecord(@PathVariable("eventID") String eventID) {
		ApiResponse apiResponse = new ApiResponse();
		if (eventID.isBlank() || eventID.isEmpty()) {
			apiResponse.setStatus("Error!");
			apiResponse.setMessage("Invalid event ID !!! ");
			apiResponse.setData(null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		}
		Optional<EarthquakeRecords> record = service.getRecordByEventId(eventID);
		if (record.isPresent()) {
			apiResponse.setStatus("Success");
			apiResponse.setMessage("Record found successfully");
			apiResponse.setData(record);
			return ResponseEntity.ok(apiResponse);
		} else {
			apiResponse.setStatus("Error!");
			apiResponse.setMessage("Record not found for event ID : " + eventID);
			apiResponse.setData(null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
		}
	}

	/**
	 * Parse Text Data into CSV format
	 * 
	 * @param Map<String, String> for inputFilePath and outputFilePath.
	 * @return ResponseEntity with ApiResponse for parsed data or not.
	 */
	@PostMapping("/parseData")
	public ResponseEntity<ApiResponse> parseDataIntoCsv(@RequestBody Map<String, String> filePath) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			int value = service.convertTsvToCsv(filePath.get("inputFilePath"), filePath.get("outputFilePath"));
			if (value == 1) {
				apiResponse.setStatus("Success");
				apiResponse.setMessage("Data Parsed Successfull");
				apiResponse.setData(null);
				return ResponseEntity.ok(apiResponse);
			} else {
				apiResponse.setStatus("Error!");
				apiResponse.setMessage("Couldn't parse Data");
				apiResponse.setData(null);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
			}
		} catch (IOException e) {
			apiResponse.setStatus("Error!");
			apiResponse.setMessage("File not found");
			apiResponse.setData(null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
			// e.printStackTrace();
		}

	}
}
