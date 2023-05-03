package src.main.java.modelo.departamento;

import src.main.java.modelo.Departamento;
import src.main.java.modelo.dao.IGenericDao;

public interface IDepartamentoDao extends IGenericDao<Departamento>{
	public boolean exists(Integer dept);
}
