/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techlab.productos;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author matias-bruno
 */
public class ProductoService {
    // El arreglo con los productos
    private final ArrayList<Producto> productos;
    
    // Constructor
    public ProductoService() {
        this.productos = new ArrayList<>();
    }
    // Getter
    public ArrayList<Producto> getProductos() {
        return productos;
    }
    
    // Metodo para tener productos ingresados
    public void agregarProductos() {
        productos.add(new Producto("Cafe Morenita", 5000, 5));
        productos.add(new Producto("Te Virginia", 1000, 10));
        productos.add(new Producto("Leche Serenisima", 1500, 10));
        productos.add(new Producto("Lavandina Ayudin", 1000, 10));
        productos.add(new Producto("Desodorante Axe", 2000, 10));
    }
    // Ingresar un producto por teclado
    public void ingresarProducto() {
        Scanner in = new Scanner(System.in);
        String nombreProducto;
        double precioProducto;
        int cantidadEnStock;
        System.out.print("Ingrese nombre producto: ");
        nombreProducto = in.nextLine();
        nombreProducto = this.formatearNombre(nombreProducto);
        System.out.print("Ingrese precio: ");
        precioProducto = in.nextDouble();
        System.out.print("Ingrese cantidad en stock: ");
        cantidadEnStock = in.nextInt();
        if(productoValido(nombreProducto, precioProducto, cantidadEnStock)) {
            Producto producto = new Producto(nombreProducto, precioProducto, cantidadEnStock);
            productos.add(producto);
        } else {
            System.out.println("No se pudo ingresar el producto porque contenia datos no validos");
        }
    }
    public Producto buscarProductoPorId() {
        Scanner in = new Scanner(System.in);
        int id;
        System.out.println("Ingrese id del producto");
        id = in.nextInt();
        for(Producto producto : productos) {
            if(producto.getId() == id)
                return producto;
        }
        return null;
    }
    public Producto buscarProductoPorNombre() {
        Scanner in = new Scanner(System.in);
        String nombre;
        System.out.println("Ingrese nombre del producto");
        nombre = this.formatearNombre(in.nextLine());
        for(Producto producto : productos) {
            if(producto.getNombre().equalsIgnoreCase(nombre))
                return producto;
        }
        return null;
    }
    public boolean borrarProducto(Producto producto) {
        Scanner in = new Scanner(System.in);
        String respuesta;
        System.out.println("Se va a eliminar el siguiente producto:");
        producto.mostrar();
        do {
            System.out.print("Desea continuar? (s/n): ");
            respuesta = in.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                productos.remove(producto);
                System.out.println("Producto eliminado");
                return true;
            } else if(!respuesta.equalsIgnoreCase("n")){
                System.out.println("Opcion incorrecta");
            }
        } while(!respuesta.equalsIgnoreCase("n"));
        System.out.println("Se cancelo la eliminacion del producto");
        return false;
    }
    public void mostrarProductos() {
        if(!productos.isEmpty()) {
            System.out.println("\nLista de productos");
            for(int i = 0; i < productos.size(); ++i) {
                productos.get(i).mostrar();
            }
        } else {
            System.out.println("\nNo se han ingresado productos");
        }
    }
    // Metodo auxiliar para nombres de productos
    public String formatearNombre(String nombre) {
        String[] palabras = nombre.split(" ");
        StringBuilder sb = new StringBuilder();
        for(String palabra : palabras) {
            sb.append(palabra.substring(0,1).toUpperCase());
            sb.append(palabra.substring(1).toLowerCase());
            sb.append(" ");
        }
        return sb.toString().trim();
    }
    public boolean productoValido(String nombre, double precio, int cantidad) {
        boolean valido = true;
        if(nombre.length() < 3) {
            System.out.println("El nombre debe contener al menos 3 letras");
            valido = false;
        }
        if(precio <= 0) {
            System.out.println("El precio debe ser un numero positivo");
            valido = false;
        }
        if(cantidad < 0) {
            System.out.println("La cantidad no puede ser negativa");
            valido = false;
        }
        return valido;
    }
}
