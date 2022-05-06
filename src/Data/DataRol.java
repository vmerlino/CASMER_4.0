package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import Entities.*;

public class DataRol {
	
	public LinkedList<Rol> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Rol> roles= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from rol");
			if(rs!=null) {
				while(rs.next()) {
					Rol r=new Rol();
					r.setId(rs.getInt("idrol"));
					r.setDescripcion(rs.getString("descripcion"));
					roles.add(r);
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
		
		
		return roles;
	}
	
	public Rol getById(Rol rolToSearch) {
		Rol r=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from rol where idrol=?"
					);
			stmt.setInt(1, rolToSearch.getId());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				r=new Rol();
				r.setId(rolToSearch.getId());
				r.setDescripcion(rs.getString("descripcion"));
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
		
		return r;
	}
	
	public Rol getByDesc(Rol rolToSearch) {
		Rol r=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from rol where descripcion=?"
					);
			stmt.setString(1, rolToSearch.getDescripcion());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				r=new Rol();
				r.setId(rs.getInt("idrol"));
				r.setDescripcion(rs.getString("descripcion"));
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
		
		return r;
	}
	
	public LinkedList<Rol> getRolesUsuario(String dni) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Rol r=new Rol();
		LinkedList<Rol> roles = new LinkedList<>(); 
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					  "select * from rol_usuario where dni=? "
					);
			stmt.setString(1, dni);
			rs= stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					
					r.setId(rs.getInt("idrol"));
					r=this.getById(r);
					roles.add(r);
				}
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
		return roles;
	}
	
	public void add(Rol rol) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into rol(descripcion) values(?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, rol.getDescripcion());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                rol.setId(keyResultSet.getInt(1));
            }

			
		} catch (SQLException e) {
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
	
	public void update(Rol rol) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update rol set descripcion=? where idrol=?");
			stmt.setString(1, rol.getDescripcion());
			stmt.setInt(2, rol.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
	public void remove(Rol rol) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from rol where id=?");
			stmt.setInt(1, rol.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}

	public void newRolUsuario(Rol r, Usuario usu) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into rol_usuario(idrol,dni) values(?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, r.getId());
			stmt.setString(2, usu.getDni());
			stmt.executeUpdate();

			
		} catch (SQLException e) {
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
}
