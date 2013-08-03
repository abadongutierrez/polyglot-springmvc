package com.jabaddon.practices.polyglotspringmvc.web.resource;

import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 */
@XmlRootElement(name = "shoppingList")
public class ShoppingListResource extends ResourceSupport {
    @XmlElement
    public volatile String name;
}
