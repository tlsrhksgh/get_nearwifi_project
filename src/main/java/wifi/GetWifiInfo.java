package wifi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import db.WifiService;
import dto.WifiInfo;

public class GetWifiInfo {
	final static String API_KEY = "authentication_key";
	
	public static int getWifiList() throws IOException{		
		WifiInfo wifiInfo = new WifiInfo();
		JsonParser jsonParser = new JsonParser();
		Gson gson = new Gson();
		
		int startIdx = 1;
		int endIdx = 1000;
		int totalCount;	
		
		while(true) {
			StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
			urlBuilder.append("/" +  URLEncoder.encode(API_KEY,"UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
			urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); 
			urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
			urlBuilder.append("/" + URLEncoder.encode(String.valueOf(startIdx),"UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
			urlBuilder.append("/" + URLEncoder.encode(String.valueOf(endIdx),"UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
			// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
					
			// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
//			urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* 서비스별 추가 요청인자들*/
					
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
			BufferedReader rd;

			// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
					rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
					rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			StringBuilder sb = new StringBuilder();
			String line;
					
			while ((line = rd.readLine()) != null) {				
					sb.append(line);								
			}
					
			String str = sb.toString();
							
			JsonElement element = jsonParser.parse(str);				
			JsonObject jsonObj = element.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject();				
			JsonArray wifiRows = jsonObj.getAsJsonObject().get("row").getAsJsonArray();
			totalCount = jsonObj.getAsJsonObject().get("list_total_count").getAsInt();
			
			for(int i = 0; i < wifiRows.size(); i++) {
				JsonObject obj = (JsonObject) wifiRows.get(i);
				wifiInfo.setWifiMgrNo(obj.get("X_SWIFI_MGR_NO").getAsString());	
				wifiInfo.setWifiWard(obj.get("X_SWIFI_WRDOFC").getAsString());
				wifiInfo.setWifiName(obj.get("X_SWIFI_MAIN_NM").getAsString());
				wifiInfo.setWifiAddr1(obj.get("X_SWIFI_ADRES1").getAsString());
				wifiInfo.setWifiAddr2(obj.get("X_SWIFI_ADRES2").getAsString());
				wifiInfo.setWifiInstlFloor(obj.get("X_SWIFI_INSTL_FLOOR").getAsString());
				wifiInfo.setWifiInstlType(obj.get("X_SWIFI_INSTL_TY").getAsString());
				wifiInfo.setWifiInstlInstt(obj.get("X_SWIFI_INSTL_MBY").getAsString());
				wifiInfo.setWifiSvcSection(obj.get("X_SWIFI_SVC_SE").getAsString());
				wifiInfo.setWifiNetType(obj.get("X_SWIFI_CMCWR").getAsString());
				wifiInfo.setWifiInstlYear(obj.get("X_SWIFI_CNSTC_YEAR").getAsString());
				wifiInfo.setWifiInOutDoor(obj.get("X_SWIFI_INOUT_DOOR").getAsString());
				wifiInfo.setWifiConnEnv(obj.get("X_SWIFI_REMARS3").getAsString());
				wifiInfo.setWifiCoordX(obj.get("LAT").getAsDouble());
				wifiInfo.setWifiCoordY(obj.get("LNT").getAsDouble());
				wifiInfo.setWifiWorkDate(obj.get("WORK_DTTM").getAsString());				
					
				WifiService wSvc = new WifiService();
				wSvc.insertWifiInfo(wifiInfo);
			}					
									
			if(endIdx > totalCount) {
				rd.close();
				conn.disconnect();
				break;				
			}
			
			
			
			startIdx += 1000;	
			endIdx += 1000;	
							
		}
		return totalCount;
	}
		
}