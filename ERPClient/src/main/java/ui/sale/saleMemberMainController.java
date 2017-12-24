package ui.sale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.Main;
import vo.UserVO;

public class saleMemberMainController {

    private Main main;
    private UserVO userVO;
    //客户管理 按钮
    @FXML
    public Button memberButton;
    //进货 按钮
    @FXML
    public Button stockInButton;
    //销售 按钮
    @FXML
    public Button saleButton;
    //退货 按钮
    @FXML
    public Button returnButton;


    //查询方式1 按钮
    @FXML
    public Button memberIDSearchButton;
    //查询方式2 按钮
    @FXML
    public Button memberKindSearchButton;
    //查询方式3 按钮
    @FXML
    public Button memberInfoSearchButton;


    //新增客户 按钮
    @FXML
    public Button memberNewButton;


    //UserInfo 用户名
    @FXML
    public Label userNameLB;


    //右上角 登出 按钮
    @FXML
    public Button logoutButton;
    //右下角 返回上一层 按钮
    @FXML
    public Button backButton;



    //跳转客户管理界面
    @FXML
    public void gotoMember(ActionEvent e){
        main.gotoSaleMember(userVO);
    }
    //跳转进货界面
    @FXML
    public void gotoStockIn(ActionEvent e){
        main.gotoSaleStock(userVO);
    }

    //跳转销售界面
    @FXML
    public void gotoSale(ActionEvent e){
        main.gotoSaleSale(userVO);
    }
    //跳转退货界面
    @FXML
    public void gotoReturn(ActionEvent e){
        main.gotoSaleReturn(userVO);
    }


    //ID 查询 跳转界面
    @FXML
    public void gotoMemberID(ActionEvent e){
        main.gotoSaleMemberIDSearch(userVO);
    }
    //类别查询 跳转界面
    @FXML
    public void gotoMemberKind(ActionEvent e){
        main.gotoSaleMemberKindSearch(userVO);
    }
    //模糊查询 跳转界面
    @FXML
    public void gotoMemberInfo(ActionEvent e){
        main.gotoSaleMemberInfoSearch(userVO);
    }
    //新增客户 跳转界面（增删改查）
    @FXML
    public void memberNew(ActionEvent e){
        main.gotoSaleMemberInfoEdit(userVO,"Add");

    }

    //返回上一层（进货销售人员主界面）
    @FXML
    public void gotoSaleMain(ActionEvent e){
        main.gotoSaleMain(userVO);
    }

    //登出
    @FXML
    public void gotoLog(ActionEvent e){
        userVO.setLogin(false);
        main.gotoLog(userVO.getType());
    }
    public void setMain(Main main,UserVO userVO){
        this.main=main;
        this.userVO=userVO;
        userNameLB.setText("管理员"+userVO.getName());
    }

}
