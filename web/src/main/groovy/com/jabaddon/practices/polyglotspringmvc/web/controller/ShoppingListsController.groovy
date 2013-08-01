package com.jabaddon.practices.polyglotspringmvc.web.controller

import com.jabaddon.practices.polyglotspringmvc.application.ShoppingListService
import com.jabaddon.practices.polyglotspringmvc.domain.model.Item
import com.jabaddon.practices.polyglotspringmvc.domain.model.ShoppingList
import groovy.json.JsonBuilder
import groovy.xml.MarkupBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import scala.collection.JavaConversions

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo

/**
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 */
@Controller
@RequestMapping("/shoppingLists")
class ShoppingListsController {
    static final Logger LOG = LoggerFactory.getLogger(ShoppingListsController)

    @Autowired
    ShoppingListService shoppingListService

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> createShoppingList(@RequestParam("name") String name) {
        LOG.debug("### >> createShoppingList() ###")

        def id = shoppingListService.createNew(name)
        def headers = new HttpHeaders()
        headers.setLocation(linkTo(ShoppingListsController.class).slash(id).toUri())

        LOG.debug("### << createShoppingList() ###")
        new ResponseEntity<Void>(headers, HttpStatus.CREATED)
    }

    @RequestMapping(method = RequestMethod.GET, produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE])
    ResponseEntity<String> list(@RequestHeader("Accept") String accept) {
        LOG.debug("### >> list() ###")

        // JavaConversions: A collection of implicit conversions supporting interoperability between Scala and Java collections.
        def sl = JavaConversions.asJavaList(shoppingListService.findAll())
        LOG.debug("Shopping Lists [{}]", sl.size())
        def headers = new HttpHeaders();
        def response = buildResponse(sl, accept)
        // NOTE: I dont like this
        headers.setContentType(MediaType.parseMediaType(accept))

        LOG.debug("### << list(): [{}] ###", response)
        new ResponseEntity<String>(response, headers, HttpStatus.OK)
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<String> handleGenericException(Exception e) {
        LOG.error("Generic Error", e)
        new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Taking advantage of the Groovy's dynamic capabilities and builders
     */
    def buildResponse(List list, String accept) {
        def writer = new StringWriter()
        def xml = MediaType.APPLICATION_JSON_VALUE.equals(accept) ?
            new JsonBuilder() :
            new MarkupBuilder(writer)

        xml.shoppingLists {
            list.each { ShoppingList sl ->
                shoppingList {
                    JavaConversions.asJavaList(sl.items).each { Item sli ->
                        item {
                            productName(sli.productName())
                            quantity(sli.quantity())
                            units(sli.units())
                        }
                    }
                }
            }
        }
        MediaType.APPLICATION_JSON_VALUE.equals(accept) ? xml.toString() : writer.toString()
    }
}
