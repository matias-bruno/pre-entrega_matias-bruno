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
        nombreProducto = formatearNombre(nombreProducto);
        System.out.print("Ingrese precio: ");
        precioProducto = in.nextDouble();
        System.out.print("Ingrese cantidad en stock: ");
        cantidadEnStock = in.nextInt();
        Producto producto = new Producto(nombreProducto, precioProducto, cantidadEnStock);
        productos.add(producto);
    }
    public Producto buscarProductoPorIndice() {
        Scanner in = new Scanner(System.in);
        int index;
        System.out.print("Ingrese indice del producto: ");
        index = in.nextInt();
        if(index >= 0 && index < productos.size())
            return productos.get(index);
        return null;
    }
    public Producto buscarProductoPorNombre() {
        Scanner in = new Scanner(System.in);
        String nombre;
        System.out.println("Ingrese nombre del producto");
        nombre = in.nextLine();
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
            for(int i = 0; i < productos.size(); ++i) {
                System.out.println("Producto indice: " + i);
                productos.get(i).mostrar();
            }
        } else {
            System.out.println("\nNo se han ingresado productos");
        }
    }
    // Metodo auxiliar para nombres de productos
    private static String formatearNombre(String nombre) {
        String[] palabras = nombre.split(" ");
        StringBuilder sb = new StringBuilder();
        for(String palabra : palabras) {
            sb.append(palabra.substring(0,1).toUpperCase());
            sb.append(palabra.substring(1).toLowerCase());
            sb.append(" ");
        }
        return sb.toString().trim();
    }
    
}
