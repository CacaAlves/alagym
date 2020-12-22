package model.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.FoodDao;
import model.entities.Food;

public class FoodDaoJDBC implements FoodDao {

	private Connection conn;
	
	public FoodDaoJDBC(Connection conn) { 
		this.conn = conn;
	}
	
	@Override
	public void insert(Food obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO Food " +
				"(name, quantity, calories, protein, carb, fat, sugar, fiber, vitB12, vitD, vitA, vitC, vitE, calcium, iodine, iron, zinc) " +
				"VALUES " +
				"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());
			st.setDouble(2, obj.getPortion().getQuantity());
			st.setDouble(3, obj.getCalories().getQuantity());
			st.setDouble(4, obj.getProtein().getQuantity());
			st.setDouble(5, obj.getCarb().getQuantity());
			st.setDouble(6, obj.getFat().getQuantity());
			st.setDouble(7, obj.getSugar().getQuantity());
			st.setDouble(8, obj.getFiber().getQuantity());
			st.setDouble(9, obj.getVitB12().getQuantity());
			st.setDouble(10, obj.getVitD().getQuantity());
			st.setDouble(11, obj.getVitA().getQuantity());
			st.setDouble(12, obj.getVitC().getQuantity());
			st.setDouble(13, obj.getVitE().getQuantity());
			st.setDouble(14, obj.getCalcium().getQuantity());
			st.setDouble(15, obj.getIodine().getQuantity());
			st.setDouble(16, obj.getIron().getQuantity());
			st.setDouble(17, obj.getZinc().getQuantity());

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
	public void update(Food obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM Food WHERE food_id = ?");

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
	public Food findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM Food WHERE food_id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Food obj = new Food(
				rs.getInt("food_id"), rs.getString("name"), rs.getDouble("quantity"), rs.getDouble("calories"),
				rs.getDouble("protein"), rs.getDouble("carb"), rs.getDouble("fat"),
				rs.getDouble("sugar"), rs.getDouble("fiber"), rs.getDouble("vitB12"),
				rs.getDouble("vitD"), rs.getDouble("vitA"), rs.getDouble("vitC"),
				rs.getDouble("vitE"), rs.getDouble("calcium"), rs.getDouble("iodine"),
				rs.getDouble("iron"), rs.getDouble("zinc"));
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
	public List<Food> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM Food ORDER BY name");
			rs = st.executeQuery();

			List<Food> list = new ArrayList<>();

			while (rs.next()) {
				Food obj = new Food(
				rs.getInt("food_id"), rs.getString("name"), rs.getDouble("quantity"), rs.getDouble("calories"),
				rs.getDouble("protein"), rs.getDouble("carb"), rs.getDouble("fat"),
				rs.getDouble("sugar"), rs.getDouble("fiber"), rs.getDouble("vitB12"),
				rs.getDouble("vitD"), rs.getDouble("vitA"), rs.getDouble("vitC"),
				rs.getDouble("vitE"), rs.getDouble("calcium"), rs.getDouble("iodine"),
				rs.getDouble("iron"), rs.getDouble("zinc"));
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
