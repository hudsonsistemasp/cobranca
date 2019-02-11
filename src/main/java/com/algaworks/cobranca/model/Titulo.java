package com.algaworks.cobranca.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/*model para mapear as entidades que irão persistir no banco*/
/*A notação @Entity é para mapear para o JPA*/
@Entity
public class Titulo {
	
	/*Para o codigo, que será o ID no banco, gerar o hashCode e fazer a notação JPA @Id 
	  e a forma que ele tem que ser gerado no banco, ex: IDENTITY*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String descricao;
	
	/*Notaçao para a data @Temporal, que no caso vai ser somente data sem hora, mas se quiser outro basta escolher*/
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")/*MM= representa MÊS. mm= representa minutos*/
	
	private Date dataVencimento;
	
	@NotNull
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valor;
	
	/*Como é uma enumeração, apontar a notação @Enumerated. Se quiser buscar no banco com status 0 ou 1, colocar como 
	 ORDINAL e não como STRING. STRING BUSCA o que está desenhado no enum; PENDENTE ou RECEBIDO*/
	@Enumerated(EnumType.STRING)
	private StatusTitulo status;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public StatusTitulo getStatus() {
		return status;
	}
	public void setStatus(StatusTitulo status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Titulo other = (Titulo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}
