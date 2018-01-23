package by.baranovskaya.command.user;

import by.baranovskaya.command.Command;
import by.baranovskaya.entity.Client;
import by.baranovskaya.entity.Order;
import by.baranovskaya.service.OrderService;
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

    private OrderService orderService;

    public ReservationCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String[] services = request.getParameterValues(PARAM_SERVICES);
        HttpSession session = request.getSession(true);
        Client client = (Client) session.getAttribute("user");
        Order order = new Order();
        order.setArrivalDate(request.getParameter(PARAM_ARRIVAL_DATE));
        order.setDepartureDate(request.getParameter(PARAM_DEPARTURE_DATE));
        System.out.println(client.getIdClient()+"" +order);

        return null;
    }
}
