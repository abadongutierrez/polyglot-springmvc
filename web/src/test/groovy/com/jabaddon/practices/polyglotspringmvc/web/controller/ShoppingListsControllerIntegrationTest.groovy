package com.jabaddon.practices.polyglotspringmvc.web.controller

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup

/**
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = [
    "classpath:META-INF/spring/applicationContext*.xml",
    "file:src/main/webapp/WEB-INF/spring/webmvc-config.xml"
])
class ShoppingListsControllerIntegrationTest {

    static final Logger LOG = LoggerFactory.getLogger(ShoppingListsControllerIntegrationTest.class)

    @Autowired
    WebApplicationContext wac

    MockMvc mockMvc

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void getXmlShouldResponseOkWithContentTypeXml() {
        LOG.debug("### >> getXmlShouldResponseOkWithContentTypeXml() ###")
        mockMvc.perform(get("/shoppingLists").accept(MediaType.TEXT_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_XML))
        LOG.debug("### << getXmlShouldResponseOkWithContentTypeXml() ###")
    }

    @Test
    public void getJsonShouldResponseOkWithContentTypeJson() {
        LOG.debug("### >> getJsonShouldResponseOkWithContentTypeJson() ###")
        mockMvc.perform(get("/shoppingLists").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        LOG.debug("### << getJsonShouldResponseOkWithContentTypeJson() ###")
    }
}
