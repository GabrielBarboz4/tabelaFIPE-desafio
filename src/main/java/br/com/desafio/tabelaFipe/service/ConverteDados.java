package br.com.desafio.tabelaFipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados implements iConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obterDadosLista(String json, Class<T> tClass) {
        CollectionType lista = mapper.getTypeFactory()
                .constructCollectionType(List.class, tClass);
        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
