package exercicio01;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Conta {

	private Long id;
	private String agencia;
	private String numeroConta;
	private String digitoVerificador;
	private BigDecimal saldo;
	private Date dataHoraAbertura;
	private Date dataHoraEncerramento;
	private List<Transacao> transacoes;
	
	public List<Transacao> listaTransacoesCreditoComStream(){
		return transacoes.stream()
				.filter(p -> p.getOperacao().equals(Operacao.CREDITO))
                .collect(Collectors.toList());
	}
	
	public List<Transacao> listaTransacoesDebitoComStream(){
		return transacoes.stream()
				.filter(p -> p.getOperacao().equals(Operacao.CREDITO))
                .collect(Collectors.toList());
	}

	public void listaTransacoesAgrupadasPortTipo(){
		// Agrupamento por soma dos precos dos lotes
		Map<Operacao, List<Transacao>> groupByOperacao = 
				transacoes.stream().collect(Collectors.groupingBy(Transacao::getOperacao));
		System.out.println(groupByOperacao);
    }
	
	
	public Conta() {
		
	}
	
	public Conta(Long id, String agencia, String numeroConta, String digitoVerificador, 
			Date dataHoraAbertura, List<Transacao> transacoes) {
		super();
		this.id = id;
		this.agencia = agencia;
		this.numeroConta = numeroConta;
		this.digitoVerificador = digitoVerificador;
		this.dataHoraAbertura = dataHoraAbertura;
		this.transacoes = transacoes;
	}

	


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	public String getDigitoVerificador() {
		return digitoVerificador;
	}
	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}
	public BigDecimal getSaldo() {
		saldo = new BigDecimal(0.0);
		for (Transacao transacao: transacoes) {
			if (transacao.getOperacao().equals(Operacao.CREDITO)) {
				saldo = saldo.add(transacao.getValor());
			} else {
				saldo = saldo.subtract(transacao.getValor());
			}
		}
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public Date getDataHoraAbertura() {
		return dataHoraAbertura;
	}
	public void setDataHoraAbertura(Date dataHoraAbertura) {
		this.dataHoraAbertura = dataHoraAbertura;
	}
	public Date getDataHoraEncerramento() {
		return dataHoraEncerramento;
	}
	public void setDataHoraEncerramento(Date dataHoraEncerramento) {
		this.dataHoraEncerramento = dataHoraEncerramento;
	}
	public List<Transacao> getTransacoes() {
		return transacoes;
	}
	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
		result = prime * result + ((dataHoraAbertura == null) ? 0 : dataHoraAbertura.hashCode());
		result = prime * result + ((dataHoraEncerramento == null) ? 0 : dataHoraEncerramento.hashCode());
		result = prime * result + ((digitoVerificador == null) ? 0 : digitoVerificador.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numeroConta == null) ? 0 : numeroConta.hashCode());
		result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (agencia == null) {
			if (other.agencia != null)
				return false;
		} else if (!agencia.equals(other.agencia))
			return false;
		if (dataHoraAbertura == null) {
			if (other.dataHoraAbertura != null)
				return false;
		} else if (!dataHoraAbertura.equals(other.dataHoraAbertura))
			return false;
		if (dataHoraEncerramento == null) {
			if (other.dataHoraEncerramento != null)
				return false;
		} else if (!dataHoraEncerramento.equals(other.dataHoraEncerramento))
			return false;
		if (digitoVerificador == null) {
			if (other.digitoVerificador != null)
				return false;
		} else if (!digitoVerificador.equals(other.digitoVerificador))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numeroConta == null) {
			if (other.numeroConta != null)
				return false;
		} else if (!numeroConta.equals(other.numeroConta))
			return false;
		if (saldo == null) {
			if (other.saldo != null)
				return false;
		} else if (!saldo.equals(other.saldo))
			return false;
		return true;
	}

	public void imprime() {
		System.out.println("Conta [id=" + id + ", agencia=" + agencia + ", numeroConta=" + numeroConta + ", digitoVerificador="
				+ digitoVerificador + ", saldo=" + saldo + ", dataHoraAbertura=" + dataHoraAbertura
				+ ", dataHoraEncerramento=" + dataHoraEncerramento + "]");
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", agencia=" + agencia + ", numeroConta=" + numeroConta + ", digitoVerificador="
				+ digitoVerificador + ", saldo=" + saldo + ", dataHoraAbertura=" + dataHoraAbertura
				+ ", dataHoraEncerramento=" + dataHoraEncerramento + "]";
	}
	
	
	
	
}
