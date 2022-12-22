package dao;

import javafx.collections.ObservableList;
import modelo.PersonalEntity;

public interface PersonalDao {
	
	void savePersonal(PersonalEntity personal);

	PersonalEntity getPersonalById(int id);

	ObservableList<PersonalEntity> getAllPersonal();

	void updatePersonal(PersonalEntity personal);

	void deletePersonalById(int id);

	ObservableList<PersonalEntity> getPersonalFiltrado(PersonalEntity personal);
	
}
