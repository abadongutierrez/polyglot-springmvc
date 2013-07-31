package com.jabaddon.practices.polyglotspringmvc.web.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 */
@Controller
@RequestMapping("/test")
class TestController {

    @RequestMapping(method = RequestMethod.GET)
    def String test(Model m) {
        "test/view"
    }
}
