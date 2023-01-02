package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

import dto.WifiHistory;
import dto.WifiInfo;
import wifi.WifiDistance;


public class WifiService {		
	public List<WifiInfo> searchWifi(double lat, double lnt) {		
		
		List<WifiInfo> wifiList = new ArrayList();
		WifiInfo wi = new WifiInfo();
		
		WifiDistance wd = new WifiDistance();
		
		if(lat == 0.0 || lnt == 0.0) {
			return wifiList;
		}
						
		
		String dbFile = "C:\\test\\zero_wifi_project\\wifiInfo_project\\src\\main\\webapp\\db\\wifiInfo.db";		
		
		try {		
			// SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");			
			
		} catch(Exception e) {
			System.out.println("Error!! : ");
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		 
		try {
			
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);			
			
			String sql = " select wifi_distance, wifi_mgr_no, wifi_ward, wifi_name, wifi_addr1, wifi_addr2, "
					+ " wifi_instl_floor, wifi_instl_type, wifi_instl_instt, wifi_svc_section, wifi_net_type, wifi_instl_year,  "
					+ "wifi_inout_door, wifi_conn_env, wifi_coordx, wifi_coordy, wifi_work_date "
					+ " from wifi_info ";
						
			pstmt = conn.prepareStatement(sql);
//			pstmt.setDouble(1, Math.abs(pos - wi.getWifiDistance()));
			
			rs = pstmt.executeQuery();				
			
			while(rs.next()) {
				String mgrNo = rs.getString("wifi_mgr_no");				
				String ward = rs.getString("wifi_ward");
				String name = rs.getString("wifi_name");
				String addr1 = rs.getString("wifi_addr1");
				String addr2 = rs.getString("wifi_addr2");
				String instlFloor = rs.getString("wifi_instl_floor");
				String instlType = rs.getString("wifi_instl_type");
				String instlInstt = rs.getString("wifi_instl_instt");
				String svcSection = rs.getString("wifi_svc_section");
				String netType = rs.getString("wifi_net_type");
				String instlYear = rs.getString("wifi_instl_year");
				String inoutDoor = rs.getString("wifi_inout_door");
				String connEnv = rs.getString("wifi_conn_env");
				double coordX = rs.getDouble("wifi_coordx");
				double coordY = rs.getDouble("wifi_coordy");
				String workDate = rs.getString("wifi_work_date");
				double distance = wd.getWIfiDistance(lat, lnt, coordX, coordY);	
				
				WifiInfo wifi = new WifiInfo();
				
				wifi.setWifiDistance(distance);
				wifi.setWifiMgrNo(mgrNo);
				wifi.setWifiWard(ward);
				wifi.setWifiName(name);								
				wifi.setWifiAddr1(addr1);
				wifi.setWifiAddr2(addr2);
				wifi.setWifiInstlFloor(instlFloor);
				wifi.setWifiInstlType(instlType);
				wifi.setWifiInstlInstt(instlInstt);
				wifi.setWifiSvcSection(svcSection);
				wifi.setWifiNetType(netType);
				wifi.setWifiInstlYear(instlYear);
				wifi.setWifiInOutDoor(inoutDoor);
				wifi.setWifiConnEnv(connEnv);
				wifi.setWifiCoordX(coordX);
				wifi.setWifiCoordY(coordY);
				wifi.setWifiWorkDate(workDate);
				
				wifiList.add(wifi);
            }
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {			
			try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (pstmt != null && !pstmt.isClosed()) {
                	pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
		
		return wifiList; 
	}
}
	
	public void insertWifiInfo(WifiInfo wi) {
		String dbFile = "C:\\test\\zero_wifi_project\\wifiInfo_project\\src\\main\\webapp\\db\\wifiInfo.db";
		
		try {
		
			// SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");								
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			String sql = "insert into wifi_info (wifi_mgr_no, wifi_ward, wifi_name, "
					+ "wifi_addr1, wifi_addr2, wifi_instl_floor, wifi_instl_type, wifi_instl_instt, wifi_svc_section, "
					+ "wifi_net_type, wifi_instl_year, wifi_inout_door, wifi_conn_env, wifi_coordx, "
					+ "wifi_coordy, wifi_work_date) "
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, wi.getWifiMgrNo());
			pstmt.setString(2, wi.getWifiWard());
			pstmt.setString(3, wi.getWifiName());
			pstmt.setString(4, wi.getWifiAddr1());
			pstmt.setString(5, wi.getWifiAddr2());
			pstmt.setString(6, wi.getWifiInstlFloor());
			pstmt.setString(7, wi.getWifiInstlType());
			pstmt.setString(8, wi.getWifiInstlInstt());
			pstmt.setString(9, wi.getWifiSvcSection());
			pstmt.setString(10, wi.getWifiNetType());
			pstmt.setString(11, wi.getWifiInstlYear());
			pstmt.setString(12, wi.getWifiInOutDoor());
			pstmt.setString(13, wi.getWifiConnEnv());
			pstmt.setDouble(14, wi.getWifiCoordX());
			pstmt.setDouble(15, wi.getWifiCoordY());
			pstmt.setString(16, wi.getWifiWorkDate());
			
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {	
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                	pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
	}
	
	public void insertHistory(double lat, double lnt) {
		
		if(lat == 0.0 || lnt == 0.0) {
			return ;
		}
		
		String dbFile = "C:\\test\\zero_wifi_project\\wifiInfo_project\\src\\main\\webapp\\db\\wifiInfo.db";
		
		WifiHistory wh = new WifiHistory();
		
		String btn = "<button class='delBtn'>삭제</button>";
		
		
		wh.setCoordX(lat);
		wh.setCoordY(lnt);
		wh.setNote(btn);
		
		try {
		
			// SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");								
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			
			String sql = "insert into select_history (coordx, coordy, note) "
					+ " values (?, ?, ?);";
			
			pstmt = conn.prepareStatement(sql);			
			pstmt.setDouble(1, wh.getCoordX());
			pstmt.setDouble(2, wh.getCoordY());
			pstmt.setString(3, wh.getNote());
			
			pstmt.executeUpdate();					
			
			System.out.println("history 저장 성공!!");
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {	
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                	pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
}
	
	public List<WifiHistory> searchWifiHistory() {
		
		List<WifiHistory> historyList = new ArrayList();		
				
		String dbFile = "C:\\test\\zero_wifi_project\\wifiInfo_project\\src\\main\\webapp\\db\\wifiInfo.db";		
		
		try {		
			// SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");			
			
		} catch(Exception e) {
			System.out.println("Error!! : ");
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);			
			
			String sql = " select * from select_history order by id desc; ";					
						
			pstmt = conn.prepareStatement(sql);			
			
			rs = pstmt.executeQuery();									
			
			while(rs.next()) { 				
				int id = rs.getInt("id");
				double coordX = rs.getDouble("coordx");
				double coordY = rs.getDouble("coordy");
				String date = rs.getString("select_date");
				String note = rs.getString("note");
				
				WifiHistory wifiHistory = new WifiHistory();
								
				
				wifiHistory.setId(id);
				wifiHistory.setCoordX(coordX);
				wifiHistory.setCoordY(coordY);
				wifiHistory.setDate(date);
				wifiHistory.setNote(note);
				
				historyList.add(wifiHistory);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {			
			try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (pstmt != null && !pstmt.isClosed()) {
                	pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            return historyList; 
	}		
}

	public void deleteHistoryRow(String id) {
		String dbFile = "C:\\test\\zero_wifi_project\\wifiInfo_project\\src\\main\\webapp\\db\\wifiInfo.db";				
		
		int rowId = Integer.parseInt(id); 
		
		try {		
			// SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");			
			
		} catch(Exception e) {
			System.out.println("Error!! : ");
			e.printStackTrace();
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		try {
			
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);			
			
			String sql = " delete from select_history where id = ? ; ";						
			
			pstmt = conn.prepareStatement(sql);			
			
			
			pstmt.setInt(1, rowId);
						
			
			pstmt.executeUpdate();		
			
			
			System.out.println("삭제 성공!");
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {			

            try {
                if (pstmt != null && !pstmt.isClosed()) {
                	pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
	}
}