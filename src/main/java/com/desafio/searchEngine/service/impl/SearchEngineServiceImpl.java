package com.desafio.searchEngine.service.impl;

import com.desafio.searchEngine.dtos.ArticleDTO;
import com.desafio.searchEngine.repository.impl.ArticlesRepositoryImpl;
import com.desafio.searchEngine.service.SearchEngineService;
import com.desafio.searchEngine.utils.ComparatorArticles;
import com.desafio.searchEngine.utils.OrderArticles;
import com.desafio.searchEngine.utils.Sorter;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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

        if (articleDTO.getCategory() != null) {
            articles = repo.getArticleByCategory(articleDTO.getCategory());
        } else {
            articles = repo.getAllArticles();
        }

        for (ArticleDTO art : articles) {
            if (comparator.compare(art, articleDTO) > 0) {
                result.add(art);
            }
        }

        return result;
    }

    @Override
    public List<ArticleDTO> getAllArticlesAvailables(Integer order) {
        return orderBy(this.getAllArticlesAvailables(), order);
    }

    @Override
    public List<ArticleDTO> getAllArticlesByCategory(String category, Integer order) {
        return orderBy(this.getAllArticlesByCategory(category), order);
    }

    @Override
    public List<ArticleDTO> getAllArticlesByFilters(ArticleDTO articleDTO, Integer order) {
        return orderBy(this.getAllArticlesByFilters(articleDTO), order);
    }

    /**
     * Funcion que determina el ordenamiento a implementar.
     * @param list - lista a ordenar
     * @param order - tipo de ordenaamiento definido en OrderArticles.enum
     * @return lista ordenada
     */
    private List<ArticleDTO> orderBy(List<ArticleDTO> list, Integer order){
        List<ArticleDTO> articles = list;

        if(order.equals(OrderArticles.ALPH_ASC)){
            //orden alfabeticamente ascendente
            return Sorter.bubbleSortAsc(articles, Comparator.comparing(ArticleDTO::getName));
        }
        if(order.equals(OrderArticles.ALPH_DESC)){
            //orden alfabeticamente descendente
            return Sorter.bubbleSortDesc(articles, Comparator.comparing(ArticleDTO::getName));
        }
        if(order.equals(OrderArticles.PRICE_HIGHER)){
            return Sorter.bubbleSortDesc(articles, Comparator.comparing(ArticleDTO::getPrice));
        }
        if(order.equals(OrderArticles.PRICE_LOWER)){
            return Sorter.bubbleSortAsc(articles, Comparator.comparing(ArticleDTO::getPrice));
        }
    }

}
