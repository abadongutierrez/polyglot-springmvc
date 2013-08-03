package com.jabaddon.practices.polyglotspringmvc.web.controller
import com.jabaddon.practices.polyglotspringmvc.domain.model.Item
import com.jabaddon.practices.polyglotspringmvc.domain.model.Units
import com.jabaddon.practices.polyglotspringmvc.domain.repository.ShoppingListRepository
import groovy.json.JsonSlurper
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
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultHandler
import org.springframework.web.context.WebApplicationContext

import static org.hamcrest.CoreMatchers.is
import static org.hamcrest.CoreMatchers.notNullValue
import static org.junit.Assert.assertThat
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
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
    ShoppingListRepository shoppingListRepository

    @Autowired
    WebApplicationContext wac

    MockMvc mockMvc

    @Before
    public void setup() {
        shoppingListRepository.clearAll()
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

    @Test
    public void postShouldCreateANewShoppingList() {
        LOG.debug("### >> postShouldCreateANewShoppingList() ###")

        def expectedName = "A Cool Shopping List!"
        def expectedLocation = linkTo(ShoppingListsController.class).slash(expectedName).toUri().toString()
        LOG.debug("Expected Location: [{}]", expectedLocation)
        mockMvc.perform(post("/shoppingLists")
                    .param("name", expectedName))
                .andDo(new ResultHandler() {
                    void handle(MvcResult mvcResult) throws Exception {
                        LOG.debug("Location: [{}]", mvcResult.response.getHeader("Location"))
                        LOG.debug("Result: [{}]", mvcResult.response.contentAsString)
                    }
                })
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", expectedLocation))

        assertThat(shoppingListRepository.exists(expectedName), is(true))
        assertThat(shoppingListRepository.find(expectedName)?.isEmpty(), is(true))

        LOG.debug("### << postShouldCreateANewShoppingList() ###")
    }

    @Test
    public void getJsonWithSomeShoppingListsShouldResponseAListOfThoseShoppingLists() {
        LOG.debug("### << getJsonWithSomeShoppingListsShouldResponseAListOfThoseShoppingLists() ###")
        def slurper = new JsonSlurper()
        def result = null

        createShoppingListsFixture(3);
        assertThat(shoppingListRepository.findAll().size(), is(3))

        mockMvc.perform(get("/shoppingLists").accept(MediaType.APPLICATION_JSON))
                .andDo(new ResultHandler() {
                    void handle(MvcResult mvcResult) throws Exception {
                        LOG.debug("Content: [{}]", mvcResult.response.contentAsString)
                        result = slurper.parseText(mvcResult.response.contentAsString)
                    }
                })
                .andExpect(status().isOk())
                // ??? .andExpect(jsonPath('$.shoppingLists[*].name', is(collectionWithSize(equalTo(2)))))

        // Using hamcrest
        assertThat(result, is(notNullValue()))
        assertThat(result.shoppingLists.size(), is(3))

        // Using groovy's assert
        assert result != null
        assert result.shoppingLists.size() == 3

        LOG.debug("### >> getJsonWithSomeShoppingListsShouldResponseAListOfThoseShoppingLists() ###")
    }

    def createShoppingListsFixture(int i) {
        (1..i).each {
            LOG.debug("Creando fixture ${it}")
            def sl = shoppingListRepository.createNew("Shopping List ${it}")
            sl.addItem(new Item("Product 1", 1, Units.Liters))
            sl.addItem(new Item("Product 2", 2, Units.Pieces))
            sl.addItem(new Item("Product 3", 3, Units.Pieces))
        }
    }
}
