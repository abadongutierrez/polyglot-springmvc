package com.jabaddon.practices.polyglotspringmvc.web.assembler

import com.jabaddon.practices.polyglotspringmvc.domain.model.Item
import com.jabaddon.practices.polyglotspringmvc.web.resource.ItemResource
import org.springframework.stereotype.Component

/**
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 */
@Component
class ItemResourceAssembler {
    ItemResource toResource(Item item) {
        ItemResource ir = new ItemResource()
        ir.productName = item.productName()
        ir.quantity = item.quantity()
        ir.total = item.totalPrice()
        ir.unitPrice = item.unitPrice()
        ir.units = item.units()
        ir
    }
}
