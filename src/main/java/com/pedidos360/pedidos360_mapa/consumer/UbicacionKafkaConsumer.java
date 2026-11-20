package com.pedidos360.pedidos360_mapa.consumer;

import com.pedidos360.pedidos360_mapa.service.UbicacionService;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UbicacionKafkaConsumer {

    private final UbicacionService ubicacionService;

    public UbicacionKafkaConsumer(UbicacionService ubicacionService) {
        this.ubicacionService = ubicacionService;
    }

    @KafkaListener(topics = {"pedidos-creados", "pagos-procesados"}, groupId = "mapa-group")
    public void escucharEventosPedido(String mensajeJson) {
        try {
            Map<String, Object> payload = JsonParserFactory.getJsonParser().parseMap(mensajeJson);
            String idPedido = String.valueOf(payload.getOrDefault("idPedido", "PED-GENERAL"));
            ubicacionService.registrarDespacho(idPedido, "EN_CAMINO");
            System.out.println("📍 Despacho activado en mapa para el pedido: " + idPedido);
        } catch (Exception e) {
            System.err.println("Error procesando evento en mapa: " + e.getMessage());
        }
    }
}