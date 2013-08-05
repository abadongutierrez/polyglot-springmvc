package com.jabaddon.practices.polyglotspringmvc.web.assembler

import com.jabaddon.practices.polyglotspringmvc.domain.model.ShoppingList
import com.jabaddon.practices.polyglotspringmvc.web.controller.ShoppingListsController
import com.jabaddon.practices.polyglotspringmvc.web.resource.ShoppingListResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import scala.collection.JavaConversions

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo

/**
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 */
@Component
class ShoppingListResourceAssembler {

    @Autowired
    ItemResourceAssembler itemResourceAssembler

    ShoppingListResource toResource(ShoppingList shoppingList) {
        def slr = new ShoppingListResource()
        slr.name = shoppingList.name()
        JavaConversions.asJavaList(shoppingList.items()).each {
            slr.items.add(itemResourceAssembler.toResource(it))
        }
        slr.add(linkTo(ShoppingListsController.class).slash(slr.name).withSelfRel())
        slr
    }
}
