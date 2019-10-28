package cn.kgc.util;

public class HouseListParam extends PageUtil {
	private String title;
	private Integer typeId;
	private Integer floorage;
	private Integer districtId;
	private Integer streetId;
	private Long minPrice;
	private Long maxPrice;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Long minPrice) {
		this.minPrice = minPrice;
	}

	public Long getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Long maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getFloorage() {
		return floorage;
	}

	public void setFloorage(Integer floorage) {
		this.floorage = floorage;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getStreetId() {
		return streetId;
	}

	public void setStreetId(Integer streetId) {
		this.streetId = streetId;
	}
}
