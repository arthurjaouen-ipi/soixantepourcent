package com.mesi.histoireHeros.repository;

import com.mesi.histoireHeros.model.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
}
