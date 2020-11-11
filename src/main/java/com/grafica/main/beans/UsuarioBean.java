package com.grafica.main.beans;

import com.csvreader.CsvReader;
import com.grafica.main.dao.UsuarioFacadeLocal;
import com.grafica.main.model.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    private Usuario usuario;
    
    private UploadedFile file;
    
    @PostConstruct
    private void init(){
        usuario = new Usuario();
    }
    
    
    public void realizarCargueUsuarios() throws IOException{
        System.out.println("Se realizara el cargue de usuarios!");
        
        if (!file.getFileName().equals("")) {
            System.out.println("Cargue. el nombre del archivo es: "+file.getFileName());
            InputStream input = file.getInputstream();
            
            //CsvReader reader = new CsvReader(input,null);
            CsvReader reader = new CsvReader(new InputStreamReader(input, "utf-8"));
            reader.readHeaders();
            
            while(reader.readRecord()) {
                
                Usuario user = new Usuario();
                user.setNombre(reader.get(0));
                user.setTelefono(reader.get(1));
                user.setEmail(reader.get(2));
                user.setUsuario(reader.get(3));
                user.setContrasena(reader.get(4));                
                
                usuarioEJB.create(user);
                
            }
        }else{
            System.out.println("Debe seleccionar un archivo.");
        }
        
    }  
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    
}
