/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaee.team2.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class TeamBean implements Serializable {

	private String test = "test";
	private List<Team> teamList;
	@Inject
	private TeamFacade teamFacade;

	private Integer max;

	public TeamBean() {
	}

	@PostConstruct
	public void init() {
		getAllTeam();
	}

	public void getAllTeam() {
		teamList = teamFacade.findAll();
		calGamesBehind();

		List<Integer> test;
		test = teamList.stream().map(i -> i.getWins() - i.getLosses()).collect(ArrayList::new, List::add, List::addAll);
		max = test.stream().reduce(Integer.MIN_VALUE, (n, m) -> Integer.max(n, m));
//		test.stream().forEach(i -> System.out.println(i - max));
//		test.stream().forEach(System.out::println);
		System.out.println(max);
	}

	public BigDecimal GB(Team team) {
		BigDecimal tmax = new BigDecimal(max);
		BigDecimal result;
		BigDecimal b1 = BigDecimal.valueOf(team.getWins());
		BigDecimal b2 = BigDecimal.valueOf(team.getLosses());
		result = b1.subtract(b2).subtract(tmax);
		
		result = result.divide(BigDecimal.valueOf(2), 1, BigDecimal.ROUND_HALF_UP);
		return result;
	}

	public void calGamesBehind() {
		//ゲーム差でソート
		Collections.sort(teamList, new Comparator<Team>() {
			public static final int ASC = 1;
			public static final int DESC = -1;

			@Override
			public int compare(Team t1, Team t2) {
				int sortType = DESC;
				int t1GB = t1.getWins() - t1.getLosses();
				int t2GB = t2.getWins() - t2.getLosses();

				if (t1 == null && t2 == null) {
					return 0;
				} else if (t1 == null) {
					return 1 * sortType;
				} else if (t2 == null) {
					return -1 * sortType;
				}
				return (t1GB - t2GB) * sortType;
			}
		});
	}

	public String editTeam(Team editTeam) {
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
