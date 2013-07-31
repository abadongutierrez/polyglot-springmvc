package com.jabaddon.practices.polyglotspringmvc.domain.model

/**
 * TODO: descripcion
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 *         Date: 7/30/13
 *         Time: 10:46 PM
 */
class Item(val productName: String, val quantity: Int, val units: Units.Value) {
    var unitPrice: Double = 0.0

    def totalPrice: Double = quantity * unitPrice
}
