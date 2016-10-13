package com.ashburndev



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TemperatureController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Temperature.list(params), model:[temperatureInstanceCount: Temperature.count()]
    }

    def show(Temperature temperatureInstance) {
        respond temperatureInstance
    }

    def create() {
        respond new Temperature(params)
    }

    @Transactional
    def save(Temperature temperatureInstance) {
        if (temperatureInstance == null) {
            notFound()
            return
        }

        if (temperatureInstance.hasErrors()) {
            respond temperatureInstance.errors, view:'create'
            return
        }

        temperatureInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'temperature.label', default: 'Temperature'), temperatureInstance.id])
                redirect temperatureInstance
            }
            '*' { respond temperatureInstance, [status: CREATED] }
        }
    }

    def edit(Temperature temperatureInstance) {
        respond temperatureInstance
    }

    @Transactional
    def update(Temperature temperatureInstance) {
        if (temperatureInstance == null) {
            notFound()
            return
        }

        if (temperatureInstance.hasErrors()) {
            respond temperatureInstance.errors, view:'edit'
            return
        }

        temperatureInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Temperature.label', default: 'Temperature'), temperatureInstance.id])
                redirect temperatureInstance
            }
            '*'{ respond temperatureInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Temperature temperatureInstance) {

        if (temperatureInstance == null) {
            notFound()
            return
        }

        temperatureInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Temperature.label', default: 'Temperature'), temperatureInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'temperature.label', default: 'Temperature'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
