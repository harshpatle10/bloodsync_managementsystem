package in.bloodsync.pojo;

import java.util.Date;

public class BloodRequestPojo {
	private int requestId;
	private String hospitalName;
	private String bloodType;
	private int requestedUnits;
	private String urgency;
	private Date requestDate;
	private String status;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public int getRequestedUnits() {
		return requestedUnits;
	}

	public void setRequestedUnits(int requestedUnits) {
		this.requestedUnits = requestedUnits;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
