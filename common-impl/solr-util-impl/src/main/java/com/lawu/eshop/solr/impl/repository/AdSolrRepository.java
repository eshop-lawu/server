package com.lawu.eshop.solr.impl.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.lawu.eshop.solr.impl.entity.AdSolr;

public interface AdSolrRepository extends SolrCrudRepository<AdSolr, Long> {
    
}