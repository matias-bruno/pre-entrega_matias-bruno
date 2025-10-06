/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techlab.pedidos;

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
    public void agregarProducto(Producto producto, int cantidad) throws StockInsuficienteException {
        if(cantidad > producto.getStock())
            throw new StockInsuficienteException("El stock es insuficiente");
        producto.descontarStock(cantidad);
        LineaPedido lineaPedido = new LineaPedido(producto, cantidad);
        lineasPedido.add(lineaPedido);
    }
//    public boolean quitarProducto(Producto producto) {
//        int pos = this.lineasPedido.indexOf(producto);
//        if(pos != -1) {
//            this.lineasPedido.remove(pos);
//            return true;
//        }
//        return false;
//    }
    public void mostrar() {
        System.out.println();
        for(LineaPedido lineaPedido : lineasPedido) {
            System.out.println("Nombre: " + lineaPedido.getProducto().getNombre());
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
