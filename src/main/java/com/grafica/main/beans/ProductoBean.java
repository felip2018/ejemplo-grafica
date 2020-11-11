package com.grafica.main.beans;

import com.grafica.main.dao.ProductoFacadeLocal;
import com.grafica.main.model.Producto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.PieChartModel;

@Named
@ViewScoped
public class ProductoBean implements Serializable{
    
    @EJB
    private ProductoFacadeLocal productoEJB;
    
    private Producto producto;
    
    private List<Producto> lista;
    
    private PieChartModel pieModel;
    
    @PostConstruct
    private void init(){
        lista = productoEJB.findAll();
    }
    
    public void graficar(){
        pieModel = new PieChartModel();
        
        for (Producto prod : lista) {
            pieModel.set(prod.getNombre(), prod.getPrecio());
        }
        
        pieModel.setTitle("Precios");
        pieModel.setLegendPosition("e");
        pieModel.setFill(false);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(150);
    }
    
    // Getters and setters
    
    public PieChartModel getPieModel(){
        return pieModel;
    }
    
    public void setPieModel(PieChartModel pieModel){
        this.pieModel = pieModel;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getLista() {
        return lista;
    }

    public void setLista(List<Producto> lista) {
        this.lista = lista;
    }
    
    
}
