package app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cliente", catalog = "dbteste", schema = "dbteste")
public class Cliente  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "password")
    private String password;

    @Column(name = "porcentagem")
    private String porcentagem;

   

    public Cliente() {
    }

    public Cliente(String nome, String password, String porcentagem) {
        super();
        this.nome = nome;
        this.password = password;
        this.porcentagem = porcentagem;
    }
    
    public Cliente(Long id, String nome, String password, String porcentagem) {
        super();
        this.id = id;
        this.nome = nome;
        this.password = password;
        this.porcentagem = porcentagem;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(String porcentagem) {
		this.porcentagem = porcentagem;
	}



}
