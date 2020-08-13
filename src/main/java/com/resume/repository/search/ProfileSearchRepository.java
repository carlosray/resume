package com.resume.repository.search;

import com.resume.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface ProfileSearchRepository extends ElasticsearchCrudRepository<Profile, Long> {
    /*
    http://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.query-methods.criterions
     */
    Page<Profile> findByObjectiveLikeOrSummaryLikeOrInfoLike
    (String objective, String summary, String info, Pageable pageable);
}
