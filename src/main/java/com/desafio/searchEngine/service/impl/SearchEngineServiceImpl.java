package com.desafio.searchEngine.service.impl;

import com.desafio.searchEngine.dtos.ArticleDTO;
import com.desafio.searchEngine.repository.impl.ArticlesRepositoryImpl;
import com.desafio.searchEngine.service.SearchEngineService;
import com.desafio.searchEngine.utils.ComparatorArticles;
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
    public List<ArticleDTO> getAllArticlesByFilters(ArticleDTO articleDTO) {
        List<ArticleDTO> articles;
        List<ArticleDTO> result = new ArrayList<>();
        ComparatorArticles comparator = new ComparatorArticles();

        if(articleDTO.getCategory() != null){
            articles = repo.getArticleByCategory(articleDTO.getCategory());
        } else {
            articles = repo.getAllArticles();
        }

        for (ArticleDTO art: articles) {
            if(comparator.compare(art, articleDTO) > 0){
                result.add(art);
            }
        }

        return result;
    }
}
