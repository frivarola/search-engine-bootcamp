package com.desafio.searchEngine.utils;

import com.desafio.searchEngine.dtos.ArticleDTO;

import java.util.Comparator;

public class ComparatorArticles implements Comparator<ArticleDTO> {
    @Override
    /**
     * Funcion para comparar articulos.. solo compara los campos que tienen un valor, si es nulo no lo tiene en cuenta
     * o1 tiene que ser lo mas parecido a o2, es decir que en los campos que tenga o2 deben ser iguales.
     * Devuelve -1 si no cumple con alguno de los campos a comparar
     */
    public int compare(ArticleDTO o1, ArticleDTO o2) {

        if(o2.getName() != null){
            if(!o1.getName().equals(o2.getName())){
                return -1;
            }
        }
        if(o2.getFreeShip() != null){
            if(!o1.getFreeShip().equals(o2.getFreeShip())){
                return -1;
            }
        }
        if(o2.getCategory() != null){
            if(!o1.getFreeShip().equals(o2.getFreeShip())){
                return -1;
            }
        }
        if(o2.getBrand() != null){
            if(!o1.getBrand().equals(o2.getBrand())){
                return -1;
            }
        }
        if(o2.getPrestige() != null){
            if(!o1.getPrestige().equals(o2.getPrestige())){
                return -1;
            }
        }
        if(o2.getPrice() != null){
            if(!o1.getPrice().equals(o2.getPrice())){
                return -1;
            }
        }

        if(o2.getQuantity() != null){
            if(o1.getQuantity().equals(o2.getQuantity())){
                return -1;
            }
        }

        return 1;
    }
}
