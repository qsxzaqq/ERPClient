package ui.FinacialStaff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import rmi.RemoteHelper;
import ui.Main;
import ui.util.AlertUtil;
import vo.*;

import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import ui.model.SaleProcessResultModel;

public class SaleProcessResult_SKandFKDController implements Initializable{
    private Main main;
    private UserVO userVO;
    private ArrayList<TradeVO> mainList;
    RemoteHelper helper = RemoteHelper.getInstance();
    @FXML
    Label label_name;
    @FXML
    Label label_number;
    @FXML
    Label label_type;
    @FXML
    TableView<SaleProcessResultModel> tableView;
    @FXML
    TableColumn tc_type;
    @FXML
    TableColumn tc_number;
    @FXML
    TableColumn tc_choose;




    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }
    public SaleProcessResult_SKandFKDController(){

    }//一个构造函数

    public void setMain(Main main,UserVO uservo,ArrayList<TradeVO> list){
        this.mainList=list;
        tableView.setEditable(true);
        tableView.refresh();
        this.main=main;
        this.userVO=uservo;
        label_name.setText(uservo.getName());
        label_type.setText("收款&付款单");

        ObservableList<SaleProcessResultModel> data = FXCollections.observableArrayList();
        for(TradeVO saleVO:list){
            if(saleVO.getType().equals("Collect")) {
                SaleProcessResultModel model = new SaleProcessResultModel("收款单", saleVO.getNumber());
                data.add(model);
            }
            else {
                SaleProcessResultModel model = new SaleProcessResultModel("付款单", saleVO.getNumber());
                data.add(model);
            }
        }

        tc_number.setCellValueFactory(
                new PropertyValueFactory<>("number")
        );

        tc_type.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );

        tc_choose.setCellValueFactory(
                new PropertyValueFactory<>("checkBox")
        );

        tableView.setItems(data);
    }

    @FXML
    public void gotoSaleProcess(){
        main.gotoSaleProcess(userVO);
    }

    @FXML
    public void logout(){
        main.backToMain();
    }

    @FXML
    public void gotoSaleProcessResultDetail(){
        ObservableList<SaleProcessResultModel> data = tableView.getItems();
        int counter=0;
        for(SaleProcessResultModel model:data){
            if(model.checkBox.isSelected())
                counter++;
        }

        if(counter==0)
            AlertUtil.showWarningAlert("请选择所要查看的单据！");
        else if(counter>=2)
            AlertUtil.showWarningAlert("只能选择一个进行查看！");
        else{
            for(SaleProcessResultModel model : data){
                if(model.checkBox.isSelected()){
                    for(TradeVO vo: mainList){
                        if(model.getNumber().equals(vo.getNumber())){
                            main.gotoSaleProcessDetail_SKandFKD(userVO,vo,mainList);
                            break;
                        }
                    }
                }
            }
        }
    }
    @FXML
    public void gotoFinacialStaffMain(){
        if(userVO.getType().equals("总经理"))
            main.gotoManagerMain(userVO);
        else
            main.gotoFinacialStaffMain(userVO);
    }
}
