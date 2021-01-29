package com.basica.ProjetoKellyEpompeaLojaUnit;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table (name="marca")
public class Marca {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
		private String nomeFabricante;
	private String descricao;
	@OneToMany(mappedBy = "marca")
    private List<Produto> produtos;
	
	
	
	public Marca() {
		super();
	}
	public String getNomeFabricante() {
		return nomeFabricante;
	}
	public void setNomeFabricante(String nomeFabricante) {
		this.nomeFabricante = nomeFabricante;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
