package Data;

import Entities.*;
import java.sql.*;
import java.util.LinkedList;

public class DataUsuario {
	
	public LinkedList<Usuario> getAll(){
		DataRol dr=new DataRol();
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Usuario> usus= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select dni,nombre,apellido,direccion,telefono,contrasenia,email from usuario");
			//intencionalmente no se recupera la password
			if(rs!=null) {
				while(rs.next()) {
					Usuario p=new Usuario();
					p.setDni(rs.getString("dni"));
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setEmail(rs.getString("email"));
					p.setTelefono(rs.getString("telefono"));
					p.setDireccion(rs.getString("direccion"));
					p.setRol(dr.getRolesUsuario(p.getDni())); 
					usus.add(p);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return usus;
	}
	
	public Usuario getByUser(Usuario usu) {
		DataRol dr=new DataRol();
		Usuario u=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select dni,nombre,apellido,direccion,telefono,contrasenia,email from usuario where email=? and contrasenia=?"
					);
			stmt.setString(1, usu.getEmail());
			stmt.setString(2, usu.getContrasenia());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				u=new Usuario();
				u.setDni(rs.getString("dni"));
				u.setNombre(rs.getString("nombre"));
				u.setApellido(rs.getString("apellido"));
				u.setEmail(rs.getString("email"));
				u.setTelefono(rs.getString("telefono"));
				u.setDireccion(rs.getString("direccion"));
				u.setContrasenia(rs.getString("contrasenia"));
				u.setRol(dr.getRolesUsuario(u.getDni()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		return u;
	}
	
	public Usuario getByDni(Usuario usu) {
		DataRol dr=new DataRol();
		Usuario u=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select dni,nombre,apellido,direccion,telefono,contrasenia,email from usuario where dni=?"
					);
			stmt.setString(1, usu.getDni());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				u=new Usuario();
				u.setDni(rs.getString("dni"));
				u.setNombre(rs.getString("nombre"));
				u.setApellido(rs.getString("apellido"));
				u.setEmail(rs.getString("email"));
				u.setTelefono(rs.getString("telefono"));
				u.setDireccion(rs.getString("direccion"));
				u.setContrasenia(rs.getString("contrasenia"));
			
				u.setRol(dr.getRolesUsuario(u.getDni()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return u;
	}
	
	public void add(Usuario p) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into usuario(nombre, apellido, Dni, direccion, email, contrasenia, telefono) values(?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, p.getNombre());
			stmt.setString(2, p.getApellido());
			stmt.setString(3, p.getDni());
			stmt.setString(4, p.getDireccion());
			stmt.setString(5, p.getEmail());
			stmt.setString(6, p.getContrasenia());
			stmt.setString(7, p.getTelefono());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setDni(keyResultSet.getString(1));
            }

			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
    }
	
	public void delete(Usuario usu) {
		PreparedStatement stmt=null;
		try 
		{
			stmt=DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM usuario WHERE dni= ?");
			stmt.setString(1,usu.getDni());
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(stmt!=null)stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void edit(Usuario usu) {
		PreparedStatement stmt = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("UPDATE usuario set nombre=?,apellido=?,direccion=?,telefono=?,contrasenia=?,email=? WHERE dni=? ");
			stmt.setString(1, usu.getNombre());
			stmt.setString(2, usu.getApellido());
			stmt.setString(3, usu.getDireccion());
			stmt.setString(4, usu.getTelefono());
			stmt.setString(5, usu.getContrasenia());
			stmt.setString(6, usu.getEmail());
			stmt.setString(7, usu.getDni());
			stmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(stmt!=null)stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}	
	}
	
}