/**
 * 
 */
 
const latInput = document.getElementById("latitude");
const lonInput = document.getElementById("longitude");
	
function geoFindMe() {
	navigator.geolocation.getCurrentPosition(getGeoLocation, getGeoErr);
}
		
function getGeoLocation(position) {
	let lat = position.coords.latitude;
	let lon = position.coords.longitude;
			
	latInput.value = lat;
	lonInput.value = lon;
			
}
		
function getGeoErr() {
	console.log("경로를 찾을 수 없습니다.")
}