package dbUtils;

import java.util.ArrayList;

public class SelectOptionList {

    public String dbError = "";
    private ArrayList<SelectOption> selectOptionList = new ArrayList();

    public SelectOptionList() {
    }

    public void addOption(SelectOption option) {
        this.selectOptionList.add(option);
    }
}