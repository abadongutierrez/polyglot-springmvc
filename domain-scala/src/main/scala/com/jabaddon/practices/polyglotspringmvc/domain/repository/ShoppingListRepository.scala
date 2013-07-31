package com.jabaddon.practices.polyglotspringmvc.domain.repository

import com.jabaddon.practices.polyglotspringmvc.domain.model.ShoppingList

/**
 * TODO: descripcion
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 *         Date: 7/31/13
 *         Time: 3:22 PM
 */
trait ShoppingListRepository {
    def findAll: List[ShoppingList]
}
