package exercicio01;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cliente {
	
	private Long id;
	private String nome;
	private Date dtNascimento;
	private String cpf;
	private String email;
	private List<Conta> contas;
	private BigDecimal saldoContas;
	
	public void listaContas(){
		if (getContas().isPresent()) {
			for (Conta conta: getContas().get()) {
				System.out.println(conta);
			}
		} else {
			System.out.println("Nenhuma conta aberta para este cliente");
		}
    }
	
	public void listaSaldosContas(){
		Stream<BigDecimal> listaSaldos = contas.stream().map(c -> c.getSaldo());
        List<BigDecimal> lista = listaSaldos.collect(Collectors.toList());
        lista.forEach(p -> System.out.println(p));
    }
	
	public void listarTodasTransacoes() {
		contas.forEach((p) -> p.listaTransacoesAgrupadasPortTipo());
	}
	
	public Cliente() {
		
	}
	
	public Cliente(Long id, String nome, Date dtNascimento, String cpf, List<Conta> contas) {
		super();
		this.id = id;
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.cpf = cpf;
		this.contas = contas;
	}




	public Cliente(Long id, String nome, Date dtNascimento, String cpf, String email, List<Conta> contas) {
		super();
		this.id = id;
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.cpf = cpf;
		this.email = email;
		this.contas = contas;
	}

	public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }


	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public Date getDtNascimento() {
		return dtNascimento;
	}




	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}




	public String getCpf() {
		return cpf;
	}




	public void setCpf(String cpf) {
		this.cpf = cpf;
	}




	public Optional<List<Conta>> getContas() {
		return Optional.ofNullable(contas);
	}




	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public BigDecimal getSaldoContas() {
		saldoContas = new BigDecimal(0.0);
		for (Conta conta: contas) {
			saldoContas = saldoContas.add(conta.getSaldo());
		}
		return saldoContas;
	}

	public void setSaldoContas(BigDecimal saldoContas) {
		this.saldoContas = saldoContas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dtNascimento == null) ? 0 : dtNascimento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dtNascimento == null) {
			if (other.dtNascimento != null)
				return false;
		} else if (!dtNascimento.equals(other.dtNascimento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}


	public static void imprimeSeEstiverPresente(Optional<Cliente> obj){
    	if(obj.isPresent()) {
    		obj.get().imprime();
    	}else {
    		System.out.println("Não encontrado!");
    	}
    }

	public void imprime() {
		System.out.println("Cliente [id=" + id + ", nome=" + nome + ", dtNascimento=" + dtNascimento + ", cpf=" + cpf + ", saldoContas=" + getSaldoContas() +"]");
	}

	
}
