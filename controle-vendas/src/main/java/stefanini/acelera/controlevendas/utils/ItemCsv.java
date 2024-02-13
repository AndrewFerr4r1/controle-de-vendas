package stefanini.acelera.controlevendas.utils;

import java.util.ArrayList;
import java.util.List;

public class ItemCsv {

    private String code;

    private String cpf;

    private String amount;

    private String date;

    public ItemCsv(String code, String cpf, String amount, String date) {
        this.code = code;
        this.cpf = cpf;
        this.amount = amount;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ItemCsv{" +
                "code='" + code + '\'' +
                ", cpf='" + cpf + '\'' +
                ", amount='" + amount + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
