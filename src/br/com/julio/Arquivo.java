package br.com.julio;

public class Arquivo {

	private String nome;
	private String tipo;
	private int paginas;

	public Arquivo(String nome, String tipo, int paginas) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.paginas = paginas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Produzindo Arquivo [ " + nome + "." + tipo + ", "+paginas+" paginas ]";
	}

}
