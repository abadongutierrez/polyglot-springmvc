package com.jabaddon.practices.polyglotspringmvc.web.assembler

import com.jabaddon.practices.polyglotspringmvc.domain.model.ShoppingList
import com.jabaddon.practices.polyglotspringmvc.web.resource.ShoppingListsResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 */
@Component
class ShoppingListsResourceAssembler {
    @Autowired
    ShoppingListResourceAssembler shoppingListResourceAssembler

    ShoppingListsResource toResource(List<ShoppingList> shoppingLists) {
        def slsr = new ShoppingListsResource()
        shoppingLists.each {
            slsr.shoppingLists.add(shoppingListResourceAssembler.toResource(it))
        }
        slsr
    }
}
