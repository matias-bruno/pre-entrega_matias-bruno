/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.techlab;

import com.techlab.pedidos.PedidoService;
import com.techlab.productos.Producto;
import com.techlab.productos.ProductoService;
import java.util.Scanner;

/**
 *
 * @author matias-bruno
 */
public class Main {
    public static void main(String[] args) {
        ProductoService productoService = new ProductoService();
        PedidoService pedidoService = new PedidoService();
        Scanner in = new Scanner(System.in);
        int option;
        String optionStr;
        Producto producto;
        productoService.agregarProductos();
        do {
            System.out.println("\n1. Ingresar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Actualizar producto por id");
            System.out.println("4. Eliminar producto por id");
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
                    productoService.ingresarProducto();
                    break;
                case 2:
                    productoService.mostrarProductos();
                    break;
                case 3:
                    producto = productoService.buscarProductoPorId();
                    //producto = productoService.buscarProductoPorNombre();
                    if(producto == null) {
                        System.out.println("El producto no se encontro");
                    } else {
                        productoService.actualizarProducto(producto);
                    }
                    break;
                case 4:
                    producto = productoService.buscarProductoPorId();
                    if(producto == null) {
                        System.out.println("El producto no se encontro");
                    } else {
                        productoService.borrarProducto(producto);
                    }
                    break;
                case 5:
                    pedidoService.ingresarPedido(productoService);
                    break;
                case 6:
                    pedidoService.mostrarPedidos();
                    break;
                default:
                    System.out.println("Opcion incorrecta, ingrese un numero de opcion de la lista");
            }
        } while(option != 0);
    }
}
