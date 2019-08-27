package exercicio01;

import java.util.List;
import java.util.Optional;

public class Banco {

	private Long id;
	private String nome;
	private String codigoBanco;
	private List<Cliente> clientes;
	
	public Cliente buscarClientePorId(Long id){
        for (Cliente cliente : clientes){
            if(cliente.getId().equals(id)){
                return cliente;
            }
        }
        return null;
    }
	
	public List<Cliente> ordenaClientePorNome(){
        clientes.sort((c1, c2) -> c1.getNome().compareTo(c2.getNome()));
        return clientes;
    }
	
	public void listaEmailsCliente(){
		for (Cliente cliente: clientes) {
			Optional<Cliente> optionalCliente = Optional.of(cliente);
	        String email = optionalCliente.flatMap(Cliente::getEmail).orElse("Email não informado");
	        System.out.println(email);
		}
    }
	
	public Banco() {
		
	}
	public Banco(Long id, String nome, String codigoBanco, List<Cliente> clientes) {
		super();
		this.id = id;
		this.nome = nome;
		this.codigoBanco = codigoBanco;
		this.clientes = clientes;
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
	public String getCodigoBanco() {
		return codigoBanco;
	}
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoBanco == null) ? 0 : codigoBanco.hashCode());
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
		Banco other = (Banco) obj;
		if (codigoBanco == null) {
			if (other.codigoBanco != null)
				return false;
		} else if (!codigoBanco.equals(other.codigoBanco))
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
	
	
	
	
}
