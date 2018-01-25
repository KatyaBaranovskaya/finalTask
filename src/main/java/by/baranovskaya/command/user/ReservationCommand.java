package by.baranovskaya.command.user;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.entity.Client;
import by.baranovskaya.entity.Order;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

public class ReservationCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ReservationCommand.class);

    private final static String PARAM_ARRIVAL_DATE = "arrival_date";
    private final static String PARAM_DEPARTURE_DATE = "departure_date";
    private final static String PARAM_SERVICES = "services";
    private final static String PARAM_ROOM_NUMBER = "roomNumber";
    private final static String PARAM_NO_PERSONS = "noPersons";
    private final static String PARAM_CLASS_APARTMENT = "classApartment";

    private OrderService orderService;

    public ReservationCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession(true);
        Client client = (Client) session.getAttribute("client");
        String[] services = request.getParameterValues(PARAM_SERVICES);
        Date arrivalDate = Date.valueOf(request.getParameter(PARAM_ARRIVAL_DATE));
        Date departureDate = Date.valueOf(request.getParameter(PARAM_DEPARTURE_DATE));
        Order order = new Order();
        order.setIdClient(client.getIdClient());
        order.setArrivalDate(arrivalDate);
        order.setDepartureDate(departureDate);
        order.setNoPersons(Integer.parseInt(request.getParameter(PARAM_NO_PERSONS)));
        order.setClassApartment(Integer.parseInt(request.getParameter(PARAM_CLASS_APARTMENT)));

        try {
            if(session.getAttribute(PARAM_ROOM_NUMBER) == null) {
                if (orderService.doOrder(order)) {
                    orderService.orderService(client.getIdClient(), arrivalDate, departureDate, services);
                    page = PageConstant.PATH_PAGE_MAIN;
                } else {
                    //TODO err
                    page = PageConstant.PATH_PAGE_USER_RESERVATION;
                }
            } else {
                order.setRoomNumber((Integer) session.getAttribute(PARAM_ROOM_NUMBER));
                if (orderService.doConcreteOrder(order)) {
                    orderService.orderService(client.getIdClient(), arrivalDate, departureDate, services);
                    page = PageConstant.PATH_PAGE_MAIN;
                } else {
                    //TODO err
                    page = PageConstant.PATH_PAGE_USER_RESERVATION;
                }
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }

        session.setAttribute("roomNumber", null);
        return page;
    }
}
