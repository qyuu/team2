/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaee.team2.bean;

import java.io.Serializable;
import java.util.List;
import javaee.team2.ejb.TeamFacade;
import javaee.team2.entity.Team;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author yuu
 */
@Named(value = "teamBean")
@ViewScoped
public class TeamBean implements Serializable{

	private String test = "test";
	private List<Team> teamList;
	@Inject
	private TeamFacade teamFacade;
	
	public TeamBean() {
	}
	
	@PostConstruct
	public void init(){
		getAllTeam();
	}
	
	public void getAllTeam(){
		teamList = teamFacade.findAll();
	}

	public String editTeam(Team editTeam){
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("editTeam", editTeam.getId());
		return "teammember.xhtml";
	}
	
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public List<Team> getTeamList() {
		return teamList;
	}

	public void setTeamList(List<Team> teamList) {
		this.teamList = teamList;
	}
}
