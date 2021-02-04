package com.desafio.searchEngine.repository;

import com.desafio.searchEngine.dtos.ArticleDTO;

import java.util.List;

public interface ArticlesRepository {

    List<ArticleDTO> getAllArticles();
    List<ArticleDTO> getArticleByCategory(String category);
    List<ArticleDTO> getArticleByFilters(ArticleDTO articleDTO);
}
