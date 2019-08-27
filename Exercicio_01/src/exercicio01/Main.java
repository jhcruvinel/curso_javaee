package exercicio01;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	private static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	public static Banco criaEPopulaBanco() {
		try {
			List<Transacao> cli_1_tr = Arrays.asList(
	                new Transacao(1L, dtf.parse("30/07/2019 10:00"), Operacao.CREDITO, new BigDecimal(100.0), "Deposito inicial"),
	                new Transacao(1L, dtf.parse("01/08/2019 09:00"), Operacao.CREDITO, new BigDecimal(10000.0), "Salario"),
	                new Transacao(1L, dtf.parse("05/08/2019 07:35"), Operacao.DEBITO, new BigDecimal(500.0), "Compra cartao"),
	                new Transacao(1L, dtf.parse("07/08/2019 14:23"), Operacao.DEBITO, new BigDecimal(1350.0), "Pagto Conta"),
	                new Transacao(1L, dtf.parse("15/08/2019 18:20"), Operacao.DEBITO, new BigDecimal(124.0), "Pgto Conta"),
	                new Transacao(1L, dtf.parse("17/08/2019 11:20"), Operacao.DEBITO, new BigDecimal(1200.0), "Compra cheque")
	        );
			List<Conta> cli_1_ct = Arrays.asList(
					new Conta(1L, "1234", "1111", "1", dtf.parse("30/07/2019 09:00"),cli_1_tr)
	        );
			List<Transacao> cli_2_tr_1 = Arrays.asList(
	                new Transacao(1L, dtf.parse("15/08/2019 09:35"), Operacao.CREDITO, new BigDecimal(1500.0), "Deposito inicial"),
	                new Transacao(1L, dtf.parse("17/08/2019 08:21"), Operacao.CREDITO, new BigDecimal(2500.0), "Deposito cheque"),
	                new Transacao(1L, dtf.parse("18/08/2019 15:35"), Operacao.DEBITO, new BigDecimal(124.0), "Compra cartao")
	        );
			List<Transacao> cli_2_tr_2 = Arrays.asList(
	                new Transacao(1L, dtf.parse("18/08/2019 09:35"), Operacao.CREDITO, new BigDecimal(100.0), "Deposito inicial")
	        );
			List<Conta> cli_2_ct = Arrays.asList(
					new Conta(2L, "1234", "2222", "2", dtf.parse("15/08/2019 09:00"),cli_2_tr_1),
					new Conta(3L, "1234", "2223", "2", dtf.parse("18/08/2019 09:00"),cli_2_tr_2)
	        );
			List<Conta> cli_3_ct = Arrays.asList();
			List<Cliente> clientes = Arrays.asList(
					new Cliente(1L, "Vera", df.parse("01/01/2000"), "111.111.111-11", "vera@gmail.com", cli_1_ct),
					new Cliente(2L, "Maria", df.parse("10/10/1980"), "222.222.222-22", cli_2_ct),
					new Cliente(3L, "João", df.parse("02/07/1970"), "333.333.333-33", "joao@gmail.com", cli_3_ct)
			);
	        return new Banco(1L, "Banco do Brasil", "001", clientes);
		} catch (Exception e) {
			return null;
	    }
    }
	
	
	
	public static void main(String[] args) throws Exception {
		Banco banco = criaEPopulaBanco();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("== Ordena Cliente por Nome (Uso de forEach) ==");
		banco.ordenaClientePorNome().forEach(Cliente::imprime);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("== Imprime Clientes (Uso de Optional) ==");
		Optional<Cliente> c1 = Optional.ofNullable(banco.buscarClientePorId(2L));
		Cliente.imprimeSeEstiverPresente(c1);
		Optional<Cliente> c2 = Optional.ofNullable(banco.buscarClientePorId(6L));
		Cliente.imprimeSeEstiverPresente(c2);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("== Lista Emails de Clientes (Uso de Optional) ==");
		banco.listaEmailsCliente();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("== Listar contas dos Clientes (Uso de Optional) ==");
		banco.getClientes().forEach(
				(p) -> { System.out.println("- " + p.getNome());p.listaContas(); }
				);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("== Listar Saldos das Contas de Clientes (Uso de Stream) ==");
		banco.getClientes().forEach(
				(p) -> { System.out.println("- " + p.getNome()); p.listaSaldosContas(); }
				);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("== Clientes com nome Maria e Data > 1981 (Filtro com Stream) ==");
		Date data = df.parse("31/12/1981");
		Cliente cliente = banco.getClientes().stream()
				.filter((p) -> "Maria".equals(p.getNome()) || data.before(p.getDtNascimento())).findFirst().orElse(null);
		cliente.imprime();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("== Clientes com Saldo de Contas > 5000 (Filtro com Stream) ==");
		Stream<Cliente> streamClientes = banco.getClientes().stream()
				.filter((p) -> (p.getSaldoContas().compareTo(new BigDecimal(5000.0)) > 0));
        List<Cliente> listaClientes = streamClientes.collect(Collectors.toList());
        listaClientes.forEach(Cliente::imprime);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("== Listar apenas nomes dos clientes ==");
		Stream<String> listaNomeClientes = banco.getClientes().stream().map(p -> p.getNome());
        List<String> lista = listaNomeClientes.collect(Collectors.toList());
        System.out.println(lista);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("== Listar apenas Contas dos clientes com mapeamento ==");
		Collector<Cliente, StringJoiner, String> clientesFormatados = 
				Collector.of(() -> new StringJoiner(" | "), // fornecedor
				(j, p) -> j.add(p.getNome().toUpperCase()), // acumulador
				(j1, j2) -> j1.merge(j2), // combinador
				StringJoiner::toString); // finalizador
		System.out.println(banco.getClientes().stream().collect(clientesFormatados));
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("== Listar Transações por cliente separadas pelo tipo ==");
		for (Cliente cli : banco.getClientes()) {
			cli.getContas().get().forEach(Conta::listaTransacoesAgrupadasPortTipo);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("== Listar apenas Contas dos clientes com formatação ==");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("== Listar apenas Contas dos clientes com formatação ==");
		
		
	}

}
