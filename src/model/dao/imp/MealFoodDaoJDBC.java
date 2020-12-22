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
import model.dao.DaoFactory;
import model.dao.FoodDao;
import model.dao.MealFoodDao;
import model.entities.MealFood;
import model.entities.MealType;
import model.services.FoodServices;

public class MealFoodDaoJDBC implements MealFoodDao {

	private FoodDao foodDao = DaoFactory.createFoodDao();
	
	private FoodServices foodServices = new FoodServices();
	
	public Connection conn;

	public MealFoodDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Integer mealRecordId, MealFood obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO MealFood " + 
					"(meal_record_id, food_id, meal_type, quantity) " +
					"VALUES " + "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, mealRecordId);
			st.setInt(2, obj.getId());
			st.setString(3, obj.getMealType().toString());
			st.setDouble(4, obj.getQuantity().getQuantity());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(MealFood obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM MealFood WHERE meal_food_id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public MealFood findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MealFood> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MealFood> findByMealRecordId(Integer mealRecordId) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM MealFood WHERE meal_record_id = ?");
			st.setInt(1, mealRecordId);
			rs = st.executeQuery();

			List<MealFood> list = new ArrayList<>();

			while (rs.next()) {
				MealFood obj = 
				foodServices.parseToMealFood(foodDao.findById(rs.getInt("food_id")));
				obj.setMealFoodId(rs.getInt("meal_food_id"));
				obj.getQuantity().setQuantity(rs.getDouble("quantity"));
				MealType mealType = null;
				switch (rs.getString("meal_type")) {
				case "BREAKFAST":
					mealType = MealType.BREAKFAST;
					break;
				case "LUNCH":
					mealType = MealType.LUNCH;
					break;
				case "DINNER":
					mealType = MealType.DINNER;
					break;
				case "SNACKS":
					mealType = MealType.SNACKS;
					break;
				default:
				}
				obj.setMealType(mealType);
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
