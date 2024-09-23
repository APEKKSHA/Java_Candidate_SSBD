package earthquakeapi.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import earthquakeapi.model.EarthquakeRecords;
import earthquakeapi.repository.EarthquakeRepository;

/**
 * A service class that handles Parsing text File,Save the parsed data into a
 * database and retrieve records by eventID.
 * 
 * @author Apeksha Patel
 */

@Service
public class EarthquakeService {

	@Autowired
	private EarthquakeRepository repository;

	private List<EarthquakeRecords> allRecords = new ArrayList<EarthquakeRecords>();

	/**
	 * Retrieve records by eventID.
	 * 
	 * @param eventID The unique identifier for the event.
	 * @return The record with the specified ID, or null if not found.
	 */
	public Optional<EarthquakeRecords> getRecordByEventId(String eventID) {
		Optional<EarthquakeRecords> records = repository.findById(eventID);
		return records;
	}

	/**
	 * Parsing Text into CSV format
	 * 
	 * @param inputFilePath,outputFilePath source path for file.
	 * @return void
	 */
	public int convertTsvToCsv(String inputFilePath, String outputFilePath) throws IOException {
		try (CSVParser parser = new CSVParser(new FileReader(inputFilePath), CSVFormat.TDF);

				CSVPrinter printer = new CSVPrinter(new FileWriter(outputFilePath), CSVFormat.DEFAULT)) {

			for (CSVRecord record : parser) {
				printer.printRecord(record);
			}
			importCsvData(outputFilePath);

			return 1;
		} catch (IOException e) {
			// e.printStackTrace();
			return 0;
		}
	}

	/**
	 * store the csv format data into database
	 * 
	 * @param csvFilePath CSV format file for records.
	 * @return void.
	 */
	public void importCsvData(String csvFilePath) throws IOException {

		CSVParser csvParser = new CSVParser(new FileReader(csvFilePath),
				CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

		Iterable<CSVRecord> iterable = csvParser.getRecords();
		for (CSVRecord record : iterable) {
			if (record.size() >= csvParser.getHeaderMap().size()) {
				EarthquakeRecords records = new EarthquakeRecords();
				records.setEventId(record.get("areventID"));
				records.setDatetime(record.get("datetime"));
				records.setLatitude(NumberUtils.toDouble(record.get("latitude"), 0));
				records.setLongitude(NumberUtils.toDouble(record.get("longitude"), 0));
				records.setMagnitude(NumberUtils.toDouble(record.get("magnitude"), 0));
				records.setMagType(record.get("mag_type"));
				records.setDepth(NumberUtils.toDouble(record.get("depth"), 0));
				records.setPhaseCount(NumberUtils.toInt(record.get("phasecount"), 0));
				records.setAzimuthGap(NumberUtils.toDouble(record.get("azimuth_gap"), 0));
				records.setLocation(record.get("location"));
				records.setAgency(record.get("agency"));
				records.setDatetimeFM(record.get("datetimeFM"));
				records.setLatFM(NumberUtils.toDouble(record.get("latFM"), 0));
				records.setLonFM(NumberUtils.toDouble(record.get("lonFM"), 0));
				records.setMagFM(NumberUtils.toDouble(record.get("magFM"), 0));
				records.setMagFM(NumberUtils.toDouble(record.get("magTypeFM"), 0));
				records.setDepthFM(NumberUtils.toDouble(record.get("depthFM"), 0));
				records.setPhaseCountFM(NumberUtils.toInt(record.get("phasecountFM"), 0));
				records.setAzGapFM(NumberUtils.toDouble(record.get("AzGapFM"), 0));
				records.setScalarMoment(NumberUtils.toDouble(record.get("scalarMoment"), 0));
				records.setMrr(NumberUtils.toDouble(record.get("Mrr"), 0));
				records.setMtt(NumberUtils.toDouble(record.get("Mtt"), 0));
				records.setMpp(NumberUtils.toDouble(record.get("Mpp"), 0));
				records.setMrt(NumberUtils.toDouble(record.get("Mrt"), 0));
				records.setMrp(NumberUtils.toDouble(record.get("Mrp"), 0));
				records.setMtp(NumberUtils.toDouble(record.get("Mtp"), 0));
				records.setVarianceReduction(NumberUtils.toDouble(record.get("varianceReduction"), 0));
				records.setDoubleCouple(NumberUtils.toDouble(record.get("doubleCouple"), 0));
				records.setClvd(NumberUtils.toDouble(record.get("clvd"), 0));
				records.setStrikeNP1(NumberUtils.toDouble(record.get("strikeNP1"), 0));
				records.setDipNP1(NumberUtils.toDouble(record.get("dipNP1"), 0));
				records.setRakeNP1(NumberUtils.toDouble(record.get("rakeNP1"), 0));
				records.setStrikeNP1(NumberUtils.toDouble(record.get("strikeNP2"), 0));
				records.setDipNP2(NumberUtils.toDouble(record.get("dipNP2"), 0));
				records.setRakeNP2(NumberUtils.toDouble(record.get("rakeNP2"), 0));
				records.setAzgap_FM(NumberUtils.toDouble(record.get("azgapFM"), 0));
				records.setMisfit(NumberUtils.toDouble(record.get("misfit"), 0));

				allRecords.add(records);
			}
		}
		repository.saveAll(allRecords);
	}
}
