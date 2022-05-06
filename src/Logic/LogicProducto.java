package Logic;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.Part;

import Data.DataProducto;
import Entities.Producto;

public class LogicProducto {
	
	private DataProducto dp;
	
	public LinkedList<Producto> getAll() throws IOException{
		dp = new DataProducto();
		return dp.getAll();
	}
	
	public Producto getByIdProducto(Producto prod) {
		dp=new DataProducto();
		return dp.getByIdProducto(prod);
	}
	
	public void add(Producto prod) throws IOException {
		dp=new DataProducto();
		dp.add(prod);
	}
	
	public void delete(Producto prod) {
		dp=new DataProducto();
		dp.delete(prod);
	}

	public void edit(Producto prod) {
		dp = new DataProducto();
		dp.edit(prod);
		
	}
	
	
	
	public LinkedList<Producto> getByDescripcion(Producto prod){
		dp=new DataProducto();
		return dp.getByDescripcion(prod);
	}
	
	public LinkedList<Producto> getSinStock(){
		dp=new DataProducto();
		return dp.getSinStock();
	}

	public LinkedList<Producto> getPorCategoria(int idCat) {
		dp=new DataProducto();
		return dp.getPorCategoria(idCat);
	}
	
	public Image ConvertirImagen(byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis; // File or InputStream
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        return reader.read(0, param);
    }
}
