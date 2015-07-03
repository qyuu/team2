package javaee.team2.bean;

import java.io.Serializable;
import java.util.List;
import javaee.team2.ejb.TeamFacade;
import javaee.team2.entity.Team;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;
import javax.validation.constraints.Size;

@Named(value = "sidebarBean")
@ViewScoped
public class SidebarBean implements Serializable{

	private List<Team> teamList;
	
	@Size(min=1, max=30)
	private String name;
	
	@Inject
	private TeamFacade teamFacade;

	public SidebarBean() {
	}
	
	@PostConstruct
	public void init(){
		getAllTeam();
	}
	
	public void getAllTeam(){
		teamList = teamFacade.findAll();
	}
	
	public void formClear(){
		name = null;
	}
	
	public String editTeam(Team editTeam){
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("editTeam", editTeam.getId());
		System.out.println("----------------------------------------" + editTeam.getId() + "----------------------------------------");
		return "teammember.xhtml";
	}
	
	public String addTeam(){
//		Team newTeam = new Team();
//		newTeam.setName(name);
//		teamFacade.create(newTeam);
		
		//teamList = teamFacade.findAll();
		//formClear();
		
		return "/addteam.xhtml";
	}
	
	public void deleteTeam(Team deleteTeam){
		teamFacade.remove(deleteTeam);
		
		teamList = teamFacade.findAll();
	}
	
// getter setter	

	public List<Team> getTeamList() {
		return teamList;
	}

	public void setTeamList(List<Team> teamList) {
		this.teamList = teamList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
