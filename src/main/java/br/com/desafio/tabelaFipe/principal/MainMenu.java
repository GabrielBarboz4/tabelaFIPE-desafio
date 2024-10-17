package br.com.desafio.tabelaFipe.principal;

import br.com.desafio.tabelaFipe.model.DadosMarcas;
import br.com.desafio.tabelaFipe.model.DadosModelos;
import br.com.desafio.tabelaFipe.model.Veiculo;
import br.com.desafio.tabelaFipe.service.ConsumoAPI;
import br.com.desafio.tabelaFipe.service.ConverteDados;

import java.util.*;

public class MainMenu {
    private final Scanner in = new Scanner(System.in);
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConverteDados conversor = new ConverteDados();
    private String urlBusca;

    public void exibeMenu() {
        System.out.println("Bem vindo ao ConsultaFipeAE");


        while (true) {
            System.out.print("""
                    Digite em qual das categorias abaixo você deseja consultar
                    1 - Carros
                    2 - Motos
                    3 - Caminhões
                    0 - Encerrar o sistema
                    """ + " --> ");
            int escolha = in.nextInt();
            in.nextLine();
            System.out.println();

            String apiURL = "https://parallelum.com.br/fipe/api/v1/";

            switch (escolha) {
                case 1:
                    urlBusca = apiURL + "carros/marcas";
                    bucasDeDados();
                    break;
                case 2:
                    urlBusca =  apiURL + "motos/marcas";
                    bucasDeDados();
                    break;
                case 3:
                    urlBusca =  apiURL + "caminhoes/marcas";
                    bucasDeDados();
                    break;
                case 0:
                    System.out.println("Sistema finalizado com sucesso");
                    in.close();
                    System.exit(0);
                default:
                    System.out.println("Valor digitado é inválido");
                    System.out.println();
            }
        }
    }

    private void bucasDeDados() {
        String json = consumoAPI.obterDados(urlBusca);

        System.out.println(json);

        List <DadosMarcas> listaMarcas = conversor.obterDadosLista(json, DadosMarcas.class);
        listaMarcas.stream()
                .sorted(Comparator.comparing(DadosMarcas::codigo))
                .forEach(t -> System.out.println("Código: " + t.codigo() + "\nNome: " + t.nomeDaMarca() + "\n----------"));

        System.out.println("\nQual a marca que você deseja buscar? Digite o código escolhido");
        int escolhaMarca = in.nextInt();
        in.nextLine();

        urlBusca = urlBusca + "/" + escolhaMarca + "/modelos";

        json = consumoAPI.obterDados(urlBusca);

        System.out.println(json);

        var modelosLista = conversor.obterDados(json, DadosModelos.class);

        System.out.println("\nModelos disponíveis\n");
        modelosLista.modelos().stream()
                .sorted(Comparator.comparing(DadosMarcas::codigo))
                .forEach(t -> System.out.println("Código: " + t.codigo() + "\nNome: " + t.nomeDaMarca() + "\n----------"));

        System.out.println("Digite o nome do veículo que você deseja buscar");
        var buscaVeiculo = in.nextLine();


        List<DadosMarcas> modelosFiltrados = modelosLista.modelos().stream()
                .filter(t -> t.nomeDaMarca().toLowerCase().contains(buscaVeiculo.toLowerCase()))
                .toList();

        System.out.println();
        System.out.println("\nModelos encontrados\n");
        modelosFiltrados.forEach(dados -> System.out.printf("\nCódigo: %s \nMarca: %s \n----------",
                dados.codigo(), dados.nomeDaMarca()));
        System.out.println();

        System.out.println("Digite o código do modelo que você deseja saber o valor da FIPE");
        var codVeiculo = in.nextInt();
        in.nextLine();

        urlBusca = urlBusca + "/" + codVeiculo + "/anos";
        json = consumoAPI.obterDados(urlBusca);
        List<DadosMarcas> anos = conversor.obterDadosLista(json, DadosMarcas.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (DadosMarcas ano : anos) {
            var enderecoAnos = urlBusca + "/" + ano.codigo();
            json = consumoAPI.obterDados(enderecoAnos);
            Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("\nTodos os veículos filtrados com avaliações por ano: \n");
        veiculos.forEach(System.out::println);
        System.out.println();
    }
}
