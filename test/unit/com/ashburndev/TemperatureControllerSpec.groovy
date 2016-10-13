package com.ashburndev



import grails.test.mixin.*
import spock.lang.*

@TestFor(TemperatureController)
@Mock(Temperature)
class TemperatureControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.temperatureInstanceList
            model.temperatureInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.temperatureInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def temperature = new Temperature()
            temperature.validate()
            controller.save(temperature)

        then:"The create view is rendered again with the correct model"
            model.temperatureInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            temperature = new Temperature(params)

            controller.save(temperature)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/temperature/show/1'
            controller.flash.message != null
            Temperature.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def temperature = new Temperature(params)
            controller.show(temperature)

        then:"A model is populated containing the domain instance"
            model.temperatureInstance == temperature
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def temperature = new Temperature(params)
            controller.edit(temperature)

        then:"A model is populated containing the domain instance"
            model.temperatureInstance == temperature
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/temperature/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def temperature = new Temperature()
            temperature.validate()
            controller.update(temperature)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.temperatureInstance == temperature

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            temperature = new Temperature(params).save(flush: true)
            controller.update(temperature)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/temperature/show/$temperature.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/temperature/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def temperature = new Temperature(params).save(flush: true)

        then:"It exists"
            Temperature.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(temperature)

        then:"The instance is deleted"
            Temperature.count() == 0
            response.redirectedUrl == '/temperature/index'
            flash.message != null
    }
}
