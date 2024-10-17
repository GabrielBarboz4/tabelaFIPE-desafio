package br.com.desafio.tabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties (ignoreUnknown = true)
public record DadosModelos(List<DadosMarcas> modelos) {}