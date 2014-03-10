package org.jooq.demo.db.daos;

import static org.fest.assertions.Assertions.assertThat;

import java.sql.SQLException;

import org.jooq.demo.TestApp;
import org.jooq.demo.db.beans.User;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class UserDaoTest extends TestApp {
	
	private UserDao userDao;
	
	@Before
	public void init() throws SQLException {
		userDao = injector().getInstance(UserDao.class);
		userDao.insert(userA(), userB());
	}
	
	@Test
	public void should_search_by_firstname() {
		assertThat(userDao.search("z", null)).isEqualTo(ImmutableList.of());
		
		assertThat(userDao.search("j", null)).isEqualTo(ImmutableList.of(userA()));
		assertThat(userDao.search("sti", null)).isEqualTo(ImmutableList.of(userA()));
		assertThat(userDao.search("Justine", null)).isEqualTo(ImmutableList.of(userA()));
		
		assertThat(userDao.search("r", null)).isEqualTo(ImmutableList.of(userB()));
		assertThat(userDao.search("RICKIE", null)).isEqualTo(ImmutableList.of(userB()));
		assertThat(userDao.search("ie", null)).isEqualTo(ImmutableList.of(userB()));
		
		assertThat(userDao.search(null, null)).isEqualTo(ImmutableList.of(userA(), userB()));
		assertThat(userDao.search("", null)).isEqualTo(ImmutableList.of(userA(), userB()));
		assertThat(userDao.search("i", null)).isEqualTo(ImmutableList.of(userA(), userB()));
	}
	
	@Test
	public void should_search_by_lastname() {
		assertThat(userDao.search(null, "z")).isEqualTo(ImmutableList.of());
		
		assertThat(userDao.search(null, "mu")).isEqualTo(ImmutableList.of(userA()));
		assertThat(userDao.search(null, "gno")).isEqualTo(ImmutableList.of(userA()));
		assertThat(userDao.search(null, "Mugnolo")).isEqualTo(ImmutableList.of(userA()));
		
		assertThat(userDao.search(null, "er")).isEqualTo(ImmutableList.of(userB()));
		assertThat(userDao.search(null, "Pl")).isEqualTo(ImmutableList.of(userB()));
		assertThat(userDao.search(null, "PLUMER")).isEqualTo(ImmutableList.of(userB()));
		
		assertThat(userDao.search(null, null)).isEqualTo(ImmutableList.of(userA(), userB()));
		assertThat(userDao.search(null, "")).isEqualTo(ImmutableList.of(userA(), userB()));
		assertThat(userDao.search(null, "u")).isEqualTo(ImmutableList.of(userA(), userB()));
	}
	
	@Test
	public void should_search_by_firstname_and_lastname() {
		assertThat(userDao.search("z", "z")).isEqualTo(ImmutableList.of());
		
		assertThat(userDao.search("j", "mu")).isEqualTo(ImmutableList.of(userA()));
		assertThat(userDao.search("sti", "gno")).isEqualTo(ImmutableList.of(userA()));
		assertThat(userDao.search("Justine", "Mugnolo")).isEqualTo(ImmutableList.of(userA()));
		
		assertThat(userDao.search("r", "er")).isEqualTo(ImmutableList.of(userB()));
		assertThat(userDao.search("RICKIE", "Pl")).isEqualTo(ImmutableList.of(userB()));
		assertThat(userDao.search("ie", "PLUMER")).isEqualTo(ImmutableList.of(userB()));
		
		assertThat(userDao.search(null, null)).isEqualTo(ImmutableList.of(userA(), userB()));
		assertThat(userDao.search("", "")).isEqualTo(ImmutableList.of(userA(), userB()));
		assertThat(userDao.search("i", "u")).isEqualTo(ImmutableList.of(userA(), userB()));
	}
	
	// utils
	
	private User userA() {
		return new User(1L, "Justine", "Mugnolo", "jmugnolo@yahoo.com", "jmugnolo", "jmugnolo741");
	}
	
	private User userB() {
		return new User(2L, "Rickie", "Plumer", "rickie.plumer@aol.com", "rickie.plumer", "azerty");
	}

}
