/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techlab.pedidos;

import com.techlab.excepciones.NonPositiveNumberException;
import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.productos.Producto;
import com.techlab.productos.ProductoService;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author matias-bruno
 */
public class PedidoService {
    // El arreglo con los pedidos
    private final ArrayList<Pedido> pedidos;
    
    // Constructor
    public PedidoService() {
        this.pedidos = new ArrayList<>();
    }
    
    public void ingresarPedido(ProductoService productoService) {
        Scanner in = new Scanner(System.in);
        String respuesta;
        boolean opcionValida;
        Pedido pedido = new Pedido();
        do {
            Producto producto = productoService.buscarProductoPorId();
            if(producto != null) {
                System.out.println("Producto: " + producto.getNombre());
                System.out.print("Ingrese cantidad: ");
                int cantidad = in.nextInt();
                try {
                    pedido.agregarProducto(producto, cantidad);
                } catch(StockInsuficienteException | NonPositiveNumberException ex) {
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
        if(pedido.getSize() > 0) {
            System.out.println("\nSu pedido ha sido confirmado");
            pedidos.add(pedido);
        } else {
            System.out.println("\nNo se agrego pedido porque no habia productos");
        }
    }
    public void mostrarPedidos() {
        if(!pedidos.isEmpty()) {
            System.out.println("\nLista de pedidos");
            for(int i = 0; i < pedidos.size(); ++i) {
                pedidos.get(i).mostrar();
            }
        } else {
            System.out.println("\nNo se han ingresado pedidos");
        }
    }
}
