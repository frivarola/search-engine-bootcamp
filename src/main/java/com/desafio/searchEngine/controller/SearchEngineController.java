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

/*    @GetMapping()
    public List<ArticleDTO> getAllArticlesAvailables(@RequestParam (required = false) Integer order) {
        return this.searchEngineSrv.getAllArticlesAvailables(order);
    }

    @GetMapping(params = {"category"})
    public List<ArticleDTO> getAllArticlesByCategory(@RequestParam String category) {
        return this.searchEngineSrv.getAllArticlesByCategory(category);
    }

 */

    @GetMapping()
    public List<ArticleDTO> getAllArticlesByFilters(@RequestParam (required = false) String name,
                                                    @RequestParam (required = false) String category,
                                                    @RequestParam (required = false) String brand,
                                                    @RequestParam (required = false) Double price,
                                                    @RequestParam (required = false) Integer quantity,
                                                    @RequestParam (required = false) Boolean freeShip,
                                                    @RequestParam (required = false) String prestige,
                                                    @RequestParam (required = false) Integer order) {
        //En este momento se arma un "PSEUDO ARTICULO" con los valores que nos llegaron como parametros y se trata de buscar el Articulo que sea mas similar a este pseudoArticulo
        //La comparacion se hace con ComparatorArticle
        ArticleDTO articleDTO = new ArticleDTO(name, category, brand, price, quantity, freeShip, prestige);
        return searchEngineSrv.getAllArticlesByFilters(articleDTO, order);
    }

}
