/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaee.team2.ejb;

import javaee.team2.entity.Member;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yuu
 */
@Stateless
public class MemberFacade extends AbstractFacade<Member> {
	@PersistenceContext(unitName = "com.mycompany_team2_war_1.0-SNAPSHOTPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public MemberFacade() {
		super(Member.class);
	}
	
}
