package Logic;

import java.util.LinkedList;

import Data.DataRol;
import Entities.Rol;
import Entities.Usuario;

public class LogicRol {

	public Rol getById(Rol r) {
		DataRol dr = new DataRol();		
		return dr.getById(r);
	}

	public void newRolUsuario(Rol r, Usuario usu) {
		DataRol dr = new DataRol();		
		dr.newRolUsuario(r, usu);
	}

	public LinkedList<Rol> getRolesUsuario(String dni) {
		DataRol dr = new DataRol();		
		return dr.getRolesUsuario(dni);
	}

}
