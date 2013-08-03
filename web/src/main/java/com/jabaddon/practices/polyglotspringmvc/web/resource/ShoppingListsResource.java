package com.jabaddon.practices.polyglotspringmvc.web.resource;

import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 */
@XmlRootElement(name = "shoppingLists")
public class ShoppingListsResource extends ResourceSupport {
    @XmlElement(name = "shoppingLists")
    public volatile List<ShoppingListResource> shoppingLists = new ArrayList<ShoppingListResource>();
}
