package org.ismetg.service;

public abstract class LetGoServices {
    public static final CategoryService categoryService = new CategoryService();
    public static final CommentService commentService = new CommentService();
    public static final FavouriteIlanService favouriteIlanService = new FavouriteIlanService();
    public static final IlanService ilanService = new IlanService();
    public static final ImageService imageService = new ImageService();
    public static final MessageService messageService = new MessageService();
    public static final UserService userService = new UserService();
}
