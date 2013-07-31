package com.jabaddon.practices.polyglotspringmvc.domain.repository.impl

import com.jabaddon.practices.polyglotspringmvc.domain.repository.ShoppingListRepository
import com.jabaddon.practices.polyglotspringmvc.domain.model.{Units, Item, ShoppingList}

/**
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 */
class InMemoryShoppingListRepositoryImpl extends ShoppingListRepository {
    var shoppingLists: List[ShoppingList] = List[ShoppingList]()
    var sl = new ShoppingList()
    sl.addItem(new Item("Milk", 2, Units.Liters))
    shoppingLists ::= sl

    def findAll: List[ShoppingList] = shoppingLists
}
