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
    private int id;
    private String nombre;
    private double  precio;
    private int stock;
    
    private static int nextId = 1;
    
    // Constructor
    public Producto(String nombre, double precio, int stock) {
        this.id = Producto.nextId++;
        this.nombre = nombre;
        this.precio = precio >= 0 ? precio : 0;
        this.stock = stock >= 0 ? stock : 0;
    }
    
    // Getters y setters
    public int getId() {
        return this.id;
    }
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio >= 0 ? precio : 0;
    }

    public int getStock() {
        return this.stock;
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
    
    public void mostrar() {
        System.out.println("\nProducto Id: " + this.id);
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Precio: " + String.format("$%.2f",this.precio));
        System.out.println("Cantidad en stock: " + this.stock);
    }
}
