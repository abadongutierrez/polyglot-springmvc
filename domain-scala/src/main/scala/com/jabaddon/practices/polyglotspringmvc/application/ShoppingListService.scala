package com.jabaddon.practices.polyglotspringmvc.application

import com.jabaddon.practices.polyglotspringmvc.domain.model.ShoppingList

/**
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 */
trait ShoppingListService {
    def findAll: List[ShoppingList]
    def createNew(name: String): ShoppingList
}
