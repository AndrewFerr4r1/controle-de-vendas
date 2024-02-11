package stefanini.acelera.controlevendas.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stefanini.acelera.controlevendas.utils.Utils;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {

    @GetMapping("/test")
    public String test(){
        return Utils.searchCep("04890090");
    }


}
