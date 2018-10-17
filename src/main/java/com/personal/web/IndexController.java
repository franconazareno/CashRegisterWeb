package com.personal.web;

import com.personal.entity.Dollar;
import com.personal.entity.Summary;
import com.personal.entity.Transaction;
import com.personal.service.CashRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private CashRegisterService cashRegisterService;

    @RequestMapping(value = "/welcome.html", method = RequestMethod.GET)
    public String welcome(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "welcome";
    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index(Model model) {
        Summary summary = cashRegisterService.findLastSummary();
        List<Dollar> list = prepareList(summary);
        int sum = summary.getValueSum();

        model.addAttribute("list", list);
        model.addAttribute("sum", sum);
        return "index";
    }

    @RequestMapping(value = "/init.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String do_init(HttpServletRequest request, HttpServletResponse response) {
        boolean success = cashRegisterService.init();
        if (success) {
            return returnResult("Success", 0, request, response);
        } else {
            return returnResult("Fail", 1, request, response);
        }
    }

    @RequestMapping(value = "/action.json", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String do_action(@RequestBody Map<String,String> requestParam, HttpServletRequest request, HttpServletResponse response) {
        logger.info("do_action requestParam = {}",requestParam);
        String missingParam = checkMissingParam(requestParam);
        if (missingParam != null) {
            return returnResult("Missing parameter: " + missingParam, 2, request, response);
        }

        String invalidParam = checkInvalidParam(requestParam);
        if (invalidParam != null) {
            return returnResult("Invalid parameter: " + invalidParam, 3, request, response);
        }

        boolean success;
        if (requestParam.get("type").equals("change")) {
            success = cashRegisterService.change(Integer.parseInt(requestParam.get("cash")));
            Transaction transaction = cashRegisterService.findLastTransaction();
            String usage = "$ 20 = " + transaction.getTwenty() +
                    "<br>$ 10 = " + transaction.getTen() +
                    "<br>$ 5 = " + transaction.getFive() +
                    "<br>$ 2 = " + transaction.getTwo() +
                    "<br>$ 1 = " + transaction.getOne();

            if (success) {
                return returnResult("Success", 0, usage, request, response);
            }
        } else {
            Transaction transaction = new Transaction.Builder()
                    .type(requestParam.get("type"))
                    .one(Integer.parseInt(requestParam.get("one")))
                    .two(Integer.parseInt(requestParam.get("two")))
                    .five(Integer.parseInt(requestParam.get("five")))
                    .ten(Integer.parseInt(requestParam.get("ten")))
                    .twenty(Integer.parseInt(requestParam.get("twenty")))
                    .build();

            success = cashRegisterService.save(transaction);
            if (success) {
                return returnResult("Success", 0, request, response);
            }
        }

        return returnResult("Fail", 1, request, response);
    }

}
