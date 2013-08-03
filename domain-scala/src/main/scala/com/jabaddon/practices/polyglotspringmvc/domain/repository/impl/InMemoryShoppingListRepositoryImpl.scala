package com.jabaddon.practices.polyglotspringmvc.domain.repository.impl

import com.jabaddon.practices.polyglotspringmvc.domain.repository.ShoppingListRepository
import com.jabaddon.practices.polyglotspringmvc.domain.model.{Units, Item, ShoppingList}

/**
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 */
class InMemoryShoppingListRepositoryImpl extends ShoppingListRepository {
    var shoppingLists: List[ShoppingList] = List[ShoppingList]()

    def findAll: List[ShoppingList] = shoppingLists

    def find(name: String): ShoppingList = {
        if (!shoppingLists.filter(_.name == name).isEmpty) {
            return shoppingLists.filter(_.name == name)(0)
        }
        null
    }

    def createNew(name: String): ShoppingList = {
        val newShoppingList = new ShoppingList(name)
        shoppingLists ::= newShoppingList
        newShoppingList
    }

    def exists(name: String): Boolean = {
        shoppingLists.filter(_.name == name).size > 0
    }

    def clearAll = shoppingLists = List[ShoppingList]()
}
