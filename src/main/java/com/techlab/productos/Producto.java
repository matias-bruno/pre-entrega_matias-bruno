/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techlab.productos;

/**
 *
 * @author matias-bruno
 */
public class Producto {
    // Atributos privados
    private String nombre;
    private double  precio;
    private int stock;
    
    private static int cantidad;
    
    // Constructor
    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio >= 0 ? precio : 0;
        this.stock = stock >= 0 ? stock : 0;
        
        Producto.cantidad++;
    }
    
    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio >= 0 ? precio : 0;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock >= 0 ? stock : 0;
    }
    public void agregarStock(int cantidad) {
        this.stock += cantidad;
    }
    public void descontarStock(int cantidad) {
        if(cantidad <= this.stock)
            this.stock -= cantidad;
    }
    public static int getCantidad() {
        return Producto.cantidad;
    }
    
    public void mostrar() {
        System.out.println("\nNombre: " + this.nombre);
        System.out.println("Precio: " + this.precio);
        System.out.println("Cantidad en stock: " + this.stock + "\n");
    }
}
