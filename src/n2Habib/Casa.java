package n2Habib;

public class Casa {

	private int id;
	private int idPessoa;
	private int qtQuartos;

	public Casa() { }

	public Casa(int id, int idPessoa, int qtQuartos) {
		this.id = id;
		this.idPessoa = idPessoa;
		this.qtQuartos = qtQuartos;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getqtQuartos() {
		return qtQuartos;
	}

	public void setqtQuartos(int qtQuartos) {
		this.qtQuartos = qtQuartos;
	}
	
	@Override
	public String toString() {
		return "Casa [id =" + id + ", qtQuartos =" + qtQuartos + "]";
	}
}