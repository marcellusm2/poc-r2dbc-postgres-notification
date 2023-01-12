package com.example.demo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
interface POIEventRepository extends ReactiveCrudRepository<POI, Integer> {

}
