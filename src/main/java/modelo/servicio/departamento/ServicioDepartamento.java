package src.main.java.modelo.servicio.departamento;

import java.util.List;

import src.main.java.modelo.Departamento;
import src.main.java.modelo.departamento.DepartamentoNeoDatisDao;
import src.main.java.modelo.departamento.IDepartamentoDao;
import src.main.java.modelo.exceptions.DuplicateInstanceException;

public class ServicioDepartamento implements IServicioDepartamento {

	private IDepartamentoDao departamentoDao;
	
	public ServicioDepartamento() {
		departamentoDao= new DepartamentoNeoDatisDao();
	}
	
	@Override
	public long create(Departamento dept) throws DuplicateInstanceException {
		
		return departamentoDao.create(dept);
	}

	@Override
	public boolean delete(Departamento dept) {
		
		return departamentoDao.delete(dept);
	}

	@Override
	public boolean update(Departamento dept) {
		
		return departamentoDao.update(dept);
	}

	@Override
	public List<Departamento> findAll() {
		
		return departamentoDao.findAll();
	}

	@Override
	public boolean exists(Integer deptno) {
		
		return departamentoDao.exists(deptno);
	}

}
