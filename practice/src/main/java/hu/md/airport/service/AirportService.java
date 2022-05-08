package hu.progmasters.airport.service;

import hu.progmasters.airport.domain.Airplane;
import hu.progmasters.airport.domain.Airport;
import hu.progmasters.airport.domain.Flight;
import hu.progmasters.airport.domain.Ticket;
import hu.progmasters.airport.dto.AirportDto;
import hu.progmasters.airport.dto.FlightInfo;
import hu.progmasters.airport.dto.TicketInfo;
import hu.progmasters.airport.dto.command.AirportCreateAndUpdateCommand;
import hu.progmasters.airport.dto.command.FlightCreateCommand;
import hu.progmasters.airport.dto.command.FlightModifyCommand;
import hu.progmasters.airport.dto.command.TicketCreateCommand;
import hu.progmasters.airport.exceptionhandling.*;
import hu.progmasters.airport.repository.AirplaneRepository;
import hu.progmasters.airport.repository.AirportRepository;
import hu.progmasters.airport.repository.FlightRepository;
import hu.progmasters.airport.repository.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirportService {

    private final AirportRepository airportRepository;
    private final TicketRepository ticketRepository;
    private final AirplaneRepository airplaneRepository;
    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AirportService(AirportRepository airportRepository, TicketRepository ticketRepository, AirplaneRepository airplaneRepository, FlightRepository flightRepository, ModelMapper modelMapper) {
        this.airportRepository = airportRepository;
        this.ticketRepository = ticketRepository;
        this.airplaneRepository = airplaneRepository;
        this.flightRepository = flightRepository;
        this.modelMapper = modelMapper;
    }

    public AirportDto createAirport(AirportCreateAndUpdateCommand command) {
        Airport airportToSave = modelMapper.map(command, Airport.class);
        Airport airportSaved = airportRepository.saveAirport(airportToSave);
        return modelMapper.map(airportSaved, AirportDto.class);
    }

    public AirportDto findAirportByInternalId(int internalId) {
        Optional<Airport> airportToFind = airportRepository.findByInternalId(internalId);
        if (airportToFind.isEmpty() || airportToFind.get().isDeleted()) {
            throw new AirportNotFoundException(internalId);
        } else {
            return modelMapper.map(airportToFind.get(), AirportDto.class);
        }

    }

    public List<AirportDto> listAllAirports() {
        return airportRepository.listAll().stream()
                .filter(airport -> !airport.isDeleted())
                .map(airport -> modelMapper.map(airport, AirportDto.class))
                .collect(Collectors.toList());
    }

    public AirportDto modifyAirport(int internalId, AirportCreateAndUpdateCommand command) {
        Optional<Airport> airportToFind = airportRepository.findByInternalId(internalId);
        if (airportToFind.isEmpty() || airportToFind.get().isDeleted()) {
            throw new AirportNotFoundException(internalId);
        } else {
            Airport airportModified = airportToFind.get();
            airportModified.setCity(command.getCity())
                    .setCountry(command.getCountry())
                    .setName(command.getName())
                    .setIdentifier(command.getIdentifier());
            return modelMapper.map(airportModified, AirportDto.class);
        }
    }

    public void deleteAirport(int internalId) {
        Optional<Airport> airportToFind = airportRepository.findByInternalId(internalId);
        if (airportToFind.isEmpty() || airportToFind.get().isDeleted()) {
            throw new AirportNotFoundException(internalId);
        } else {
            airportRepository.delete(internalId);
        }
    }

    public FlightInfo saveFlight(FlightCreateCommand command) {
        Flight flightToSave = new Flight();
        command = checkCommand(command);
        flightToSave.setIdentifier(command.getIdentifier())
                .setAirplane(airplaneRepository.findById(command.getAirplaneId()).get())
                .setStartAirport(airportRepository.findByInternalId(command.getStartAirportId()).get())
                .setLandingAirport(airportRepository.findByInternalId(command.getLandingAirportId()).get())
                .setTimeOfDeparture(command.getTimeOfDeparture())
                .setTimeOfLanding(command.getTimeOfLanding())
                .setNumberOfFreeSeats(flightToSave.getAirplane().getNumberOfSeats());

        Flight flightSaved = flightRepository.saveFlight(flightToSave);
        return modelMapper.map(flightSaved, FlightInfo.class);
    }

    public FlightCreateCommand checkCommand(FlightCreateCommand command) {
        Optional<Airplane> airplaneToFind = airplaneRepository.findById(command.getAirplaneId());
        if (airplaneToFind.isEmpty() || airplaneToFind.get().isDeleted()) {
            throw new AirplaneNotFoundException(command.getAirplaneId());
        } else {
            Optional<Airport> startAirport = airportRepository.findByInternalId(command.getStartAirportId());
            Optional<Airport> landingAirport = airportRepository.findByInternalId(command.getLandingAirportId());
            if (startAirport.isEmpty() || startAirport.get().isDeleted()) {
                throw new AirportNotFoundException(command.getStartAirportId());
            }
            if (landingAirport.isEmpty() || landingAirport.get().isDeleted()) {
                throw new AirportNotFoundException(command.getLandingAirportId());
            }
            if (command.getTimeOfDeparture().isAfter(command.getTimeOfLanding())) {
                throw new FlightTimeNotValidException(command.getIdentifier());
            } else {
                return command;
            }
        }
    }

    public FlightInfo findFlightByInternalId(int internalId) {
        Optional<Flight> flightToFind = flightRepository.findByInternalId(internalId);
        if (flightToFind.isEmpty() || flightToFind.get().isDeleted()) {
            throw new FlightNotFoundException(internalId);
        } else {
            return modelMapper.map(flightToFind.get(), FlightInfo.class);
        }
    }

    public FlightInfo modifyFlight(int internalId, FlightModifyCommand command) {
        Optional<Flight> flightToModify = flightRepository.findByInternalId(internalId);
        if (flightToModify.isEmpty() || flightToModify.get().isDeleted()) {
            throw new FlightNotFoundException(internalId);
        } else {
            Flight flightUpdated = flightToModify.get();
            if (command.getTimeOfDeparture().isAfter(command.getTimeOfLanding())) {
                throw new FlightTimeNotValidException(flightUpdated.getIdentifier());
            } else {
                flightUpdated.setTimeOfDeparture(command.getTimeOfDeparture())
                        .setTimeOfLanding(command.getTimeOfLanding());
                return modelMapper.map(flightUpdated, FlightInfo.class);
            }
        }

    }

    public List<FlightInfo> listAllFlights() {
        return flightRepository.listAll().stream()
                .filter(flight -> !flight.isDeleted())
                .map(flight -> modelMapper.map(flight, FlightInfo.class))
                .collect(Collectors.toList());
    }

    public TicketInfo createTicket(TicketCreateCommand command) {
        Optional<Flight> flightToFind = flightRepository.findByInternalId(command.getFlightId());
        if (flightToFind.isEmpty() || flightToFind.get().isDeleted()) {
            throw new FlightNotFoundException(command.getFlightId());
        } else {
            Flight flightToBook = flightToFind.get();
            if (flightToBook.getNumberOfFreeSeats() > 0) {
                Ticket ticketToSave = new Ticket().setName(command.getName())
                        .setEmail(command.getEmail())
                        .setFlight(flightToBook);
                flightToBook.setNumberOfFreeSeats(flightToBook.getNumberOfFreeSeats() - 1);
                Ticket ticketSaved = ticketRepository.saveTicket(ticketToSave);
                flightToBook.getTicketIds().add(ticketSaved.getInternalId());
                return modelMapper.map(ticketSaved, TicketInfo.class);
            } else {
                throw new AirplaneIsFullException(flightToBook.getInternalId());
            }
        }
    }

    public TicketInfo findTicketById(int internalId) {
        Optional<Ticket> ticketToFind = ticketRepository.findById(internalId);
        if (ticketToFind.isEmpty() || ticketToFind.get().isDeleted()) {
            throw new TicketNotFoundException(internalId);
        } else {
            return modelMapper.map(ticketToFind.get(), TicketInfo.class);
        }
    }

    public List<TicketInfo> listAllTickets() {
        return ticketRepository.listAll().stream()
                .filter(ticket -> !ticket.isDeleted())
                .map(ticket -> modelMapper.map(ticket, TicketInfo.class))
                .collect(Collectors.toList());
    }

    public void deleteTicket(int internalId) {
        Optional<Ticket> ticketToFind = ticketRepository.findById(internalId);
        if (ticketToFind.isEmpty() || ticketToFind.get().isDeleted()) {
            throw new TicketNotFoundException(internalId);
        } else {
            ticketToFind.get().getFlight()
                    .setNumberOfFreeSeats(ticketToFind.get().getFlight().getNumberOfFreeSeats() + 1);
            ticketToFind.get().setDeleted(true);
        }
    }

    public void cancelFlight(int internalId) {
        Optional<Flight> flightToDelete = flightRepository.findByInternalId(internalId);
        if (flightToDelete.isEmpty() || flightToDelete.get().isDeleted()) {
            throw new FlightNotFoundException(internalId);
        } else {
            flightToDelete.get().setDeleted(true);
            List<Integer> tickets = flightToDelete.get().getTicketIds();
            for (Integer integer : tickets) {
                Optional<Ticket> ticket = ticketRepository.findById(integer);
                if (ticket.isPresent() && !ticket.get().isDeleted()) {
                    ticket.get().getFlight().setNumberOfFreeSeats(ticket.get().getFlight().getNumberOfFreeSeats() + 1);
                    ticket.get().setDeleted(true);
                }
            }
        }
    }
}
