package com.jabaddon.practices.polyglotspringmvc.domain.model

import org.scalatest.{BeforeAndAfterEach, FunSuite}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

/**
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 */
@RunWith(classOf[JUnitRunner])
class ShoppingListTest extends FunSuite with BeforeAndAfterEach {
    var shoppingList: ShoppingList = null

    override def beforeEach() {
        shoppingList = new ShoppingList
    }

    test("A new shopping list should be empty") {
        assert(shoppingList.isEmpty == true)
    }

    test("A shopping list with some items should not be empty") {
        shoppingList.addItem(new Item("Milk", 2, Units.Liters))
        shoppingList.addItem(new Item("Tequila", 1, Units.Pieces))
        assert(shoppingList.isEmpty == false)
    }

    test("A shopping list with 3 items should have 3 items") {
        shoppingList.addItem(new Item("Milk", 2, Units.Liters))
        shoppingList.addItem(new Item("Tequila", 1, Units.Pieces))
        shoppingList.addItem(new Item("Tomatos", 3, Units.Kilograms))
        assert(shoppingList.totalItems == 3)
    }

    test("A shopping list with not bought items should have 0 as total spent") {
        shoppingList.addItem(new Item("Milk", 2, Units.Liters))
        shoppingList.addItem(new Item("Tequila", 1, Units.Pieces))
        shoppingList.addItem(new Item("Tomatos", 3, Units.Kilograms))
        assert(shoppingList.totalSpent == 0.0)
    }

    // TODO: Fix the name
    test("A shopping list with _") {
        shoppingList.addItem(new Item("Milk", 2, Units.Liters))
        shoppingList.addItem(new Item("Tequila", 1, Units.Pieces))
        shoppingList.addItem(new Item("Tomatos", 3, Units.Kilograms))

        shoppingList.buyItem("Milk", 15.0)

        assert(shoppingList.totalSpent == 30.0)
    }
}
