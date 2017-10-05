package com.saiton.ccs.scale;

import com.saiton.ccs.popup.CustomerIdPopup;
import com.saiton.ccs.printerservice.ReportPath;
import com.saiton.ccs.scaledao.ScaleDAO;
import com.saiton.ccs.uihandle.ReportGenerator;
import com.saiton.ccs.uihandle.StagePassable;
import com.saiton.ccs.uihandle.UiMode;
import com.saiton.ccs.validations.CustomTableViewValidationImpl;
import com.saiton.ccs.validations.CustomTextAreaValidationImpl;
import com.saiton.ccs.validations.CustomTextFieldValidationImpl;
import com.saiton.ccs.validations.ErrorMessages;
import com.saiton.ccs.validations.FormatAndValidate;
import com.saiton.ccs.validations.Validatable;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.util.StringConverter;
import org.controlsfx.control.PopOver;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;

public class SearchWeightScaleController implements Initializable, Validatable,
        StagePassable {

    @FXML
    private Button btnClose;

    @FXML
    private TableColumn<Item, String> tcDate;
    @FXML
    private TableColumn<Item, String> tcWeightScaleID;

    @FXML
    private TableColumn<Item, String> tcScale;

    @FXML
    private TableColumn<Item, String> tcCustomer;

    @FXML
    private TableColumn<Item, String> tcNetWeight;

    @FXML
    private TableColumn<Item, String> tcNoOfBags;
    @FXML
    private TableColumn<Item, String> tcGrossWeight;
    @FXML
    private TableColumn<Item, String> tcTare;
    @FXML
    private TableColumn<Item, String> tcCoarseLeaf;
    @FXML
    private TableColumn<Item, String> tcWater;
    @FXML
    private TableColumn<Item, String> tcBoilLeaf;
    @FXML
    private TableColumn<Item, String> tcCoreWeight;

    @FXML
    private ComboBox<String> cmbScale;

    @FXML
    private DatePicker dtpFromDate;

    @FXML
    private DatePicker dtpToDate;

    @FXML
    private TableView<Item> tblItemList;

    private ObservableList tableScaleData = FXCollections.
            observableArrayList();

//</editor-fold>
    private Stage stage;

    ScaleDAO scaleDAO = new ScaleDAO();
    Item scaleItem = new Item();

    @FXML
    private Label lblSearch;
    @FXML
    private TextField txtSCustomer;
    @FXML
    private Button btnSearchCustomer;

    String customerCode = "";
    //Customer Popup
    private TableView customerIdTable = new TableView();
    private CustomerIdPopup customerIdPopup = new CustomerIdPopup();
    private ObservableList<CustomerIdPopup> customerData = FXCollections.
            observableArrayList();
    private PopOver customerIdPop;

    //<editor-fold defaultstate="collapsed" desc="Key Events">
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Action Events">
    @FXML
    void btnCloseOnAction(ActionEvent event) {

        HashMap param = new HashMap();
        param.put("from_date", dtpFromDate.
                getValue().toString());
        param.put("to_date", dtpToDate.
                getValue().toString());
                        param.put("cus_code", customerCode);

        File fileOne
                = new File(
                        ReportPath.PATH_WEIGHT_INFO_A4.
                        toString());
        String img = fileOne.getAbsolutePath();
        ReportGenerator r = new ReportGenerator(img, param);

        r.setVisible(true);

    }

    @FXML
    void dtpFromDateOnAction(ActionEvent event) {
        loadTableData();
    }

    @FXML
    void dtpToDateOnAction(ActionEvent event) {
        loadTableData();
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Click Events">
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tcGrossWeight.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "colGrossWeight"));
        tcTare.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "colTare"));

        tcCoarseLeaf.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "colCoarseLeaf"));
        tcWater.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "colWater"));
        tcBoilLeaf.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "colBoilLeaf"));

        tcCoreWeight.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "colCoreWeight"));

        tcNoOfBags.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "colNoOfBags"));

        tcDate.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "colDate"));
        tcWeightScaleID.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "colWeightScaleID"));
        tcScale.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "colScale"));

        tcCustomer.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "colCustomer"));
//
        tcNetWeight.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "colNetWeight"));
//
        tblItemList.setItems(tableScaleData);

        loadScaleNames();

        dateFormatter("yyyy-MM-dd");
        dtpFromDate.setValue(LocalDate.now());
        dtpToDate.setValue(LocalDate.now());

        loadTableData();
//        mb = SimpleMessageBoxFactory.createMessageBox();
//        txtServiceId.setText(serviceDAO.generateID());
//        btnDelete.setVisible(false);
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

    private void disableUi(boolean state) {

        btnClose.setDisable(state);
        btnClose.setVisible(!state);
    }

    @Override
    public void setStage(Stage stage, Object[] obj) {

        this.stage = stage;
        
         

        //CustomerId popup------------------------
        customerIdTable = customerIdPopup.tableViewLoader(customerData);

        customerIdTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                try {
                    CustomerIdPopup p = null;
                    p = (CustomerIdPopup) customerIdTable.getSelectionModel().
                            getSelectedItem();
                    //clearInput();

                    if (p.getColCustomerName() != null) {
                        txtSCustomer.setText(p.getColCustomerName());
                        customerCode = p.getColCustomerCode();
//                        btnDelete.setVisible(true);
                        loadTableData();

                    }

                } catch (NullPointerException n) {

                }

                customerIdPop.hide();
                validatorInitialization();

            }

        });

        customerIdTable.setOnMousePressed(e -> {

            if (e.getButton() == MouseButton.SECONDARY) {

                customerIdPop.hide();
                validatorInitialization();

            }

        });
        
          customerIdPop = new PopOver(customerIdTable);

        

        stage.setOnCloseRequest(e -> {

            if (customerIdPop.isShowing()
                    ) {
                e.consume();
                customerIdPop.hide();
               

            }
        });
    }

    private void loadScaleNames() {

        cmbScale.getItems().clear();
        ArrayList<String> list = null;
        list = scaleDAO.loadScaleItem();
        if (list != null) {
            try {
                ObservableList<String> List = FXCollections.observableArrayList(
                        list);
                cmbScale.setItems(List);
                cmbScale.setValue(List.get(0));
            } catch (Exception e) {

            }

        }
    }

    private void dateFormatter(String pattern) {

        dtpFromDate.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(
                    pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        dtpToDate.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(
                    pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

    }

    private void loadTableData() {

        tableScaleData.clear();

        ArrayList<ArrayList<String>> scaleInfo
                = new ArrayList<>();
        ArrayList<ArrayList<String>> list = scaleDAO.loadScaleInfo(dtpFromDate.
                getValue().toString(), dtpToDate.getValue().toString(),customerCode);

        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                scaleInfo.add(list.get(i));
            }

            if (scaleInfo != null && scaleInfo.size() > 0) {
                for (int i = 0; i < scaleInfo.size(); i++) {

                    scaleItem = new Item();
                    scaleItem.colDate.setValue(scaleInfo.get(i).get(0));
                    scaleItem.colWeightScaleID.setValue(scaleInfo.get(i).get(1));
                    scaleItem.colScale.setValue(scaleInfo.get(i).get(2));
                    scaleItem.colCustomer.setValue(scaleInfo.get(i).get(3));
                    scaleItem.colNetWeight.setValue(scaleInfo.get(i).get(4));
                    scaleItem.colNoOfBags.setValue(scaleInfo.get(i).get(5));
                    scaleItem.colGrossWeight.setValue(scaleInfo.get(i).get(6));
                    scaleItem.colTare.setValue(scaleInfo.get(i).get(7));
                    scaleItem.colCoarseLeaf.setValue(scaleInfo.get(i).get(8));
                    scaleItem.colWater.setValue(scaleInfo.get(i).get(9));
                    scaleItem.colBoilLeaf.setValue(scaleInfo.get(i).get(10));
                    scaleItem.colCoreWeight.setValue(scaleInfo.get(i).get(11));

                    tableScaleData.add(scaleItem);

                }
            }

        }

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
    
    
      private void customerTableDataLoader(String keyword) {

        customerData.clear();
        ArrayList<ArrayList<String>> itemInfo
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> list = scaleDAO.
                searchCustomerDetailsDetails(keyword);

        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                itemInfo.add(list.get(i));
            }

            if (itemInfo != null && itemInfo.size() > 0) {
                for (int i = 0; i < itemInfo.size(); i++) {

                    customerIdPopup = new CustomerIdPopup();
                    customerIdPopup.colCustomerCode.setValue(itemInfo.get(i).
                            get(0));
                    customerIdPopup.colCustomerName.setValue(itemInfo.get(i).
                            get(1));

                    customerData.add(customerIdPopup);
                }
            }

        }

    }

    @FXML
    private void txtSCustomerKeyReleased(KeyEvent event) {
    }

    @FXML
    private void btnSearchCustomerOnAction(ActionEvent event) {
        
        customerTableDataLoader(txtSCustomer.getText());
        customerIdTable.setItems(customerData);
        if (!customerData.isEmpty()) {
            customerIdPop.show(btnSearchCustomer);
        }
        validatorInitialization();
        
    }

    public class Item {

        public SimpleStringProperty colGrossWeight
                = new SimpleStringProperty(
                        "tcGrossWeight");
           public String getColGrossWeight() {
            return colGrossWeight.get();
        }

        public SimpleStringProperty colTare
                = new SimpleStringProperty(
                        "tcTare");
             public String getColTare() {
            return colTare.get();
        }

        public SimpleStringProperty colCoarseLeaf
                = new SimpleStringProperty(
                        "tcCoarseLeaf");
              public String getColCoarseLeaf() {
            return colCoarseLeaf.get();
        }

        public SimpleStringProperty colWater
                = new SimpleStringProperty(
                        "tcWater");
            public String getColWater() {
            return colWater.get();
        }

        public SimpleStringProperty colBoilLeaf
                = new SimpleStringProperty(
                        "tcBoilLeaf");
          public String getColBoilLeaf() {
            return colBoilLeaf.get();
        }

        public SimpleStringProperty colCoreWeight
                = new SimpleStringProperty(
                        "tcCoreWeight");
        public String getColCoreWeight() {
            return colCoreWeight.get();
        }

        public SimpleStringProperty colNoOfBags
                = new SimpleStringProperty(
                        "tcNoOfBags");
        public String getColNoOfBags() {
            return colNoOfBags.get();
        }

        public SimpleStringProperty colDate = new SimpleStringProperty(
                "tcDate");
        public SimpleStringProperty colWeightScaleID
                = new SimpleStringProperty(
                        "tcWeightScaleID");

        public SimpleStringProperty colScale = new SimpleStringProperty(
                "tcScale");

        public SimpleStringProperty colCustomer = new SimpleStringProperty(
                "tcCustomer");

        public SimpleStringProperty colNetWeight
                = new SimpleStringProperty(
                        "tcNetWeight");

        public String getColDate() {
            return colDate.get();
        }

        public String getColWeightScaleID() {
            return colWeightScaleID.get();
        }

        public String getColScale() {
            return colScale.get();
        }

        public String getColCustomer() {
            return colCustomer.get();
        }

        public String getColNetWeight() {
            return colNetWeight.get();
        }

        public void setColNetWeight(String serviceName) {
            colNetWeight.setValue(serviceName);
        }

    }

//</editor-fold>
}
