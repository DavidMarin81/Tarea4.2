package src.main.java.modelo.servicio.departamento;

import java.util.List;

import src.main.java.modelo.Departamento;
import src.main.java.modelo.exceptions.DuplicateInstanceException;

public interface IServicioDepartamento {
	
	public long create(Departamento dept) throws DuplicateInstanceException;
	public boolean delete(Departamento dept);
	public boolean update(Departamento dept);
	public List<Departamento> findAll();
	public boolean exists(Integer deptno) ;

}
