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
import model.dao.ProfileDao;
import model.entities.GymGoal;
import model.entities.Profile;

public class ProfileDaoJDBC implements ProfileDao {

	private Connection conn;

	public ProfileDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Profile obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO Profile "
					+ "(name, age, biological_genre, weight, height, profile_img_path, gym_goal, calories, protein, carb, fat, sugar, fiber, vitB12, vitD, vitA, vitC, vitE, calcium, iodine, iron, zinc) "
					+ "VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());
			st.setInt(2, obj.getAge());
			st.setString(3, "" + obj.getBiologicalGenre());
			st.setDouble(4, obj.getWeight());
			st.setDouble(5, obj.getHeight());
			st.setString(6, (obj.getProfileImgPath() == null ? "" : obj.getProfileImgPath()));
			st.setString(7, obj.getGymGoal().toString());
			st.setDouble(8, obj.getCalories().getQuantity());
			st.setDouble(9, obj.getProtein().getQuantity());
			st.setDouble(10, obj.getCarb().getQuantity());
			st.setDouble(11, obj.getFat().getQuantity());
			st.setDouble(12, obj.getSugar().getQuantity());
			st.setDouble(13, obj.getFiber().getQuantity());
			st.setDouble(14, obj.getVitB12().getQuantity());
			st.setDouble(15, obj.getVitD().getQuantity());
			st.setDouble(16, obj.getVitA().getQuantity());
			st.setDouble(17, obj.getVitC().getQuantity());
			st.setDouble(18, obj.getVitE().getQuantity());
			st.setDouble(19, obj.getCalcium().getQuantity());
			st.setDouble(20, obj.getIodine().getQuantity());
			st.setDouble(21, obj.getIron().getQuantity());
			st.setDouble(22, obj.getZinc().getQuantity());

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
	public void update(Profile obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE Profile "
					+ "SET name = ?, age = ?, biological_genre = ?, weight = ?, height = ?, profile_img_path = ?, gym_goal = ?, calories = ?, protein = ?, carb = ?, fat = ?, sugar = ?, fiber = ?, vitB12 = ?, vitD = ?, vitA = ?, vitC = ?, vitE = ?, calcium = ?, iodine = ?, iron = ?, zinc = ? "
					+ "WHERE profile_id = ?");

			st.setString(1, obj.getName());
			st.setInt(2, obj.getAge());
			st.setString(3, "" + obj.getBiologicalGenre());
			st.setDouble(4, obj.getWeight());
			st.setDouble(5, obj.getHeight());
			st.setString(6, (obj.getProfileImgPath() == null ? "" : obj.getProfileImgPath()));
			st.setString(7, obj.getGymGoal().toString());
			st.setDouble(8, obj.getCalories().getQuantity());
			st.setDouble(9, obj.getProtein().getQuantity());
			st.setDouble(10, obj.getCarb().getQuantity());
			st.setDouble(11, obj.getFat().getQuantity());
			st.setDouble(12, obj.getSugar().getQuantity());
			st.setDouble(13, obj.getFiber().getQuantity());
			st.setDouble(14, obj.getVitB12().getQuantity());
			st.setDouble(15, obj.getVitD().getQuantity());
			st.setDouble(16, obj.getVitA().getQuantity());
			st.setDouble(17, obj.getVitC().getQuantity());
			st.setDouble(18, obj.getVitE().getQuantity());
			st.setDouble(19, obj.getCalcium().getQuantity());
			st.setDouble(20, obj.getIodine().getQuantity());
			st.setDouble(21, obj.getIron().getQuantity());
			st.setDouble(22, obj.getZinc().getQuantity());
			st.setInt(23, obj.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM Profile WHERE profile_id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Profile findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM Profile WHERE profile_id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				GymGoal gymGoal = null;
				switch (rs.getString("gym_goal")) {
				case "LOSE_WEIGHT":
					gymGoal = GymGoal.LOSE_WEIGHT;
					break;
				case "MAINTAIN_WEIGHT":
					gymGoal = GymGoal.MAINTAIN_WEIGHT;
					break;
				case "GAIN_WEIGHT":
					gymGoal = GymGoal.GAIN_WEIGHT;
					break;
				default:
					break;
				}
				String profile_img_path = (rs.getString("profile_img_path").isBlank() ? null
						: rs.getString("profile_img_path"));
				Profile obj = new Profile(rs.getInt("profile_id"), rs.getInt("age"),
						rs.getString("biological_genre").charAt(0), rs.getString("name"), rs.getDouble("weight"),
						rs.getDouble("height"), profile_img_path, gymGoal, rs.getDouble("calories"),
						rs.getDouble("protein"), rs.getDouble("carb"), rs.getDouble("fat"), rs.getDouble("sugar"),
						rs.getDouble("fiber"), rs.getDouble("vitB12"), rs.getDouble("vitD"), rs.getDouble("vitA"),
						rs.getDouble("vitC"), rs.getDouble("vitE"), rs.getDouble("calcium"), rs.getDouble("iodine"),
						rs.getDouble("iron"), rs.getDouble("zinc"));
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Profile> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM Profile ORDER BY profile_id");
			rs = st.executeQuery();

			List<Profile> list = new ArrayList<>();

			while (rs.next()) {
				GymGoal gymGoal = null;
				switch (rs.getString("gym_goal")) {
				case "LOSE_WEIGHT":
					gymGoal = GymGoal.LOSE_WEIGHT;
					break;
				case "MAINTAIN_WEIGHT":
					gymGoal = GymGoal.MAINTAIN_WEIGHT;
					break;
				case "GAIN_WEIGHT":
					gymGoal = GymGoal.GAIN_WEIGHT;
					break;
				default:
					break;
				}
				String profile_img_path = (rs.getString("profile_img_path").isBlank() ? null
						: rs.getString("profile_img_path"));
				Profile obj = new Profile(rs.getInt("profile_id"), rs.getInt("age"),
						rs.getString("biological_genre").charAt(0), rs.getString("name"), rs.getDouble("weight"),
						rs.getDouble("height"), profile_img_path, gymGoal, rs.getDouble("calories"),
						rs.getDouble("protein"), rs.getDouble("carb"), rs.getDouble("fat"), rs.getDouble("sugar"),
						rs.getDouble("fiber"), rs.getDouble("vitB12"), rs.getDouble("vitD"), rs.getDouble("vitA"),
						rs.getDouble("vitC"), rs.getDouble("vitE"), rs.getDouble("calcium"), rs.getDouble("iodine"),
						rs.getDouble("iron"), rs.getDouble("zinc"));
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
