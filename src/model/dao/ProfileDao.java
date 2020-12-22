package model.dao;

import java.util.List;

import model.entities.Profile;

public interface ProfileDao {
	
	void insert(Profile obj);

	void update(Profile obj);

	void deleteById(Integer id);

	Profile findById(Integer id);

	List<Profile> findAll();
}
