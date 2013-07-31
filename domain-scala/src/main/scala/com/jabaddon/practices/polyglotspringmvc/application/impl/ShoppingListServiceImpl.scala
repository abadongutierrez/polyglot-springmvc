package com.jabaddon.practices.polyglotspringmvc.application.impl

import com.jabaddon.practices.polyglotspringmvc.application.ShoppingListService
import com.jabaddon.practices.polyglotspringmvc.domain.model.ShoppingList
import com.jabaddon.practices.polyglotspringmvc.domain.repository.ShoppingListRepository

/**
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 */
class ShoppingListServiceImpl(val shoppingListRepository: ShoppingListRepository) extends ShoppingListService {

    def findAll: List[ShoppingList] = shoppingListRepository.findAll
}
