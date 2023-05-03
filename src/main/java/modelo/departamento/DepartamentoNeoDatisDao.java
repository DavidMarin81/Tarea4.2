package src.main.java.modelo.departamento;

import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.oid.OIDFactory;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import src.main.java.modelo.Departamento;
import src.main.java.modelo.dao.AbstractGenericDao;
import src.main.java.modelo.exceptions.InstanceNotFoundException;
import src.main.java.util.ConnectionFactory;
import src.main.java.util.Utils;


public class DepartamentoNeoDatisDao extends AbstractGenericDao<Departamento> implements IDepartamentoDao{

	private ODB dataSource;

	public DepartamentoNeoDatisDao() {
		this.dataSource = ConnectionFactory.getConnection();
	}
	
	@Override
	public long create(Departamento entity) {
		OID oid = null;
		long oidlong =-1;
		try {
			
			oid = this.dataSource.store(entity);
			this.dataSource.commit();

		} catch (Exception ex) {
			
			System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
			this.dataSource.rollback();
			oid = null;
		}
		if(oid!=null) {
			oidlong= oid.getObjectId();
		}
		return oidlong;
	}

	@Override
	public Departamento read(long id) throws InstanceNotFoundException {
		Departamento departamento = null;
		
		try {
			OID oid = OIDFactory.buildObjectOID(id);
			departamento = (Departamento) this.dataSource.getObjectFromId(oid);
			
			
		} catch (ODBRuntimeException ex) {
		
			System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

			throw new InstanceNotFoundException(id, getEntityClass());
		}
		catch(Exception ex) {
			
			System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

		}
		return departamento;
	}

	@Override
	public boolean update(Departamento entity) {
		boolean exito = false;
		try {
			this.dataSource.store(entity);
			this.dataSource.commit();
			exito = true;
		} catch (Exception ex) {			
			System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
			this.dataSource.rollback();
		}
		return exito;
	}

	@Override
	public boolean delete(Departamento entity) {
		boolean exito = false;
		try {
			this.dataSource.delete(entity);
			this.dataSource.commit();
			exito = true;
		} catch (Exception ex) {
			System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
			this.dataSource.rollback();
		}
		
		return exito;
	}

	@Override
	public List<Departamento> findAll() {
		CriteriaQuery query = new CriteriaQuery(Departamento.class);
		IQuery iquery = query.orderByAsc("empno");
		Objects<Departamento> empleados = dataSource.getObjects(iquery);
		return Utils.toList(empleados);
	}

	@Override
	public boolean exists(Integer dept) {
		boolean exito = false;
		
		CriteriaQuery query = new CriteriaQuery(Departamento.class, Where.equal("deptno", dept));
		Objects<Departamento> departamentos = dataSource.getObjects(query);
		return (departamentos.size()==1);
	}

}
