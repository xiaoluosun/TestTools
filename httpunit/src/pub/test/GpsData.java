package pub.test;

public class GpsData {
	private String latitude;			// 纬度
	private String longitude;			// 经度
	private String speed;				// 速度
	private String direction;			// 方向
	
	public GpsData(){
		latitude = "";
		longitude = "";
		speed = "";
		direction = "";
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}	
	
}
