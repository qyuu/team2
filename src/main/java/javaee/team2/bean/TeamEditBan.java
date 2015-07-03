/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaee.team2.bean;

import java.io.Serializable;
import javaee.team2.ejb.MemberFacade;
import javaee.team2.ejb.TeamFacade;
import javaee.team2.entity.Member;
import javaee.team2.entity.Team;
import javaee.team2.util.Tracer;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author yuu
 */
@Interceptors(Tracer.class)
@Named(value = "teamEditBan")
@ViewScoped
public class TeamEditBan implements Serializable {

	private Integer key;
	private Team editTeam;

	@Size(min=1, max=20)
	private String name;
	
	@Pattern(regexp="^[0-9]{1,3}")
	private String number;

	@Inject
	private TeamFacade teamFacade;
	@Inject
	private MemberFacade memberFacade;

	@PostConstruct
	public void init() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		key = (Integer) flash.get("editTeam");
		System.out.println("key--------------------------------------------------------------------------------" + key);
		if(key == null) return;
		editTeam = teamFacade.find(key);
	}

	public void addMember() {
		Member newMember = new Member();
		newMember.setBelong(editTeam);
		newMember.setName(name);
		newMember.setNumber(number);
//		memberFacade.create(newMember);
		editTeam.getMembers().add(newMember);

		teamFacade.edit(editTeam);
//		teamFacade.remove(editTeam);
//		teamFacade.create(editTeam);
		editTeam = teamFacade.find(key);
		
		formClear();
	}

	public TeamEditBan() {

	}

	public void deleteMember(Member deleteMember) {
//		teamFacade.remove(editTeam);
		editTeam.getMembers().remove(deleteMember);
////		teamFacade.create(editTeam);
		teamFacade.edit(editTeam);
		memberFacade.remove(deleteMember);
		
		//editTeam = teamFacade.find(key);
	}

	public void formClear(){
		name = number = null;
	}
	
	// getter setter
	public Team getEditTeam() {
		return editTeam;
	}

	public void setEditTeam(Team editTeam) {
		this.editTeam = editTeam;
	}

	public TeamFacade getTeamFacade() {
		return teamFacade;
	}

	public void setTeamFacade(TeamFacade teamFacade) {
		this.teamFacade = teamFacade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
