package com.jabaddon.practices.polyglotspringmvc.application.impl

import com.jabaddon.practices.polyglotspringmvc.application.{ShoppingListNotFoundException, ShoppingListService}
import com.jabaddon.practices.polyglotspringmvc.domain.model.ShoppingList
import com.jabaddon.practices.polyglotspringmvc.domain.repository.ShoppingListRepository

/**
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 */
class ShoppingListServiceImpl(val shoppingListRepository: ShoppingListRepository) extends ShoppingListService {

    def findAll: List[ShoppingList] = shoppingListRepository.findAll

    def createNew(name: String): ShoppingList = shoppingListRepository.createNew(name)

    def delete(name:String) = shoppingListRepository.delete(name)

    def find(name: String): ShoppingList = {
        var sl = shoppingListRepository.find(name)
        if (sl == null) {
            throw new ShoppingListNotFoundException
        }
        sl
    }
}
