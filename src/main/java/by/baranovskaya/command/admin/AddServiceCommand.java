package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.entity.Room;
import by.baranovskaya.entity.Service;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.AdminService;
import by.baranovskaya.service.HotelService;
import by.baranovskaya.validation.Validation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddServiceCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AddServiceCommand.class);

    private final static String PARAM_TYPE = "typeService";
    private final static String PARAM_PRICE = "price";
    private HotelService hotelService;

    public AddServiceCommand(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Service service = new Service();
        service.setTypeService(request.getParameter(PARAM_TYPE));
        service.setPrice(Double.parseDouble(request.getParameter(PARAM_PRICE)));

        if(Validation.validateService(service)){
            try {
                if(hotelService.addService(service)){
                    page = PageConstant.PATH_PAGE_ADMIN_SERVICES;
                } else{
                    //TODO err
                    page = PageConstant.PATH_PAGE_ADMIN_ADD_SERVICE;
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            //TODO warn incorrect info
            page = PageConstant.PATH_PAGE_ADMIN_ADD_SERVICE;;
        }
        return page;
    }
}
