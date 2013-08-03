package com.jabaddon.practices.polyglotspringmvc.web.controller
import com.jabaddon.practices.polyglotspringmvc.application.ShoppingListService
import com.jabaddon.practices.polyglotspringmvc.domain.model.ShoppingList
import com.jabaddon.practices.polyglotspringmvc.web.assembler.ShoppingListsResourceAssembler
import com.jabaddon.practices.polyglotspringmvc.web.resource.ShoppingListsResource
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
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

    @Autowired
    ShoppingListsResourceAssembler shoppingListsResourceAssembler

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Void> createShoppingList(@RequestParam("name") String name) {
        LOG.debug("### >> createShoppingList() ###")

        def sl = shoppingListService.createNew(name)
        def headers = new HttpHeaders()
        headers.setLocation(linkTo(ShoppingListsController.class).slash(sl.name()).toUri())

        LOG.debug("### << createShoppingList() ###")
        new ResponseEntity<Void>(headers, HttpStatus.CREATED)
    }

    @RequestMapping(method = RequestMethod.GET, produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE])
    ResponseEntity<ShoppingListsResource> list() {
        LOG.debug("### >> list() ###")

        // JavaConversions: A collection of implicit conversions supporting interoperability between Scala and Java collections.
        def sl = JavaConversions.asJavaList(shoppingListService.findAll())
        LOG.debug("Shopping Lists [{}]", sl.size())
        def headers = new HttpHeaders();
        def response = buildResponse(sl)

        LOG.debug("### << list(): [{}] ###", response)
        new ResponseEntity<ShoppingListsResource>(response, headers, HttpStatus.OK)
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<String> handleGenericException(Exception e) {
        LOG.error("Generic Error", e)
        new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Taking advantage of the Groovy's dynamic capabilities and builders
     */
    def buildResponse(List<ShoppingList> list) {
        return shoppingListsResourceAssembler.toResource(list)
    }
}
