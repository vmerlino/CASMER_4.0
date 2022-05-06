package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import Entities.Producto;
import Logic.LogicProducto;
import Entities.LineaDePedido;
import Entities.Pedido;

public class DataLdp {
	LinkedList<Producto> productosUsados= new LinkedList<Producto>();
	 LinkedList<LineaDePedido> listaLdp = new LinkedList<LineaDePedido>();
	public LinkedList<LineaDePedido> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<LineaDePedido> ldp= new LinkedList<>();
	
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select idProducto,cantidad,subTotal from lineadepedido");
			//intencionalmente no se recupera la password
			if(rs!=null) {
				while(rs.next()) {
					LineaDePedido lp=new LineaDePedido();
					Producto prod=new Producto();
					LogicProducto ctrlProd = new LogicProducto();
					prod.setIdProducto(rs.getInt("idProducto"));
					prod = ctrlProd.getByIdProducto(prod);
					lp.setCant(rs.getInt("cantidad"));
					lp.setSubTot(rs.getFloat("subTotal"));
					lp.setProd(prod);
					ldp.add(lp);
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
		
		return ldp;
	}

	public void delete(int idProducto, int idPedido) {
		PreparedStatement stmt=null;
		try 
		{
			stmt=DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM lineadepedido WHERE idproducto=? and idpedido=?");
			stmt.setInt(1,idProducto);
			stmt.setInt(2,idPedido);
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

	public void add(LineaDePedido ldp,int idPedido) {
		PreparedStatement stmt= null;
		try {
			
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into lineadepedido(idProducto, cantidad, subtotal, idpedido) values(?, ?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, ldp.getProd().getIdProducto());
			stmt.setInt(2, ldp.getCant());
			stmt.setDouble(3, ldp.getSubTot());
			stmt.setInt(4,idPedido);
			stmt.executeUpdate();
			
		}  catch (SQLException e) {
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

	public LinkedList<LineaDePedido> getLineasDelPedido(int idPedido) {
		LinkedList<LineaDePedido> ldp= new LinkedList<>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			
			stmt=DbConnector.getInstancia().getConn().prepareStatement("select idProducto,cantidad,subTotal from lineadepedido where idpedido= ?");
			stmt.setInt(1,idPedido);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					LineaDePedido lp=new LineaDePedido();
					Producto prod=new Producto();
					LogicProducto ctrlProd = new LogicProducto();
					prod.setIdProducto(rs.getInt("idProducto"));
					prod = ctrlProd.getByIdProducto(prod);
					lp.setCant(rs.getInt("cantidad"));
					lp.setSubTot(rs.getFloat("subTotal"));
					lp.setProd(prod);
					ldp.add(lp);
				}
			
		}}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		return ldp;
	}

	public void update(LineaDePedido linea, Pedido ped) {
		PreparedStatement stmt = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("UPDATE lineadepedido set subTotal=?, cantidad=?  WHERE idpedido=? AND idproducto=? ");
			stmt.setDouble(1, linea.getSubTot());
			stmt.setInt(2, linea.getCant());
			stmt.setInt(3, ped.getIdPedido());
			stmt.setInt(4, linea.getProd().getIdProducto());
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

	public LineaDePedido getOne(int idProducto, int idPedido) {
		PreparedStatement stmt=null;
		LineaDePedido lp=new LineaDePedido();
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement("select idProducto,cantidad,subTotal,idpedido from lineadepedido where idProducto=? and idpedido=?");
			stmt.setInt(1,idProducto);
			stmt.setInt(2,idPedido);
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				Producto prod=new Producto();
				LogicProducto ctrlProd = new LogicProducto();
				prod.setIdProducto(rs.getInt("idProducto"));
				prod = ctrlProd.getByIdProducto(prod);
				lp.setCant(rs.getInt("cantidad"));
				lp.setSubTot(rs.getFloat("subTotal"));
				lp.setProd(prod);}
					
		}}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		return lp;
	}
}
