/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaee.team2.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javaee.team2.ejb.TeamFacade;
import javaee.team2.entity.Member;
import javaee.team2.entity.Team;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author yuu
 */
@Named(value = "addTeamBean")
@ViewScoped
public class AddTeamBean implements Serializable{

	private String name;
	
	@Inject
	TeamFacade teamFacade;
	
	public AddTeamBean() {
	}
	
	public String addTeam(){
		Team newTeam = new Team();
		newTeam.setName(name);
//		List<Member> memberList = new ArrayList<>();
		teamFacade.create(newTeam);
		
		return "/index.xhtml";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
