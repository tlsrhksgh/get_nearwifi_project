package wifi;

public class WifiDistance {
	public double getWIfiDistance(double lat1, double lnt1, double lat2, double lnt2) {
		double distance;
	    double radius = 6371; // 지구 반지름(km)
	    double toRadian = Math.PI / 180;

	    double deltaLatitude = Math.abs(lat1 - lat2) * toRadian;
	    double deltaLongitude = Math.abs(lnt1 - lnt2) * toRadian;

	    double sinDeltaLat = Math.sin(deltaLatitude / 2);
	    double sinDeltaLng = Math.sin(deltaLongitude / 2);
	    double squareRoot = Math.sqrt(
	        sinDeltaLat * sinDeltaLat +
	        Math.cos(lat1 * toRadian) * Math.cos(lat2 * toRadian) * sinDeltaLng * sinDeltaLng);

	    distance = 2 * radius * Math.asin(squareRoot);
	    
	    distance = Math.round(distance*10000)/10000.0;

	    return distance;
	}
}
