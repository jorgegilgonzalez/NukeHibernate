package dao;

import javafx.collections.ObservableList;
import modelo.CargosEntity;
import modelo.PersonalEntity;

public interface CargosDao {
	
	void saveCargos(CargosEntity personal);

	CargosEntity getCargosById(int id);

	ObservableList<CargosEntity> getAllCargos();

	void updateCargos(CargosEntity cargos);

	void deleteCargosById(int id);
	
}
