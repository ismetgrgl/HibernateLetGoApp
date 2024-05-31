package org.ismetg.service;

import org.ismetg.repository.FavouriteIlanRepository;

public class FavouriteIlanService {
    FavouriteIlanRepository favouriteIlanRepository;

    public FavouriteIlanService()
    {
        this.favouriteIlanRepository = new FavouriteIlanRepository();
    }
}
