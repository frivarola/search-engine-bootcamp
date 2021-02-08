package com.desafio.searchEngine.service.impl;

import com.desafio.searchEngine.dtos.ArticleDTO;
import com.desafio.searchEngine.repository.impl.ArticlesRepositoryImpl;
import com.desafio.searchEngine.service.SearchEngineService;
import com.desafio.searchEngine.utils.ComparatorArticles;
import com.desafio.searchEngine.utils.OrderArticles;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
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

        if(result.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron resultados para la busqueda.");
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
        if (order != null) {
            return orderBy(this.getAllArticlesByFilters(articleDTO), order);
        } else {
            return this.getAllArticlesByFilters(articleDTO);
        }
    }

    /**
     * Funcion que determina el ordenamiento a implementar.
     *
     * @param list  - lista a ordenar
     * @param order - tipo de ordenaamiento definido en OrderArticles.enum
     * @return lista ordenada
     */
    private List<ArticleDTO> orderBy(List<ArticleDTO> list, @Nullable Integer order) {
        List<ArticleDTO> articles = list;
        if (order.equals(OrderArticles.ALPH_ASC.ordinal())) {
            //orden alfabeticamente ascendente
            Collections.sort(articles, (a,b) -> b.getName().compareTo(a.getName()));

            return articles;
        }
        if (order.equals(OrderArticles.ALPH_DESC.ordinal())) {
            //orden alfabeticamente descendente
            Collections.sort(articles, (a,b) -> a.getName().compareTo(b.getName()));
            return articles;
        }
        if (order.equals(OrderArticles.PRICE_HIGHER.ordinal())) {
            Collections.sort(articles, (a,b) -> b.getPrice().compareTo(a.getPrice()));
            return articles;
        }
        if (order.equals(OrderArticles.PRICE_LOWER.ordinal())) {
            Collections.sort(articles, (a,b) -> a.getPrice().compareTo(b.getPrice()));
            return articles;
        }
        //si el metodo de ordenamiento no existe, devuelve la lista original
        return articles;
    }

}
