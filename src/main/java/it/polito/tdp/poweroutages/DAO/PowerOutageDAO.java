package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.polito.tdp.poweroutages.model.Blackout;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<Blackout> getBlackouts(int nerc_id){
		String sql = "SELECT id, customers_affected, date_event_began, date_event_finished "
				+ "FROM poweroutages "
				+ "WHERE nerc_id = ? "
				+ "ORDER BY date_event_began";
		
		List<Blackout> eventi = new ArrayList<Blackout>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nerc_id);
			
			ResultSet res = st.executeQuery();

			while (res.next()) {
			
				LocalDateTime tempo1 = res.getTimestamp("date_event_began").toLocalDateTime();
				LocalDateTime tempo2 = res.getTimestamp("date_event_finished").toLocalDateTime();
				
				Blackout b = new Blackout(res.getInt("id"), tempo1, tempo2, res.getInt("customers_affected"));
				eventi.add(b);
			}
			
			st.close();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return eventi;
	}
}
