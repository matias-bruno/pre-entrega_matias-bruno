/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techlab.pedidos;

import com.techlab.excepciones.NonPositiveNumberException;
import com.techlab.excepciones.StockInsuficienteException;
import java.util.ArrayList;
import com.techlab.productos.Producto;

/**
 *
 * @author matias-bruno
 */
public class Pedido {
    // Atributos privados
    final private ArrayList<LineaPedido> lineasPedido;
    
    // Constructor
    public Pedido() {
        lineasPedido = new ArrayList<>();
    }
    
    // Getter
    public ArrayList<LineaPedido> getLineasPedido() {
        return lineasPedido;
    }
    
    // Metodos para agregar y quitar
    public void agregarProducto(Producto producto, int cantidad) throws StockInsuficienteException, NonPositiveNumberException {
        if(cantidad > producto.getStock()) {
            throw new StockInsuficienteException("El stock es insuficiente");
        }
        if(cantidad < 1) {
            throw new NonPositiveNumberException("La cantidad debe ser mayor que cero");
        }
        producto.descontarStock(cantidad);
        LineaPedido lineaPedido = new LineaPedido(producto, cantidad);
        lineasPedido.add(lineaPedido);
    }
    // Este metodo hay que probarlo, porque como no se usa, no lo probe
//    public boolean quitarProducto(Producto producto) {
//        for(LineaPedido lineaPedido : lineasPedido) {
//            if(lineaPedido.getProducto().getNombre().equals(producto.getNombre())) {
//                lineasPedido.remove(lineaPedido);
//            }
//        }
//        return false;
//    }
    public void mostrar() {
        for(LineaPedido lineaPedido : lineasPedido) {
            System.out.println("\nNombre: " + lineaPedido.getProducto().getNombre());
            System.out.println("Precio: " + lineaPedido.getProducto().getPrecio());
            System.out.println("Cantidad : " + lineaPedido.getCantidad() + "\n");
        }
        System.out.println("Total: " + this.calcularTotal() + "\n");
    }
    public double calcularTotal() {
        double total = 0;
        for(LineaPedido lineaPedido : lineasPedido) {
            Producto producto = lineaPedido.getProducto();
            total += producto.getPrecio() * lineaPedido.getCantidad();
        }
        return total;
    }
}
