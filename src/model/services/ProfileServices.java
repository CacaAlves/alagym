package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ProfileDao;
import model.entities.Profile;

public class ProfileServices {
	
	ProfileDao dao = DaoFactory.createProfileDao();
	
	public List<Profile> getProfiles() {
		return dao.findAll();
		
	}
	
	public void saveProfile(Profile profile) {
		dao.insert(profile);
	}
	
	public void updateProfile(Profile profile) {
		dao.update(profile);
	}

	public void deleteAccount(Profile profile) {
		dao.deleteById(profile.getId());
	}
	
	public Profile getProfile(Integer id) {
		return dao.findById(id);
	}
}
