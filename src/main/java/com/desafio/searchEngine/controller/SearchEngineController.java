package com.desafio.searchEngine.controller;

import com.desafio.searchEngine.dtos.ArticleDTO;
import com.desafio.searchEngine.service.impl.SearchEngineServiceImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/articles")
public class SearchEngineController {

    private final SearchEngineServiceImpl searchEngineSrv;

    public SearchEngineController(SearchEngineServiceImpl searchEngineSrv) {
        this.searchEngineSrv = searchEngineSrv;
    }

    @GetMapping()
    public List<ArticleDTO> getAllArticlesAvailables() {
        return this.searchEngineSrv.getAllArticlesAvailables();
    }

    @RequestMapping(params = "category")
    public List<ArticleDTO> getAllArticlesByCategory(@RequestParam String category) {
        return this.searchEngineSrv.getAllArticlesByCategory(category);
    }

    @GetMapping()
    public List<ArticleDTO> getAllArticlesByFilters(@RequestParam (required = false) String name,
                                                    @RequestParam (required = false) String category,
                                                    @RequestParam (required = false) String brand,
                                                    @RequestParam (required = false) Double price,
                                                    @RequestParam (required = false) Integer quantity,
                                                    @RequestParam (required = false) Boolean freeShip,
                                                    @RequestParam (required = false) String prestige) {
        ArticleDTO articleDTO = new ArticleDTO(name, category, brand, price, quantity, freeShip, prestige);
        return searchEngineSrv.getAllArticlesByFilters(articleDTO);
    }

}
