/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaee.team2.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author yuu
 */

@Entity
@Table(name = "NPBTEAM", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Team implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@Getter @Setter
	private Integer wins;
	@Getter @Setter
	private Integer losses;
	@Getter @Setter
	private Integer drows;

	@OneToMany(mappedBy = "belong", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Member> members;
	
	public Team(){}
	public Team(String name, List<Member> members){
		this.name = name;
		this.members = members;
		this.wins = 0;
		this.losses = 0;
		this.drows = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
}
