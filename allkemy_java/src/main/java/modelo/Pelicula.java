/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Image;
import java.util.Date;

/**
 *
 * @author tomas
 */
public class Pelicula {
    private Integer idpel;
    private String titulo;
    private Date fecha;
    private Integer calif;
    private Integer idper;
    private Image imagen;

    public Pelicula(Integer idpel, String titulo, Date fecha, Integer calif, Integer idper, Image imagen) {
        this.idpel = idpel;
        this.titulo = titulo;
        this.fecha = fecha;
        this.calif = calif;
        this.idper = idper;
        this.imagen = imagen;
    }

    public Integer getIdpel() {
        return idpel;
    }

    public void setIdpel(Integer idpel) {
        this.idpel = idpel;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCalif() {
        return calif;
    }

    public void setCalif(Integer calif) {
        this.calif = calif;
    }

    public Integer getIdper() {
        return idper;
    }

    public void setIdper(Integer idper) {
        this.idper = idper;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
    
    
    
    
}
