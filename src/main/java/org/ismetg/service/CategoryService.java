package org.ismetg.service;

import org.ismetg.repository.CategoryRepository;

public class CategoryService {
    CategoryRepository categoryRepository;

    public CategoryService()
    {
        this.categoryRepository = new CategoryRepository();
    }
}
