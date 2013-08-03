package com.jabaddon.practices.polyglotspringmvc.web.assembler

import com.jabaddon.practices.polyglotspringmvc.domain.model.ShoppingList
import com.jabaddon.practices.polyglotspringmvc.web.resource.ShoppingListResource
import org.springframework.stereotype.Component

/**
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 */
@Component
class ShoppingListResourceAssembler {
    ShoppingListResource toResource(ShoppingList shoppingList) {
        def slr = new ShoppingListResource()
        slr.name = shoppingList.name()
        slr
    }
}
