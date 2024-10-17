package br.com.desafio.tabelaFipe.service;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public interface iConverteDados {
    <T> T obterDados(String json, Class<T> tClass);

    <T>List<T> obterDadosLista(String json, Class<T> tClass);
}

