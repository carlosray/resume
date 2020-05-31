package com.resume.repository;

import com.resume.entity.SkillCategory;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SkillCategoryRepository extends PagingAndSortingRepository<SkillCategory, Long> {
}
