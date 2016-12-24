package com.saiton.ccs.scale;

import com.saiton.ccs.base.UserPermission;
import com.saiton.ccs.base.UserSession;
import com.saiton.ccs.msgbox.MessageBox;
import com.saiton.ccs.msgbox.SimpleMessageBoxFactory;
import com.saiton.ccs.popup.ItemInfoPopup;
import com.saiton.ccs.popup.ServiceInfoPopup;
import com.saiton.ccs.printerservice.ReportPath;
import com.saiton.ccs.salesdao.ServiceDAO;
import com.saiton.ccs.uihandle.ReportGenerator;
import com.saiton.ccs.uihandle.StagePassable;
import com.saiton.ccs.uihandle.UiMode;
import com.saiton.ccs.util.SerialTest;
import com.saiton.ccs.validations.CustomTableViewValidationImpl;
import com.saiton.ccs.validations.CustomTextAreaValidationImpl;
import com.saiton.ccs.validations.CustomTextFieldValidationImpl;
import com.saiton.ccs.validations.ErrorMessages;
import com.saiton.ccs.validations.FormatAndValidate;
import com.saiton.ccs.validations.Validatable;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;

public class ScaleController implements Initializable, Validatable,
        StagePassable {

    //<editor-fold defaultstate="collapsed" desc="Initcomponent">
    @FXML
    private TextField txtWeightScaleId;

    @FXML
    private DatePicker dtpDate;

    @FXML
    private TextField txtReelNo;

    @FXML
    private TextField txtGrossWeight;

    @FXML
    private TextField txtNetWeight1;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField txtGauge;

    @FXML
    private TextField txtNetWeight;

    @FXML
    private TextField txtWidth;

    @FXML
    private TextField txtBatchNo;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnRefreshNetWeight;

    @FXML
    private TextField txtJobNo;

    @FXML
    private TextField txtLength;

    @FXML
    private ComboBox<?> cmbScale;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQty;

    @FXML
    private Button btnPrint;

    @FXML
    private Button btnRefreshCoreWeight;

    @FXML
    private Label lblItemId;

    @FXML
    private TextField txtEPFNo;

    @FXML
    private Button btnRefreshGrossWeight;
//</editor-fold>

    private Stage stage;
    @FXML
    private TextField txtCustomer;
    @FXML
    private Button btnSearchCustomer;
    @FXML
    private Button btnRefreshCustomer;
    @FXML
    private Button btnCustomerAdd;
    @FXML
    private TextField txtSize;
    @FXML
    private Button btnSearchSize;
    @FXML
    private Button btnRefreshSize;
    @FXML
    private Button btnSizeAdd;
    @FXML
    private TextField txtMachine;
    @FXML
    private Button btnSearchMachine;
    @FXML
    private Button btnRefreshMachine;
    @FXML
    private Button btnMachineAdd;

    public static String grossWeight = " ";

    //<editor-fold defaultstate="collapsed" desc="Key Events">
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Action Events">
    void txtWeightScaleIdOnKeyReleased(ActionEvent event) {

    }

    @FXML
    void btnRefreshGrossWeightOnAction(ActionEvent event) {

        SerialTest main = new SerialTest();
        main.initialize();
        Thread t = new Thread() {
            public void run() {
            //the following line will keep this app alive for 1000    seconds,
                //waiting for events to occur and responding to them    (printing incoming messages to console).
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException ie) {
                }
            }
        };
        t.start();
        System.out.println("Started");
        txtGrossWeight.setText(grossWeight);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnPrintOnAction(ActionEvent event) {

        HashMap param = new HashMap();
        param.put("weight_scale_id","SCA0001");
        File fil
                = new File(
                        ReportPath.PATH_WEIGHT_ONE_REPORT.
                        toString());
        String img = fil.getAbsolutePath();
        ReportGenerator r = new ReportGenerator(img, param);
        r.setVisible(true);
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshNetWeightOnAction(ActionEvent event) {

    }

    @FXML
    void dtpDateOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshCoreWeightOnAction(ActionEvent event) {

    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Click Events">
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        tcServiceId.setCellValueFactory(new PropertyValueFactory<Item, String>(
//                "colServiceId"));
//        tcServiceName.setCellValueFactory(
//                new PropertyValueFactory<Item, String>(
//                        "colServiceName"));
//        tcServicePrice.setCellValueFactory(
//                new PropertyValueFactory<Item, String>(
//                        "colServicePrice"));
//        tcServiceDescription.setCellValueFactory(
//                new PropertyValueFactory<Item, String>(
//                        "colServiceDescription"));
//
//        tblItemList.setItems(TableItemData);
//
//        mb = SimpleMessageBoxFactory.createMessageBox();
//        txtServiceId.setText(serviceDAO.generateID());
        btnDelete.setVisible(false);

    }

    @Override
    public boolean isValid() {

        return true;

    }

    @Override
    public void clearInput() {
//            txtDescription.clear();
//            txtPrice.clear();
//            txtService.clear();
//            txtServiceId.clear();
//            TableItemData.clear();
//            txtServiceId.setText(serviceDAO.generateID());
//            no = 1;
//             isupdate = false;

    }

    private void clearComponentsForNewEntry() {

//        txtDescription.clear();
//        txtPrice.clear();
//        txtService.clear();
    }

    @Override
    public void clearValidations() {

//        no = 1;
//        
////        txtPrice.clear();
//        isupdate = true;
//
//        int count = TableItemData.size();
//        if (count == 0) {
//
//            btnDelete.setVisible(true);
//
//        }
    }

    private void setUserAccessLevel() {

//        userId = UserSession.getInstance().getUserInfo().getEid();
//
//        userName = UserSession.getInstance().getUserInfo().getName();
//        userCategory = UserSession.getInstance().getUserInfo().getCategory();
//        txtUserName.setText(userName);
//
//        String title = stage.getTitle();
//
//        if (!title.isEmpty()) {
//
//            UserPermission userPermission = UserSession.getInstance().
//                    findPermission(title);
//
//            if (userPermission.canInsert() == true) {
//                insert = true;
//            }
//
//            if (userPermission.canDelete() == true) {
//                delete = true;
//            }
//
//            if (userPermission.canUpdate() == true) {
//                update = true;
//            }
//
//            if (userPermission.canView() == true) {
//                view = true;
//            }
//
//            if (insert == true && delete == true && update == true && view
//                    == true) {
//                setUiMode(UiMode.FULL_ACCESS);
//
//            } else if (insert == false && delete == true && update == true
//                    && view
//                    == true) {
//                setUiMode(UiMode.FULL_ACCESS);
//
//            } else if (insert == true && delete == false && update == true
//                    && view
//                    == true) {
//                setUiMode(UiMode.ALL_BUT_DELETE);
//
//            } else if (insert == true && delete == true && update == false
//                    && view
//                    == true) {
//
//                setUiMode(UiMode.FULL_ACCESS);
//
//            } else if (insert == true && delete == true && update == true
//                    && view
//                    == false) {
//                setUiMode(UiMode.SAVE);
//
//            } else if (insert == false && delete == false && update == true
//                    && view
//                    == true) {
//
//                setUiMode(UiMode.FULL_ACCESS);
//
//            } else if (insert == false && delete == true && update == false
//                    && view
//                    == true) {
//                setUiMode(UiMode.DELETE);
//
//            } else if (insert == false && delete == true && update == true
//                    && view
//                    == false) {
//                setUiMode(UiMode.NO_ACCESS);
//
//            } else if (insert == true && delete == false && update == false
//                    && view
//                    == true) {
//
//                setUiMode(UiMode.ALL_BUT_DELETE);
//
//            } else if (insert == true && delete == false && update == true
//                    && view
//                    == false) {
//                setUiMode(UiMode.SAVE);
//
//            } else if (insert == true && delete == true && update == false
//                    && view
//                    == false) {
//                setUiMode(UiMode.SAVE);
//
//            } else if (insert == false && delete == false && update == false
//                    && view
//                    == true) {
//
//                setUiMode(UiMode.READ_ONLY);
//
//            } else if (insert == false && delete == false && update == true
//                    && view
//                    == false) {
//                setUiMode(UiMode.NO_ACCESS);
//
//            } else if (insert == false && delete == true && update == false
//                    && view
//                    == false) {
//                setUiMode(UiMode.NO_ACCESS);
//
//            } else if (insert == true && delete == false && update == false
//                    && view
//                    == false) {
//                setUiMode(UiMode.SAVE);
//
//            } else if (insert == false && delete == false && update == false
//                    && view
//                    == false) {
//                setUiMode(UiMode.NO_ACCESS);
//
//            }
//        } else {
//
//            setUiMode(UiMode.NO_ACCESS);
//
//        }
    }

    private void setUiMode(UiMode uiMode) {

        switch (uiMode) {

            case SAVE:
                disableUi(false);
                deactivateSearch();

                btnDelete.setVisible(false);

                break;

            case DELETE:
                disableUi(false);

                btnPrint.setDisable(true);
                btnPrint.setVisible(false);

                deactivateCombo();

                break;

            case READ_ONLY:
                disableUi(false);
                deactivateCombo();
                btnDelete.setVisible(false);

                btnPrint.setDisable(true);
                btnPrint.setVisible(false);

                break;

            case ALL_BUT_DELETE:
                disableUi(false);

                btnDelete.setVisible(false);

                break;

            case FULL_ACCESS:
                disableUi(false);
                btnDelete.setVisible(false);
                break;

            case NO_ACCESS:
                disableUi(true);

                break;

        }

    }

    private void disableUi(boolean state) {

        btnDelete.setDisable(state);
        btnDelete.setVisible(!state);

        btnPrint.setDisable(state);
        btnPrint.setVisible(!state);

        btnClose.setDisable(state);
        btnClose.setVisible(!state);
    }

    private void deactivateSearch() {

//        componentControl.deactivateSearch(lblItemName, txtItemName,
//                btnItemNameSearch,
//                220.00, 0.00);
    }

    private void deactivateCombo() {
//        componentControl.controlCComboBox(lblItemId1, cmbBatchNo, btnBatchNo,
//                220.00, 0.0, true);
    }

    private void itemTableDataLoader(String keyword) {

//        itemData.clear();
//        ArrayList<ArrayList<String>> itemInfo
//                = new ArrayList<ArrayList<String>>();
//        ArrayList<ArrayList<String>> list = serviceDAO.searchItemDetails(keyword);
//
//        if (list != null) {
//
//            for (int i = 0; i < list.size(); i++) {
//
//                itemInfo.add(list.get(i));
//            }
//
//            if (itemInfo != null && itemInfo.size() > 0) {
//                for (int i = 0; i < itemInfo.size(); i++) {
//
//                    itemPopup = new ServiceInfoPopup();
//                    itemPopup.colItemID.setValue(itemInfo.get(i).get(0));
//                    itemPopup.colItemName.setValue(itemInfo.get(i).get(1));
//                    itemPopup.colItemDesc.setValue(itemInfo.get(i).get(2));
//                    itemPopup.colItemPrice.setValue(itemInfo.get(i).get(3));
//                    
//                    
//                    itemData.add(itemPopup);
//                }
//            }
//
//        }
    }

    @Override
    public void setStage(Stage stage, Object[] obj) {

        this.stage = stage;
//        setUserAccessLevel();
//        
//        //item popup------------------------
//        itemTable = itemPopup.tableViewLoader(itemData);
//
//        itemTable.setOnMouseClicked(e -> {
//            if (e.getClickCount() == 2) {
//                try {
//                    btnDelete.setVisible(true);
//                    ServiceInfoPopup p = null;
//                    p = (ServiceInfoPopup) itemTable.getSelectionModel().
//                            getSelectedItem();
//                    if (p.getColItemID() != null) {
//                        clearValidations();
//
//                        txtServiceId.setText(p.getColItemID());
//                        txtService.setText(p.getColItemName());
//                        txtDescription.setText(p.getColItemDesc());
//                        txtPrice.setText(p.getColItemPrice());
//                        txtUserName.setText(serviceDAO.getUserName(
//                                txtServiceId.getText()));
//                        
//                        
//                    }
//
//                } catch (NullPointerException n) {
//
//                }
//
//                itemPop.hide();
//                validatorInitialization();
//
//            }
//
//        });
//
//        itemTable.setOnMousePressed(e -> {
//
//            if (e.getButton() == MouseButton.SECONDARY) {
//
//                itemPop.hide();
//                validatorInitialization();
//
//            }
//
//        });
//
//        itemPop = new PopOver(itemTable);
//
//        stage.setOnCloseRequest(e -> {
//
//            if (itemPop.isShowing()) {
//                e.consume();
//                itemPop.hide();
//
//            }
//        });
//
//        
//        
//        
//        validatorInitialization();
    }

    private void validatorInitialization() {

//        validationSupportTableData.registerValidator(txtService,
//                new CustomTextAreaValidationImpl(txtService,
//                        !fav.validName(txtService.getText()),
//                        ErrorMessages.Error));
//
//        validationSupportTableData.registerValidator(txtDescription,
//                new CustomTextAreaValidationImpl(txtDescription,
//                        !fav.validName(txtDescription.getText()),
//                        ErrorMessages.Error));
//
//        validationSupportTableData.registerValidator(txtDescription,
//                new CustomTextAreaValidationImpl(txtDescription,
//                        !fav.validName(txtDescription.getText()),
//                        ErrorMessages.Error));
//
//        validationSupportTableData.registerValidator(txtPrice,
//                new CustomTextFieldValidationImpl(txtPrice,
//                        !fav.chkPrice(txtPrice.getText()),
//                        ErrorMessages.InvalidPrice));
//        
//        validationSupportTable.registerValidator(tblItemList,
//                new CustomTableViewValidationImpl(tblItemList,
//                        !fav.validTableView(tblItemList),
//                        ErrorMessages.EmptyListView));
    }

    @FXML
    private void txtWeightScaleIdOnKeyReleased(KeyEvent event) {
    }

    @FXML
    private void btnSearchCustomerOnAction(ActionEvent event) {
    }

    @FXML
    private void btnRefreshCustomerOnAction(ActionEvent event) {
    }

    @FXML
    private void btnCustomerAddOnAction(ActionEvent event) {
    }

    @FXML
    private void btnSearchSizeOnAction(ActionEvent event) {
    }

    @FXML
    private void btnRefreshSizeOnAction(ActionEvent event) {
    }

    @FXML
    private void btnSizeAddOnAction(ActionEvent event) {
    }

    @FXML
    private void btnSearchMachineOnAction(ActionEvent event) {
    }

    @FXML
    private void btnRefreshMachineOnAction(ActionEvent event) {
    }

    @FXML
    private void btnMachineAddOnAction(ActionEvent event) {
    }

    public class Item {

        public SimpleStringProperty colServiceId = new SimpleStringProperty(
                "tcServiceId");
        public SimpleStringProperty colServiceName = new SimpleStringProperty(
                "tcServiceName");
        public SimpleStringProperty colServicePrice
                = new SimpleStringProperty(
                        "tcServicePrice");
        public SimpleStringProperty colServiceDescription
                = new SimpleStringProperty(
                        "tcServiceDescription");

        public String getColServiceId() {
            return colServiceId.get();
        }

        public String getColServiceName() {
            return colServiceName.get();
        }

        public String getColServicePrice() {
            return colServicePrice.get();
        }

        public String getColServiceDescription() {
            return colServiceDescription.get();
        }

        public void setColServiceName(String serviceName) {
            colServiceName.setValue(serviceName);
        }

    }

//</editor-fold>
}
