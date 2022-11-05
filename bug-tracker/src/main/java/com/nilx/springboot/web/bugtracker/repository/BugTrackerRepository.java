package com.nilx.springboot.web.bugtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nilx.springboot.web.bugtracker.model.Jira;

public interface BugTrackerRepository extends JpaRepository<Jira, Integer> {
	List<Jira> findByUser(String user);
}
