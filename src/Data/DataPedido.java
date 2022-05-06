package Data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;

import Entities.Categoria;
import Entities.Pedido;
import Entities.Producto;
import Entities.Usuario;

public class DataPedido {

	public LinkedList<Pedido> getAll() {
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Pedido> ped= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select idpedido,estado,fecha,fechaEntrega, fechaCancel, dniusuario,monto from pedido order by idpedido DESC");
			if(rs!=null) {
				while(rs.next()) {
					Pedido p=new Pedido();
					Usuario u = new Usuario();
					p.setIdPedido(rs.getInt("idpedido"));
					u.setDni(rs.getString("dniusuario"));
					p.setEstado(rs.getString("estado"));
					p.setFechaPedido(rs.getDate("fecha"));
					p.setFechaCancelacion(rs.getDate("fechaCancel"));
					p.setFechaEntrega(rs.getDate("fechaEntrega"));
					p.setUsu(u);
					p.setMonto(rs.getDouble("monto"));
					ped.add(p);
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
		return ped;
	}
	
	public void add(Pedido p) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into pedido (estado,fecha,dniusuario) values(?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, p.getEstado());
			stmt.setDate(2, (Date) p.getFechaPedido());
			stmt.setString(3, p.getUsu().getDni());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setIdPedido(keyResultSet.getInt(1));
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
	
	public void delete(Pedido ped) {
		PreparedStatement stmt=null;
		try 
		{
			stmt=DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM pedido WHERE idpedido= ?");
			stmt.setInt(1,ped.getIdPedido());
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

	public void update(Pedido p) {
		PreparedStatement stmt = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("UPDATE pedido set estado=?, fechaEntrega=?,fechaCancel=?,fecha=?, monto=?   WHERE idpedido=? ");
			stmt.setString(1, p.getEstado());
			stmt.setDate(2,(Date)p.getFechaEntrega());
			stmt.setDate(3,(Date) p.getFechaCancelacion());
			stmt.setDate(4, (Date) p.getFechaPedido());
			stmt.setDouble(5, p.getMonto());
			stmt.setInt(6, p.getIdPedido());
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
	
	public Pedido getOne(Pedido ped) {
		Pedido p=null;
		Usuario u=new Usuario();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idpedido,estado,fecha,fechaEntrega, fechaCancel, dniusuario,monto from pedido where idpedido=? "
					);
			stmt.setInt(1, ped.getIdPedido());
			rs=stmt.executeQuery();
			if(rs.next()) {
				p=new Pedido();
				p.setIdPedido(rs.getInt("idpedido"));
				u.setDni(rs.getString("dniusuario"));
				p.setEstado(rs.getString("estado"));
				p.setFechaPedido(rs.getDate("fecha"));
				p.setFechaCancelacion(rs.getDate("fechaCancel"));
				p.setFechaEntrega(rs.getDate("fechaEntrega"));
				p.setUsu(u);
				p.setMonto(rs.getDouble("monto"));
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

	public LinkedList<Pedido> getPedidoUsuario(Usuario usu) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Pedido> ped= new LinkedList<>();
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idpedido,estado,fecha,fechaEntrega, fechaCancel, dniusuario,monto from pedido where dniusuario=? order by idpedido DESC "
					);
			stmt.setString(1, usu.getDni());
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Pedido p=new Pedido();
					p.setIdPedido(rs.getInt("idpedido"));
					p.setEstado(rs.getString("estado"));
					p.setFechaPedido(rs.getDate("fecha"));
					p.setFechaCancelacion(rs.getDate("fechaCancel"));
					p.setFechaEntrega(rs.getDate("fechaEntrega"));
					p.setUsu(usu);
					p.setMonto(rs.getDouble("monto"));
					ped.add(p);
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
		return ped;
	}
	
	}
