package Data;


import java.io.FileInputStream;
import java.io.IOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.LinkedList;



import Entities.Categoria;
import Entities.Producto;
import Logic.LogicCategoria;
import Logic.LogicProducto;

public class DataProducto {

	public LinkedList<Producto> getAll() throws IOException{
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Producto> prod= new LinkedList<>();		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select idproducto,idcategoria,descripcion, precio, stock, img from producto order by descripcion");
			if(rs!=null) {
				while(rs.next()) {
					Producto p=new Producto();
					Categoria cat=new Categoria();
					p.setIdProducto(rs.getInt("idProducto"));
					cat.setIdCategoria(rs.getInt("idCategoria"));
					p.setDescripcion(rs.getString("descripcion"));
					p.setPrecio(rs.getFloat("precio"));
					p.setStock(rs.getInt("stock"));
					
		            
					p.setCat(cat);
					p.setImg(rs.getString("img"));
					prod.add(p);}
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
		
		
		return prod;
	}

	
	public Producto getByIdProducto(Producto prod) {
		Producto p=null;
		Categoria c=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idProducto,idcategoria,descripcion, precio, stock, img from producto where idProducto=? "
					);
			stmt.setInt(1, prod.getIdProducto());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				p=new Producto();
				c=new Categoria();
				p.setIdProducto(rs.getInt("idProducto"));
				c.setIdCategoria((rs.getInt("idcategoria")));
				p.setCat(c);
				p.setDescripcion(rs.getString("descripcion"));
				p.setPrecio(rs.getFloat("precio"));
				p.setStock(rs.getInt("stock"));
				p.setImg(rs.getString("img"));

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
		
		return p;
	}
	

	public void add(Producto p) throws IOException {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		FileInputStream fis = null;

		try {
			
			
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into producto(idcategoria, descripcion, precio, stock, img) values(?, ?, ?,?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, p.getCat().getIdCategoria());
			stmt.setString(2, p.getDescripcion());
			stmt.setFloat(3, p.getPrecio());
			stmt.setInt(4, p.getStock());
            stmt.setString(5, p.getImg());
            stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setIdProducto(keyResultSet.getInt(1));
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
	
	public void delete(Producto prod) {
		PreparedStatement stmt=null;
		try 
		{
			stmt=DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM producto WHERE idproducto= ?");
			stmt.setInt(1,prod.getIdProducto());
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


	public void edit(Producto prod) {
			PreparedStatement stmt = null;
			try {
				if(prod.getImg()!=null) {
				stmt=DbConnector.getInstancia().getConn().prepareStatement("UPDATE producto set idcategoria=?, descripcion=?, precio=?, stock=?, img=?  WHERE idproducto=? ");
				stmt.setInt(1, prod.getCat().getIdCategoria());
				stmt.setString(2, prod.getDescripcion());
				stmt.setFloat(3, prod.getPrecio());
				stmt.setInt(4, prod.getStock());
				stmt.setString(5, prod.getImg());
				stmt.setInt(6, prod.getIdProducto());
				stmt.executeUpdate();
				}else {
					stmt=DbConnector.getInstancia().getConn().prepareStatement("UPDATE producto set idcategoria=?, descripcion=?, precio=?, stock=?  WHERE idproducto=? ");
					stmt.setInt(1, prod.getCat().getIdCategoria());
					stmt.setString(2, prod.getDescripcion());
					stmt.setFloat(3, prod.getPrecio());
					stmt.setInt(4, prod.getStock());
					stmt.setInt(5, prod.getIdProducto());
					stmt.executeUpdate();
				}
				
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

	public LinkedList<Producto> getSinStock(){
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Producto> prods= new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("Select idcategoria, idProducto, descripcion, precio, stock, img from producto  WHERE stock = 0 order by descripcion ");
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Producto p=new Producto();
					Categoria c= new Categoria();
					LogicCategoria ctrlCat = new LogicCategoria();
					p.setIdProducto(rs.getInt("idProducto"));
					p.setDescripcion(rs.getString("descripcion"));
					p.setPrecio(rs.getFloat("precio"));
					p.setStock(rs.getInt("stock"));
					p.setImg(rs.getString("img"));
					c = ctrlCat.getByIdCategoria(rs.getInt("idcategoria"));
					p.setCat(c);
					prods.add(p);
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
			}}
			
			return prods;
	}
	
	public LinkedList<Producto> getByDescripcion(Producto prod) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Producto> prods= new LinkedList<>();
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("Select idcategoria, idProducto, descripcion, precio, stock, img from producto  WHERE descripcion LIKE ? order by descripcion ");
			stmt.setString(1,'%'+prod.getDescripcion()+'%');
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Producto p=new Producto();
					Categoria c= new Categoria();
					LogicCategoria ctrlCat = new LogicCategoria();
					p.setIdProducto(rs.getInt("idProducto"));
					p.setDescripcion(rs.getString("descripcion"));
					p.setPrecio(rs.getFloat("precio"));
					p.setStock(rs.getInt("stock"));
					p.setImg(rs.getString("img"));
					c = ctrlCat.getByIdCategoria(rs.getInt("idcategoria"));
					p.setCat(c);
					prods.add(p);
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
		
		
		return prods;
	}


	public LinkedList<Producto> getPorCategoria(int idCat) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Producto> prods= new LinkedList<>();
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("Select idcategoria, idProducto, descripcion, precio, stock, img from producto WHERE idcategoria=? order by descripcion");
			stmt.setInt(1,idCat);
			rs = stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Producto p=new Producto();
					Categoria c= new Categoria();
					LogicCategoria ctrlCat = new LogicCategoria();
					p.setIdProducto(rs.getInt("idProducto"));
					p.setDescripcion(rs.getString("descripcion"));
					p.setPrecio(rs.getFloat("precio"));
					p.setStock(rs.getInt("stock"));
					p.setImg(rs.getString("img"));
					c = ctrlCat.getByIdCategoria(rs.getInt("idcategoria"));
					p.setCat(c);
					prods.add(p);
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
		
		
		return prods;
	}


	
}