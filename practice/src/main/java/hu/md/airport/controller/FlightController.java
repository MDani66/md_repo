package hu.progmasters.airport.controller;

import hu.progmasters.airport.dto.FlightInfo;
import hu.progmasters.airport.dto.TicketInfo;
import hu.progmasters.airport.dto.command.FlightCreateCommand;
import hu.progmasters.airport.dto.command.FlightModifyCommand;
import hu.progmasters.airport.dto.command.TicketCreateCommand;
import hu.progmasters.airport.service.AirportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/airportapp/flights")
public class FlightController {

    private final AirportService airportService;
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    public FlightController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping
    public ResponseEntity<FlightInfo> saveFlight(@RequestBody FlightCreateCommand command) {
        LOGGER.info("Http request, POST /api/airportapp/flights, body: " + command.toString());
        FlightInfo flightSaved = airportService.saveFlight(command);
        LOGGER.info("Flight created: " + flightSaved.toString());
        return new ResponseEntity<>(flightSaved, HttpStatus.CREATED);
    }

    @GetMapping("/{internalid}")
    public ResponseEntity<FlightInfo> findFlightByInternalId(@PathVariable("internalid") int internalId) {
        LOGGER.info("Http request, GET /api/airportapp/flights/" + internalId);
        FlightInfo flightFound = airportService.findFlightByInternalId(internalId);
        LOGGER.info("Flight found: " + flightFound.toString());
        return new ResponseEntity<>(flightFound, HttpStatus.OK);
    }

    @PutMapping("/{internalid}")
    public ResponseEntity<FlightInfo> modifyFlight(@PathVariable("internalid") int internalId,
                                                   @RequestBody @Valid FlightModifyCommand command) {
        LOGGER.info("Http request, PUT /api/airportapp/flights/" + internalId + " body: " + command.toString());
        FlightInfo flightModified = airportService.modifyFlight(internalId, command);
        LOGGER.info("Flight modified: " + flightModified.toString());
        return new ResponseEntity<>(flightModified, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FlightInfo>> listAllFlights() {
        LOGGER.info("Http request, GET /api/airportapp/flights");
        List<FlightInfo> flights = airportService.listAllFlights();
        LOGGER.info("List all flights: " + flights.toString());
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @DeleteMapping("/{internalid}")
    public ResponseEntity<Void> deleteFlight(@PathVariable("internalid") int internalId) {
        LOGGER.info("Http request, DELETE /api/airportapp/flights/" + internalId);
        airportService.cancelFlight(internalId);
        LOGGER.info("Flight deleted succesfully.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/ticketbookings")
    public ResponseEntity<TicketInfo> createTicket(@RequestBody @Valid TicketCreateCommand command) {
        LOGGER.info("Http request, POST /api/airportapp/flights/ticketbookings, body: " + command.toString());
        TicketInfo ticketSaved = airportService.createTicket(command);
        LOGGER.info("Ticket created and saved: " + ticketSaved.toString());
        return new ResponseEntity<>(ticketSaved, HttpStatus.CREATED);
    }

    @GetMapping("/ticketbookings/{internalid}")
    public ResponseEntity<TicketInfo> findTicketByInternalId(@PathVariable("internalid") int internalId) {
        LOGGER.info("Http request, GET /api/airportapp/flights/ticketbookings/" + internalId);
        TicketInfo ticketFound = airportService.findTicketById(internalId);
        LOGGER.info("Ticket found: " + ticketFound.toString());
        return new ResponseEntity<>(ticketFound, HttpStatus.OK);
    }

    @GetMapping("/ticketbookings")
    public ResponseEntity<List<TicketInfo>> listAllTickets() {
        LOGGER.info("Http request, GET /api/airportapp/flights/ticketbookings");
        List<TicketInfo> tickets = airportService.listAllTickets();
        LOGGER.info(("List all tickets: " + tickets.toString()));
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @DeleteMapping("/ticketbookings/{internalid}")
    public ResponseEntity<Void> deleteTicket(@PathVariable("internalid") int internalId) {
        LOGGER.info("Http request, DELETE /api/airportapp/flights/ticketbookings/" + internalId);
        airportService.deleteTicket(internalId);
        LOGGER.info("Ticket successfully deleted");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
