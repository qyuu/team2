/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaee.team2.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

/**
 *
 * @author yuu
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"NUMBER", "belong_id"})})
public class Member implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Size(max = 3)
	private String number;
	
	private String name;
	
	@ManyToOne
	private Team belong;
	
	public Member(){}
	public Member(String number, String name, Team belong){
		this.number = number;
		this.name = name;
		this.belong = belong;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Team getBelong() {
		return belong;
	}

	public void setBelong(Team belong) {
		this.belong = belong;
	}
	
	
}
