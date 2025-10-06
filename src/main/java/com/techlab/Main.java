/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.techlab;

import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.pedidos.Pedido;
import com.techlab.productos.Producto;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author matias-bruno
 */
public class Main {
    // El arreglo con los productos
    private static ArrayList<Producto> productos;
    // El arreglo con los pedidos
    private static ArrayList<Pedido> pedidos;
    
    private static void agregarProductos() {
        productos.add(new Producto("Cafe Morenita", 5000, 5));
        productos.add(new Producto("Te Virginia", 1000, 10));
        productos.add(new Producto("Leche Serenisima", 1500, 10));
        productos.add(new Producto("Lavandina Ayudin", 1000, 10));
        productos.add(new Producto("Desodorante Axe", 2000, 10));
    }
    
    private static  void ingresarProducto() {
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
    
    private static Producto buscarProductoPorIndice() {
        Scanner in = new Scanner(System.in);
        int index;
        System.out.print("Ingrese indice del producto: ");
        index = in.nextInt();
        if(index >= 0 && index < productos.size())
            return productos.get(index);
        return null;
    }
    
    private static Producto buscarProductoPorNombre() {
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
    
    private static boolean borrarProducto(Producto producto) {
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
    private static void ingresarPedido() {
        Scanner in = new Scanner(System.in);
        String respuesta;
        boolean opcionValida;
        Pedido pedido = new Pedido();
        do {
            Producto producto = buscarProductoPorIndice();
            if(producto != null) {
                System.out.println("Producto: " + producto.getNombre());
                System.out.print("Ingrese cantidad: ");
                int cantidad = in.nextInt();
                try {
                    pedido.agregarProducto(producto, cantidad);
                } catch(StockInsuficienteException ex) {
                    System.out.println(ex.getMessage());
                }
                in.nextLine();
            } else {
                System.out.println("El producto no se encontro");
            }
            do {
                opcionValida = true;
                System.out.print("Desea ingresar otro producto? (s/n): ");
                respuesta = in.nextLine();
                if(!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n")) {
                    opcionValida = false;
                    System.out.println("Opcion incorrecta");
                }
            } while(!opcionValida);
        } while(respuesta.equalsIgnoreCase("s"));
        if(!pedido.getLineasPedido().isEmpty()) {
            System.out.println("\nSu pedido ha sido confirmado");
            pedidos.add(pedido);
        } else {
            System.out.println("\nNo se agrego pedido porque no habia productos");
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
    public static void main(String[] args) {
        productos = new ArrayList<>();
        pedidos = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int option;
        String optionStr;
        Producto producto;
        agregarProductos();
        do {
            System.out.println("\n1. Ingresar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar producto por indice");
            System.out.println("4. Eliminar producto por indice");
            System.out.println("5. Crear pedido");
            System.out.println("6. Listar pedidos");
            System.out.println("0. Salir");
            optionStr = in.nextLine();
            try {
                option = Integer.parseInt(optionStr);
            } catch(NumberFormatException e) {
                option = -1;
            }
            switch(option) {
                case 0:
                    break;
                case 1:
                    ingresarProducto();
                    break;
                case 2:
                    if(!productos.isEmpty()) {
                        for(int i = 0; i < productos.size(); ++i) {
                            System.out.println("Producto indice: " + i);
                            productos.get(i).mostrar();
                        }
                    } else {
                        System.out.println("\nNo se han ingresado productos");
                    }
                    break;
                case 3:
                    producto = buscarProductoPorIndice();
                    //producto = buscarProductoPorNombre();
                    if(producto == null) {
                        System.out.println("El indice no se encontro");
                    } else {
                        producto.mostrar();
                    }
                    break;
                case 4:
                    producto = buscarProductoPorIndice();
                    if(producto == null) {
                        System.out.println("El indice no se encontro");
                    } else {
                        borrarProducto(producto);
                    }
                    break;
                case 5:
                    ingresarPedido();
                    break;
                case 6:
                    if(!pedidos.isEmpty()) {
                        for(int i = 0; i < pedidos.size(); ++i) {
                            System.out.println("Pedido indice: " + i);
                            pedidos.get(i).mostrar();
                        }
                    } else {
                        System.out.println("\nNo se han ingresado pedidos");
                    }
                    break;
                default:
                    System.out.println("Opcion incorrecta, ingrese un numero de opcion de la lista");
            }
        } while(option != 0);
    }
}
