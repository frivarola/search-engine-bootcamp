package com.desafio.searchEngine.service;

import com.desafio.searchEngine.dtos.ArticleDTO;

import java.util.List;

public interface SearchEngineService {

    List<ArticleDTO> getAllArticlesAvailables();
    List<ArticleDTO> getAllArticlesByCategory(String category);
    List<ArticleDTO> getAllArticlesByFilters(String[] filters);
}
