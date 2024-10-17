package br.com.desafio.tabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(
        @JsonAlias("Valor") String valor,
        @JsonAlias("Marca") String marca,
        @JsonAlias("Modelo") String modelo,
        @JsonAlias("AnoModelo") String ano,
        @JsonAlias("Combustivel") String tipoCombustivel
) {

    @Override
    public String toString() {
        return String.format("%s %s \nAno: %s \nValor da FIPE: %s \nCombustível: %s \n----------",
                marca, modelo, ano, valor, tipoCombustivel);
    }
}