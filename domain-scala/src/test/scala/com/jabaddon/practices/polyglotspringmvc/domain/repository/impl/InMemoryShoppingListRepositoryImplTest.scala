package com.jabaddon.practices.polyglotspringmvc.domain.repository.impl

import org.scalatest.{BeforeAndAfterEach, FunSuite}
import com.jabaddon.practices.polyglotspringmvc.domain.repository.ShoppingListRepository
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 */
@RunWith(classOf[JUnitRunner])
class InMemoryShoppingListRepositoryImplTest extends FunSuite with BeforeAndAfterEach {
    var repository: ShoppingListRepository = null

    override def beforeEach() {
        repository = new InMemoryShoppingListRepositoryImpl()
    }

    test("exists() after adding a Shopping List should return true") {
        repository.createNew("A new Shopping List")

        assert(repository.exists("A new Shopping List"))
    }

    test("find() after adding a Shopping List should return the Shopping List") {
        repository.createNew("1")

        assert("1".equals(repository.find("1").name))
    }
}
