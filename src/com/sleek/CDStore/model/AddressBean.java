package com.sleek.CDStore.model;

public class AddressBean {
	protected int id;
	protected String type="shipping"; // billing or shipping 
	protected String street;
	protected String province;
	protected String country;
	protected String city;
	protected String zip;
	protected int accountId;
	/** get id
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/** get type
	 * @return type
	 */
	public String getType(){
		return type;
	}
	/*set type 
	 * @param type
	 * */
	public void setType(String type){
		this.type=type;
	}
	/** get steet
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/** get city
	 * @return city
	 */
	public String getCity() {
		return city;
	}
	/** get province
	 * @return province
	 */
	public String getProvince() {
		return province;
	}
	/** get country
	 * @return country
	 */
	public String getCountry() {
		return country;
	}
	/** get zip
	 * @return zip
	 */
	public String getZip() {
		return zip;
	}
	/** get aacount id
	 * @return accountId
	 */
	public int getAccountId() {
		return accountId;
	}
	/** set id
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/** set street
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/** set city
	 * @param city 
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/** set province
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/** set country
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/** set zip
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	/** set account id
	 * @param accountId the accountId to set
	 */
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
}
