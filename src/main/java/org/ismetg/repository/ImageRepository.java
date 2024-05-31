package org.ismetg.repository;

import org.ismetg.entity.Image;

public class ImageRepository extends RepositoryManager<Image , Long> {

    public ImageRepository() {
        super(Image.class);
    }
}
