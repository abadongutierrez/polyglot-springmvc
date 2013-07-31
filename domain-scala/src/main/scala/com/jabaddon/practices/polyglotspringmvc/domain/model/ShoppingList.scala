package com.jabaddon.practices.polyglotspringmvc.domain.model

/**
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 */
class ShoppingList {
    var items: List[Item] = List[Item]()

    def isEmpty(): Boolean = items.size == 0

    def addItem(item: Item) = items ::= item

    def totalItems: Int = items.size

    def buyItem(productName: String, unitPrice: Double) = {
        items.filter(_.productName.equalsIgnoreCase(productName))(0).unitPrice = unitPrice
    }

    def totalSpent: Double = items.foldLeft(0.0)(_ + _.totalPrice)

}
