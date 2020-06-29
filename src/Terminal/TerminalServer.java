package Terminal;

public class TerminalServer {


    private boolean serverIsRunning;
    private Double allMoney = 0.;

    public TerminalServer(Long accountId) {
        serverIsRunning = true;
        if (accountId > 0)
            allMoney = 50521.236; //взять эту инфу из бд
    }

    //public String getAccountState(Double countMoney) {
    public String getAccountState(MyDouble countMoney) {
        countMoney.retDouble = allMoney;
        return changeState(1, 0);
    }

    public String putMoney(int money) {
        return changeState(2, money);
    }

    public String getMoney(int money) {
        return changeState(3, money);
    }

    public void switchOffServer() {
        serverIsRunning = false;
    }

    private String changeState(int trigger, int money) {
        String returnString = "";
        if (check()) {
            if (trigger == 1) {
             //   countMoney = allMoney;
                returnString = "Запрос выполнен.";
            }
            else if (trigger == 2) {
                allMoney += money;
                returnString = "Операция выполнена. Деньги на вашем счете.";
            }
            else if (trigger == 3) {
                if ((allMoney - money) < 0. ) {
                    returnString = "Недостаточно денег на счете для выполнения операции.";
                }
                else {
                    allMoney -= money;
                }
            }

        }
        else
        {
            returnString = "Сервер недоступен. Попробуйте повторить попытку через несколько минут.";
        }

        return returnString;
    }


    private boolean check() {
        if (!serverIsRunning)
            throw new RuntimeException("Server is not available. param serverIsRunning=false.");
        return serverIsRunning;
    }




}
