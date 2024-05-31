package org.ismetg.repository;

import org.ismetg.entity.FavouriteIlan;

public class FavouriteIlanRepository extends RepositoryManager<FavouriteIlan, Long> {
    public FavouriteIlanRepository() {
        super(FavouriteIlan.class);
    }
}
