package com.desafio.searchEngine.repository.impl;

import com.desafio.searchEngine.dtos.ArticleDTO;
import com.desafio.searchEngine.repository.ArticlesRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticlesRepositoryImpl implements ArticlesRepository {

    private static final ArrayList<ArticleDTO> database;
    static{
        database = loadDatabase();
    }

    @Override
    public ArrayList<ArticleDTO> getAllArticles() {
        ArrayList<ArticleDTO> articles = database;
        return articles;
    }

    @Override
    public List<ArticleDTO> getArticleByCategory(String category) {
        ArrayList<ArticleDTO> articles = database;
        List<ArticleDTO> result = new ArrayList<>();

        for (ArticleDTO article: articles) {
            if(article.getCategory().equals(category)){
                result.add(article);
            }
        }

        return result;
    }

    @Override
    public List<ArticleDTO> getArticleByFilters(ArticleDTO articleDTO) {
        ArrayList<ArticleDTO> articles = database;
        List<ArticleDTO> result = new ArrayList<>();

        return result;
    }

    private static ArrayList<ArticleDTO> loadDatabase(){
        ArrayList<ArticleDTO> articles = new ArrayList<ArticleDTO>();
        File fileDb = null;

        try{
            fileDb = ResourceUtils.getFile("/Users/frivarola/Documents/ProjectsDH/spring/search-engine/src/main/java/com/desafio/searchEngine/repository/articles.json");
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<ArrayList<ArticleDTO>> tf = new TypeReference<>(){};
            articles = mapper.readValue(fileDb, tf);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return articles;
    }

}
