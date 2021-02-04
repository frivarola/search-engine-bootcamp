package com.desafio.searchEngine.controller;

import com.desafio.searchEngine.dtos.ArticleDTO;
import com.desafio.searchEngine.service.impl.SearchEngineServiceImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1/articles")
public class SearchEngineController {

    private final SearchEngineServiceImpl searchEngineSrv;

    public SearchEngineController(SearchEngineServiceImpl searchEngineSrv) {
        this.searchEngineSrv = searchEngineSrv;
    }

    @GetMapping()
    public List<ArticleDTO> getAllArticlesAvailables(){
      return this.searchEngineSrv.getAllArticlesAvailables();
    }

    @RequestMapping(method = RequestMethod.GET, params={"category"})
    public List<ArticleDTO> getAllArticlesByCategory(@RequestParam String category){
        return this.searchEngineSrv.getAllArticlesByCategory(category);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name", "category", "freeShip", "brand", "price", "quantity", "prestige", "price"})
    public List<ArticleDTO> getAllArticlesByFilters(@RequestParam String name,
                                                    @RequestParam String category,
                                                    @RequestParam String brand,
                                                    @RequestParam Double price,
                                                    @RequestParam Integer quantity,
                                                    @RequestParam Boolean freeShip,
                                                    @RequestParam String prestige) {
        ArticleDTO articleDTO = new ArticleDTO(name, category, brand, price, quantity, freeShip, prestige);
        return searchEngineSrv.getAllArticlesByFilters(articleDTO);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"category", "freeShip"})
    public List<ArticleDTO> getAllArticlesByFilters(@RequestParam String category,
                                                    @RequestParam Boolean freeShip) {
        ArticleDTO articleDTO = new ArticleDTO(category, freeShip);
        return searchEngineSrv.getAllArticlesByFilters(articleDTO);
    }
    @RequestMapping(method = RequestMethod.GET, params = {"category", "brand"})
    public List<ArticleDTO> getAllArticlesByFilters(@RequestParam String category,
                                                    @RequestParam String brand) {
        ArticleDTO articleDTO = new ArticleDTO(category);
        articleDTO.setBrand(brand);
        return searchEngineSrv.getAllArticlesByFilters(articleDTO);
    }





}

/*


    @RequestParam String name,
    @RequestParam String category,
    @RequestParam String brand,
    @RequestParam Double price,
    @RequestParam Integer quantity,
    @RequestParam Boolean freeShip,
    @RequestParam String prestige,

 */