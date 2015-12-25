package com.sleek.CDStore.model;

public class TrackBean {
	public int id;
	public String trackName;
	public String CD;
	
	/** get id
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/** set id
	 * @param the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/** get track name
	 * @return the trackName
	 */
	public String getTrackName() {
		return trackName;
	}
	/** set track name
	 * @param the trackName to set
	 */
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
	/** get cd
	 * @return the cD
	 */
	public String getCD() {
		return CD;
	}
	/** set cd
	 * @param the cD to set
	 */
	public void setCD(String cD) {
		CD = cD;
	}
}
