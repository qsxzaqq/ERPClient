package ui.Manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import vo.CashRecord;
import rmi.RemoteHelper;
import ui.Main;
import ui.model.PaymentModel;
import ui.util.AlertUtil;
import vo.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Check_CashController implements Initializable{
    private Main main;
    private UserVO userVO;
    private CashVO cashVO;

    RemoteHelper helper = RemoteHelper.getInstance();

    @FXML
    Label label_number;
    @FXML
    Label label_name;
    @FXML
    Label label_sum;
    @FXML
    TextField tf_bank;
    @FXML
    TextField tf_operator;
    @FXML
    TableView<PaymentModel> tableView;
    @FXML
    TableColumn tc_name;
    @FXML
    TableColumn tc_money;
    @FXML
    TableColumn tc_remark;

    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }

    public Check_CashController(){

    }//一个构造函数

    public void setMain(Main main,UserVO uservo,CashVO cashvo){
        this.main=main;
        this.userVO=uservo;
        this.cashVO=cashvo;

        tableView.setEditable(true);
        tableView.refresh();

        label_name.setText(userVO.getName());

        label_number.setText(cashvo.getNumber());
        tf_bank.setText(cashvo.getName_bank());
        tf_operator.setText(cashvo.getOperator().getName());

        label_sum.setText("￥"+cashvo.getSum());

        tf_bank.setEditable(false);
        tf_operator.setEditable(false);

        ArrayList<CashRecord> list = cashvo.getRecordList();

        ObservableList<PaymentModel> data = FXCollections.observableArrayList();

        for(CashRecord record:list){
            PaymentModel model = new PaymentModel(record.getTask(),record.getMoney()+"",record.getRemark());
            data.add(model);
        }

        tc_name.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        tc_money.setCellValueFactory(
                new PropertyValueFactory<>("money")
        );

        tc_remark.setCellValueFactory(
                new PropertyValueFactory<>("remark")
        );

        tableView.setItems(data);
    }
    @FXML
    public void pass() throws MalformedURLException,NotBoundException,RemoteException{
        helper.getCashBLService().pass(cashVO);
        AlertUtil.showInformationAlert("审批成功！");
        main.gotoSeeThrough(userVO);
    }

    @FXML
    public void fail() throws MalformedURLException,NotBoundException,RemoteException{
        helper.getCashBLService().unpass(cashVO);
        AlertUtil.showInformationAlert("审批成功！");
        main.gotoSeeThrough(userVO);
    }

    @FXML
    public void logout(){
        main.backToMain();
    }

    @FXML
    public void gotoSeeThrough(){
        main.gotoSeeThrough(userVO);
    }

    @FXML
    public void gotoManagerMain(){
        main.gotoManagerMain(userVO);
    }
}
