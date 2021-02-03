package com.desafio.searchEngine.controller;

import com.desafio.searchEngine.dtos.ArticleDTO;
import com.desafio.searchEngine.service.impl.SearchEngineServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(name="/api/v1/articles")
public class SearchEngineController {

    private final SearchEngineServiceImpl searchEngineSrv;

    public SearchEngineController(SearchEngineServiceImpl searchEngineSrv) {
        this.searchEngineSrv = searchEngineSrv;
    }

    @GetMapping()
    public List<ArticleDTO> getAllArticlesAvailables(){
      return this.searchEngineSrv.getAllArticlesAvailables();
    }

    @GetMapping()
    public List<ArticleDTO> getAllArticlesByCategory(@RequestParam String category){
        return this.searchEngineSrv.getAllArticlesByCategory(category);
    }
}
