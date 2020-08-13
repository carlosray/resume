package com.resume.repository.storage;

import com.resume.entity.SkillCategory;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SkillCategoryRepository extends PagingAndSortingRepository<SkillCategory, Long> {
}
