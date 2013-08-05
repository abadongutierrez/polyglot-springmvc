package com.jabaddon.practices.polyglotspringmvc.web.resource;

import com.jabaddon.practices.polyglotspringmvc.domain.model.Units;
import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 */
@XmlRootElement
public class ItemResource extends ResourceSupport {
    @XmlElement
    public volatile String productName;
    @XmlElement
    public volatile Integer quantity;
    @XmlElement
    public volatile Units units;
    @XmlElement
    public volatile Double unitPrice;
    @XmlElement
    public volatile Double total;
}
