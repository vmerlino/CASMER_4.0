package Data;

import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Entities.Categoria;

public class DataCategoria {

	public LinkedList<Categoria> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Categoria> cate= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select idCategoria,descripcion from categoria order by descripcion");
			//intencionalmente no se recupera la password
			if(rs!=null) {
				while(rs.next()) {
					Categoria p=new Categoria();
					p.setIdCategoria(rs.getInt("idcategoria"));
					p.setDescripcion(rs.getString("descripcion"));
					cate.add(p);
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
				System.out.println("se ha producido un error");
			}
		}
		
		
		return cate;
	}

	
	public Categoria getByIdCategoria(int idCat) {
		Categoria c=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idCategoria,descripcion from categoria where idCategoria=?"
					);
			stmt.setInt(1, idCat);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				c=new Categoria();
				c.setIdCategoria(rs.getInt("idCategoria"));
				c.setDescripcion(rs.getString("descripcion"));
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
		
		return c;
	}
	
	public void add(Categoria c) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into categoria(descripcion) values(?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setString(1, c.getDescripcion());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                c.setIdCategoria(keyResultSet.getInt(1));
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
	
	public String delete(Categoria cat) {
		PreparedStatement stmt=null;
		String error="";
		try 
		{
			stmt=DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM categoria WHERE idCategoria= ?");
			stmt.setInt(1,cat.getIdCategoria());
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			error="No es posible eliminar la categoria, ya que se encuentran productos cargados en ella"	;
		}finally {
			try {
				if(stmt!=null)stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
				

			}
		}	
		return error;

	}


	public void edit(Categoria cat) {
			PreparedStatement stmt = null;
			try {
				stmt=DbConnector.getInstancia().getConn().prepareStatement("UPDATE categoria set descripcion=? WHERE idCategoria=? ");
				stmt.setString(1, cat.getDescripcion());
				stmt.setInt(2, cat.getIdCategoria());
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


	public LinkedList<Categoria> getByDescripcion(Categoria cat) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Categoria> cate= new LinkedList<>();
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("Select * from categoria   WHERE descripcion LIKE ? ");
			stmt.setString(1,cat.getDescripcion()+'%');
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Categoria p=new Categoria();
					p.setIdCategoria(rs.getInt("idcategoria"));
					p.setDescripcion(rs.getString("descripcion"));
					cate.add(p);
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
		
		
		return cate;
	}


	
}
