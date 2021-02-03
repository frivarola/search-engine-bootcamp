package com.desafio.searchEngine.service.impl;

import com.desafio.searchEngine.dtos.ArticleDTO;
import com.desafio.searchEngine.repository.impl.ArticlesRepositoryImpl;
import com.desafio.searchEngine.service.SearchEngineService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchEngineServiceImpl implements SearchEngineService {

    private final ArticlesRepositoryImpl repo;

    public SearchEngineServiceImpl(ArticlesRepositoryImpl repo) {
        this.repo = repo;
    }

    @Override
    public List<ArticleDTO> getAllArticlesAvailables() {
        List<ArticleDTO> articles = repo.getAllArticles();
        return articles;
    }

    @Override
    public List<ArticleDTO> getAllArticlesByCategory(String category) {
        List<ArticleDTO> articles = repo.getArticleByCategory(category);
        return articles;
    }

    @Override
    public List<ArticleDTO> getAllArticlesByFilters(String[] filters) {
        return null;
    }
}
