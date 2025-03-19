package ui;

import model.UserFinancesList;

public class BalanceSheet {
    private UserFinancesList userList;

    public BalanceSheet(UserFinancesList userList) {
        this.userList = userList;
    }

    public void setList(UserFinancesList userList) {
        this.userList = userList;
    }

    public UserFinancesList getList() {
        return this.userList;
    }
}
