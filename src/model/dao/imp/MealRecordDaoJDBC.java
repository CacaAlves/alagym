package model.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.MealRecordDao;
import model.entities.MealRecord;
import model.services.ProfileServices;

public class MealRecordDaoJDBC implements MealRecordDao {
	
	ProfileServices profileServices = new ProfileServices();
	
	private Connection conn;
	
	public MealRecordDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(MealRecord obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO MealRecord " +
				"(profile_id, date) " +
				"VALUES " +
				"(?, ?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, obj.getProfile().getId());
			st.setDate(2, java.sql.Date.valueOf(obj.getDate()));

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(MealRecord obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM MealRecord WHERE meal_record_id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public MealRecord find(Integer profileId, LocalDate date) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM MealRecord WHERE profile_id = ? AND date = ?");
			st.setInt(1, profileId);
			st.setDate(2, java.sql.Date.valueOf(date));
			rs = st.executeQuery();
			if (rs.next()) {
				MealRecord obj = new MealRecord();
				obj.setId(rs.getInt("meal_record_id"));
				obj.setProfile(profileServices.getProfile(rs.getInt("profile_id")));
				obj.setDate(date);
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<MealRecord> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM MealRecord ORDER BY meal_record_id");
			rs = st.executeQuery();

			List<MealRecord> list = new ArrayList<>();

			while (rs.next()) {
				MealRecord obj = new MealRecord(
				rs.getInt("meal_record_id"),
				profileServices.getProfile(rs.getInt("profile_id")),
				rs.getDate("date").toLocalDate()
				);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
