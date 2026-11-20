package com.pedidos360.pedidos360_mapa.controller;

import com.pedidos360.pedidos360_mapa.service.UbicacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/ubicaciones")
@CrossOrigin(origins = "*")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    public UbicacionController(UbicacionService ubicacionService) {
        this.ubicacionService = ubicacionService;
    }

    @GetMapping("/sucursales")
    public List<java.util.Map<String, Object>> listarSucursales() {
        return ubicacionService.listarSucursales();
    }

    @GetMapping("/tracking/{idPedido}")
    public Map<String, Object> obtenerTracking(@PathVariable String idPedido) {
        return ubicacionService.obtenerTracking(idPedido);
    }
}