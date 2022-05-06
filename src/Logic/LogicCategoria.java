package Logic;

import java.util.LinkedList;
import Data.DataCategoria;
import Data.*;
import Entities.*;

public class LogicCategoria {
	
	private DataCategoria dc;
	
	public LinkedList<Categoria> getAll(){
		dc=new DataCategoria();
		return dc.getAll();
	}
	
	public Categoria getByIdCategoria(int cat) {
		dc=new DataCategoria();
		return dc.getByIdCategoria(cat);
	}
	
	public void add(Categoria cat) {
		dc=new DataCategoria();
		dc.add(cat);
	}
	
	public String delete(Categoria cat) {
		dc=new DataCategoria();
		return dc.delete(cat);
	}

	public void edit(Categoria cat) {
		dc = new DataCategoria();
		dc.edit(cat);
		
	}

	public LinkedList<Categoria> getByDescripcion(Categoria cat){
		dc=new DataCategoria();
		return dc.getByDescripcion(cat);
	}

}
