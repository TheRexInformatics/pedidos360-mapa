package com.pedidos360.pedidos360_mapa.service;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UbicacionService {

    private final Map<String, Map<String, Object>> despachosActivos = new ConcurrentHashMap<>();

    public List<java.util.Map<String, Object>> listarSucursales() {
        return List.of(
            Map.of(
                "id", "SUC-01",
                "nombre", "Pedidos360 Sede Concepción Centro",
                "direccion", "Paicaví 3280, Concepción",
                "horario", "09:00 - 22:00 hrs",
                "lat", -36.8169,
                "lng", -73.0478
            ),
            Map.of(
                "id", "SUC-02",
                "nombre", "Pedidos360 Campus San Andrés",
                "direccion", "Alonso de Ribera 2850, Concepción",
                "horario", "08:30 - 21:30 hrs",
                "lat", -36.7952,
                "lng", -73.0628
            ),
            Map.of(
                "id", "SUC-03",
                "nombre", "Pedidos360 Talcahuano / El Trébol",
                "direccion", "Av. Jorge Alessandri 3177, Talcahuano",
                "horario", "10:00 - 22:30 hrs",
                "lat", -36.7885,
                "lng", -73.0684
            )
        );
    }

    public void registrarDespacho(String idPedido, String estado) {
        Map<String, Object> tracking = new HashMap<>();
        tracking.put("idPedido", idPedido);
        tracking.put("estadoEnvio", estado);
        tracking.put("repartidor", "Carlos Mendoza (Moto #14)");
        tracking.put("tiempoEstimadoMin", 12);
        tracking.put("origen", Map.of("lat", -36.8169, "lng", -73.0478, "nombre", "Sede Concepción Centro"));
        tracking.put("repartidorPos", Map.of("lat", -36.8215, "lng", -73.0492));
        tracking.put("destino", Map.of("lat", -36.8270, "lng", -73.0503, "nombre", "Dirección del Cliente"));

        despachosActivos.put(idPedido, tracking);
    }

    public Map<String, Object> obtenerTracking(String idPedido) {
        if (!despachosActivos.containsKey(idPedido)) {
            registrarDespacho(idPedido, "EN_CAMINO");
        }
        return despachosActivos.get(idPedido);
    }
}