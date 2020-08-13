package com.resume.service.impl;

import com.resume.entity.Profile;
import com.resume.event.UpdateProfileEvent;
import com.resume.repository.search.ProfileSearchRepository;
import com.resume.service.SearchService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
@Log4j
public class ElasticSearchIndexService {
    @Value("${elasticsearch.index.all.during.startup}")
    private boolean indexAllDuringStartup;

    private final ElasticsearchOperations elasticsearchOperations;
    private final SearchService searchService;
    private final ProfileSearchRepository profileSearchRepository;

    @Autowired
    public ElasticSearchIndexService(ElasticsearchOperations elasticsearchOperations, SearchService searchService, ProfileSearchRepository profileSearchRepository) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.searchService = searchService;
        this.profileSearchRepository = profileSearchRepository;
    }

    @PostConstruct
    private void postConstruct(){
        if(indexAllDuringStartup) {
            updateAllIndexes();
        }
        else{
            log.info("ELASTIC: indexAllDuringStartup is disabled");
        }
    }

    public void updateAllIndexes() {
        log.info("ELASTIC: Detected index all command");
        log.info("ELASTIC: Clear old index");
        elasticsearchOperations.deleteIndex(Profile.class);
        log.info("ELASTIC: Start index of profiles");
        for(Profile p : searchService.findAllProfilesForIndexing()){
            profileSearchRepository.save(p);
            log.info("ELASTIC: Successful indexing of profile: "+ p.getUid());
        }
        log.info("ELASTIC: Finish index of profiles");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void updateIndexProfile(UpdateProfileEvent event) {
        Profile updatableProfile = event.getObject();
        log.debug("ELASTIC: Обновление индекса началось, профиль ID " + updatableProfile.getId());
        Profile byId = profileSearchRepository.findOne(updatableProfile.getId());
        if (byId != null) {
            updateIndexForProfile(byId);
        }
        else {
            createNewIndexForProfile(updatableProfile);
        }
    }

    private void updateIndexForProfile(Profile byId) {
        profileSearchRepository.save(byId);
        log.debug("ELASTIC: Обновление индекса успешно, профиль ID " + byId.getId());
    }

    private void createNewIndexForProfile(Profile updatableProfile) {
        if (updatableProfile.getCertificates() == null) {
            updatableProfile.setCertificates(Collections.EMPTY_LIST);
        }
        if (updatableProfile.getPractics() == null) {
            updatableProfile.setPractics(Collections.EMPTY_LIST);
        }
        if (updatableProfile.getLanguages() == null) {
            updatableProfile.setLanguages(Collections.EMPTY_LIST);
        }
        if (updatableProfile.getSkills() == null) {
            updatableProfile.setSkills(Collections.EMPTY_LIST);
        }
        profileSearchRepository.save(updatableProfile);
        log.debug("ELASTIC: Создан новый индекс, профиль ID " + updatableProfile.getId());
    }
}
