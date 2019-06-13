package com.mesi.histoireHeros.repository;

import com.mesi.histoireHeros.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {
}
