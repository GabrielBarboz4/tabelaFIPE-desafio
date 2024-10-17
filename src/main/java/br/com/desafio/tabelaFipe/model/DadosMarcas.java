package br.com.desafio.tabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public record DadosMarcas(
   @JsonAlias ("codigoModelo") String codigo,
   @JsonAlias ("nome") String nomeDaMarca
) {}
