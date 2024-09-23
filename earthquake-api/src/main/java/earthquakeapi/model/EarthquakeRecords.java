package earthquakeapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class with required fields
 * 
 * @author Apeksha Patel
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "earthquake_records")
public class EarthquakeRecords {

	@Id
	@Column(name = "event_id")
	private String eventId;
	@Column(name = "datetime")
	private String datetime;
	@Column(name = "latitude")
	private Double latitude;
	@Column(name = "longitude")
	private Double longitude;
	@Column(name = "magnitude")
	private Double magnitude;
	@Column(name = "mag_type")
	private String magType;
	@Column(name = "depth")
	private Double depth;
	@Column(name = "phasecount")
	private Integer phaseCount;
	@Column(name = "azimuth_gap")
	private Double azimuthGap;
	@Column(name = "location")
	private String location;
	@Column(name = "agency")
	private String agency = "";
	@Column(name = "datetimeFM")
	private String datetimeFM = "";
	@Column(name = "latFM")
	private Double latFM = 0.0;
	@Column(name = "lonFM")
	private Double lonFM = 0.0;
	@Column(name = "magFM")
	private Double magFM = 0.0;
	@Column(name = "magTypeFM")
	private String magTypeFM = "";
	@Column(name = "depthFM")
	private Double depthFM = 0.0;
	@Column(name = "phasecountFM")
	private Integer phaseCountFM = 0;
	@Column(name = "azGapFM")
	private Double azGapFM = 0.0;
	@Column(name = "scalarMoment")
	private Double scalarMoment = 0.0;
	@Column(name = "Mrr")
	private Double mrr = 0.0;
	@Column(name = "Mtt")
	private Double mtt = 0.0;
	@Column(name = "Mpp")
	private Double mpp = 0.0;
	@Column(name = "Mrt")
	private Double mrt = 0.0;
	@Column(name = "Mrp")
	private Double mrp = 0.0;
	@Column(name = "Mtp")
	private Double mtp = 0.0;
	@Column(name = "varianceReduction")
	private Double varianceReduction = 0.0;
	@Column(name = "doubleCouple")
	private Double doubleCouple = 0.0;
	@Column(name = "clvd")
	private Double clvd = 0.0;
	@Column(name = "strikeNP1")
	private Double strikeNP1 = 0.0;
	@Column(name = "dipNP1")
	private Double dipNP1 = 0.0;
	@Column(name = "rakeNP1")
	private Double rakeNP1 = 0.0;
	@Column(name = "strikeNP2")
	private Double strikeNP2 = 0.0;
	@Column(name = "dipNP2")
	private Double dipNP2 = 0.0;
	@Column(name = "rakeNP2")
	private Double rakeNP2 = 0.0;
	@Column(name = "azgap_FM")
	private Double azgap_FM = 0.0;
	@Column(name = "misfit")
	private Double misfit = 0.0;
}
