package dao;

import javafx.collections.ObservableList;
import modelo.BasesEntity;

public interface BasesDao {
	
	void saveBases(BasesEntity bases);

	BasesEntity getBasesById(int id);

	ObservableList<BasesEntity> getAllBases();

	void updateBases(BasesEntity bases);

	void deleteBasesById(int id);
	
}
