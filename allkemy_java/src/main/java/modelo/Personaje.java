/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Image;

/**
 *
 * @author tomas florio
 */
public class Personaje {
    private Integer id_per;
    private String nombre;
    private Integer edad;
    private Integer peso;
    private String historia;
    private Integer id_pelicula;
    private Image imagen;

    public Personaje(Integer id_per, String nombre, Integer edad, Integer peso, String historia, Integer id_pelicula, Image imagen) {
        this.id_per = id_per;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
        this.id_pelicula = id_pelicula;
        this.imagen = imagen;
    }

    public Integer getId_per() {
        return id_per;
    }

    public void setId_per(Integer id_per) {
        this.id_per = id_per;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public Integer getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(Integer id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }


    
    
}
