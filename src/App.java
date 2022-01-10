import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import static javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 

public class App extends Application {
    TableView<modelDatabase> tableView = new TableView<modelDatabase>();
    // ObservableList<modelDatabase> observableList = new ObservableList<modelDatabase>();

    public TableView tblView;
    private Text txtInfo;
    private Label lblTitle,lblData,lblNK,lblNAMA,lblTELPON,lblSTATUS,lblCari;
    public TextField txtNK,txtNAMA,txtTELPON,txtSTATUS,txtCARI;
    public TableColumn tblColumn1,tblColumn2,tblColumn3,tblColumn4;
    private SplitPane splitPaneH;
    private VBox panevbox,panevbox2;
    private AnchorPane pane;
    private GridPane grid;
    private HBox panehbox,searchbox;
    private Button btnAdd,btnUpdate,btnDelete,btnClear,btnClose,btnRefresh;
    modelDatabase modelDb;
    ObservableList data = FXCollections.observableArrayList();
   
    public void initComponent(){
      //========================================================================
        lblData    = new Label("FORM DATA");
        lblTitle   = new Label();
        lblNK     = new Label("NK");
        lblNAMA    = new Label("NAMA");
        lblTELPON = new Label("TELPON");
        lblSTATUS  = new Label("STATUS");
        lblCari    = new Label("CARI DATA :");
        txtInfo    = new Text("No data");
        tblColumn1 = new TableColumn("NK");
        tblColumn2 = new TableColumn("NAMA");
        tblColumn3 = new TableColumn("TELPON");
        tblColumn4 = new TableColumn("STATUS");
        txtNK     = new TextField();
        txtNAMA    = new TextField();
        txtTELPON = new TextField();
        txtSTATUS  = new TextField();
        txtCARI    = new TextField();
        splitPaneH = new SplitPane();
        pane       = new AnchorPane();
        panevbox   = new VBox();
        panevbox2  = new VBox();
        grid       = new GridPane();
        panehbox   = new HBox(5);
        searchbox  = new HBox(5);
        tblView    = new TableView();
        btnAdd     = new Button("ADD");
        btnUpdate  = new Button("UPDATE");
        btnDelete  = new Button("DELETE");
        btnClear   = new Button("CLEAR");
        btnClose   = new Button("CLOSE");
        btnRefresh = new Button("REFRESH");
      //========================================================================
        tblColumn1.setCellValueFactory(new PropertyValueFactory("nk"));
        tblColumn2.setCellValueFactory(new PropertyValueFactory("nama"));
        tblColumn3.setCellValueFactory(new PropertyValueFactory("telpon"));
        tblColumn4.setCellValueFactory(new PropertyValueFactory("status"));
       
        txtNK.setPromptText("Masukkan NK Anda");
        txtNAMA.setPromptText("Masukkan Nama Anda");
        txtTELPON.setPromptText("Masukkan Telpon Anda");
        txtSTATUS.setPromptText("Masukkan Status Anda");
        txtCARI.setPromptText("Masukkan data yang ingin dicari");
       
        lblCari.setPadding(new Insets(10));
        lblCari.setFont(Font.font("Arial Black", FontWeight.BOLD, 12));
        lblCari.setAlignment(Pos.CENTER);
        lblCari.setUnderline(true);
       
        lblData.setPadding(new Insets(10));
        lblData.setFont(Font.font("Arial Black", FontWeight.BOLD, 22));
        // lblData.setUnderline(true);
        lblData.setAlignment(Pos.CENTER);
       
        lblTitle.setText("DATABASE KOSSAN");
        // lblTitle.setUnderline(true);
        lblTitle.setPadding(new Insets(10));
        lblTitle.setFont(Font.font("Arial Black", FontWeight.MEDIUM, 22));
        lblTitle.setAlignment(Pos.CENTER);
       
        lblNK.setPrefSize(100, 30);
        lblNAMA.setPrefSize(100, 30);
        lblTELPON.setPrefSize(100, 30);
        lblSTATUS.setPrefSize(100, 30);
       
        txtNK.setPrefSize(250, 30);
        txtNAMA.setPrefSize(250, 30);
        txtTELPON.setPrefSize(250, 30);
        txtSTATUS.setPrefSize(250, 30);
        txtCARI.setPrefSize(250, 30);
       
        tblView.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
        tblView.setPlaceholder(txtInfo);
        tblView.setPadding(new Insets(10));
        tblView.getColumns().addAll(tblColumn1,tblColumn2,tblColumn3,tblColumn4);
        tblView.setPrefHeight(250);
        tblView.setBackground(new Background(
                new BackgroundFill(Color.DARKGRAY,new CornerRadii(10),Insets.EMPTY)));
       
        panehbox.setAlignment(Pos.CENTER);
        panehbox.setPadding(new Insets(10));
        panehbox.setLayoutX(23);
        panehbox.setLayoutY(194);
        panehbox.getChildren().addAll(btnAdd,btnUpdate,btnDelete,btnClear,btnClose);
        panehbox.setBackground(new Background(new BackgroundFill(
                Color.DARKGRAY, new CornerRadii(10), Insets.EMPTY)));
       
        searchbox.setAlignment(Pos.CENTER_LEFT);
        searchbox.setPadding(new Insets(5));
        searchbox.getChildren().addAll(lblCari,txtCARI,btnRefresh);
        searchbox.setBackground(new Background(new BackgroundFill(
                Color.DARKGRAY, new CornerRadii(10), Insets.EMPTY)));
       
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setLayoutX(5);
        grid.setLayoutY(5);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.addRow(1, lblNK,txtNK);
        grid.addRow(2, lblNAMA,txtNAMA);
        grid.addRow(3, lblTELPON,txtTELPON);
        grid.addRow(4, lblSTATUS,txtSTATUS);
        grid.setGridLinesVisible(false);
       
        pane.setBorder(new Border(new BorderStroke(
                Color.WHITESMOKE,BorderStrokeStyle.DASHED,
                new CornerRadii(15),new BorderWidths(5),Insets.EMPTY)));
        pane.setBackground(new Background(new BackgroundFill(
                Color.LIGHTGREY, new CornerRadii(15),Insets.EMPTY)));
        pane.getChildren().addAll(grid,panehbox);
       
        panevbox.getChildren().addAll(lblTitle,tblView,searchbox);
        panevbox.setPadding(new  Insets(5));
        panevbox.setSpacing(5);
        panevbox.minWidthProperty().bind(splitPaneH.widthProperty().multiply(0.65));
        panevbox.maxWidthProperty().bind(splitPaneH.widthProperty().multiply(0.65));
       
        panevbox2.getChildren().addAll(lblData,pane);
        panevbox2.setPadding(new Insets(5));
        panevbox2.setSpacing(5);
       
        splitPaneH.setOrientation(Orientation.HORIZONTAL);
        splitPaneH.getItems().addAll(panevbox,panevbox2);
        splitPaneH.setPadding(new Insets(2));
        splitPaneH.setBackground(new Background(
                   new BackgroundFill(Color.DARKSLATEGRAY,CornerRadii.EMPTY,Insets.EMPTY)));
        splitPaneH.setDividerPositions(0.5);
    }
    /**=======================================================================================
     *                       UNTUK MENAMPUNG DATA DARI DATABASE
     * =======================================================================================
     **/

    private ObservableList loadData(){
            ObservableList listData = FXCollections.observableArrayList();
            try {
            Connection c    = Database.tryConnect();
            String sql1     = "select * from kost;";
            ResultSet rs1   = c.createStatement().executeQuery(sql1);
            while(rs1.next()){
                modelDb         = new modelDatabase(rs1.getString(1),rs1.getString(2),
                                                    rs1.getString(3),rs1.getString(4));
                listData.add(modelDb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
            return listData;
           
    }
   
    private ObservableList searchByNK(String n){
        ObservableList listData = FXCollections.observableArrayList();
        try {
            Connection c = Database.tryConnect();
            String sql2 = " select distinct * from kost where nk like '%"+n+"%';";
            ResultSet rs2 = c.createStatement().executeQuery(sql2);
            while(rs2.next()){
                modelDb         = new modelDatabase(rs2.getString(1),rs2.getString(2),
                                                    rs2.getString(3),rs2.getString(4));
                listData.add(modelDb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listData;
    }
    //=======================================================================================
   
    /**======================================================================================
     *                      UNTUK MELAKUKAN INSERT, DELETE DAN UPDATE
     *              DIMANA DATA DIAMBIL DARI FORM KEMUDIAN DIKUMPULKAN DI MODEL
     * ======================================================================================
     **/
    private void insert(modelDatabase m){
        Connection c = Database.tryConnect();
        PreparedStatement ps;
        try {
            String sql = "insert into kost values (?,?,?,?);";
            ps  = c.prepareStatement(sql);
            ps.setString(1,m.getNk());
            ps.setString(2,m.getNama());
            ps.setString(3,m.getTelpon());
            ps.setString(4,m.getStatus());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
        }
    }
   
    private void delete(modelDatabase m){
        try {
            Connection c = Database.tryConnect();
            PreparedStatement ps;
            String sql = "delete from kost where nk = ?;";
            ps = c.prepareStatement(sql);
            ps.setString(1, m.getNk());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
   
    private void update(modelDatabase m){
        try {
            Connection c = Database.tryConnect();
            PreparedStatement ps;
            String sql = "update kost set nama = ? ,telpon = ? , status = ? where nk = ? ;";
            ps = c.prepareStatement(sql);
            ps.setString(1, m.getNama());
            ps.setString(2, m.getTelpon());
            ps.setString(3, m.getStatus());
            ps.setString(4, m.getNk());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //=======================================================================================
   
    /**======================================================================================
     *                                   ACTIONEVENT  
     * ======================================================================================
     **/
    private void selectData(){
        modelDb = (modelDatabase) tblView.getSelectionModel().getSelectedItems().get(0);
        txtNK.setText(modelDb.getNk());
        txtNAMA.setText(modelDb.getNama());
        txtTELPON.setText(modelDb.getTelpon());
        txtSTATUS.setText(modelDb.getStatus());
        txtNK.setDisable(true);
    }
   
    private void deleteData(){
        modelDb = new modelDatabase(txtNK.getText(), "", "", "");
        delete(modelDb);
        clearData();
        showData();
    }
   
    private void updateData(){
        modelDb = new modelDatabase(txtNK.getText(),txtNAMA.getText(),
                                    txtTELPON.getText(),txtSTATUS.getText());
        update(modelDb);
        clearData();
        showData();
    }
   
    private void searchbyNK(){
        data.clear(); // <- menghapus data pada penampung data
        data = searchByNK(txtCARI.getText().trim());
        tblView.setItems(data); // <- menaruh data pada tabel agar bisa tampil
        tblView.getSelectionModel().clearSelection(); // <- menghapus seleksi baris pada tabel
    }
   
    private void refresh(){
        showData();
        clearData();
        txtCARI.clear();
    }
   
    private void showData(){
        data.clear();
        data = loadData();
        tblView.setItems(data);
        tblView.getSelectionModel().clearSelection();
    }
   
    private void clearData(){
        txtNK.clear();
        txtNAMA.clear();
        txtTELPON.clear();
        txtSTATUS.clear();
        txtNK.setDisable(false);
        tblView.getSelectionModel().clearSelection();
    }
   
    private void addData(){
        // mengambil data dari form, kemudian disusun seperti array
        modelDb = new modelDatabase(txtNK.getText(),txtNAMA.getText(),
                                    txtTELPON.getText(),txtSTATUS.getText());
        insert(modelDb); //<- data dikirim ke SQL
        showData();
        clearData();
    }
  //====================================================================================    
    @Override
    public void start(Stage primaryStage) {
        initComponent(); // <- VIEW
        showData();     // <- MENAMPILKAN DATA
        tblView.setOnMousePressed((MouseEvent event) -> {
            selectData(); // <- EVENT BARIS KETIKA DIPILIH
        });
        btnAdd.setOnAction((ActionEvent e) -> {
            addData(); // <- INSERT DATA
        });
        btnClear.setOnAction((ActionEvent e) -> {
            clearData(); // <- CLEAR FIELD INPUT DATA
        });
        btnClose.setOnAction((ActionEvent e) -> {
            primaryStage.close(); // <- CLOSE SCENE WINDOW
        });
        btnUpdate.setOnAction((ActionEvent e) -> {
            updateData(); // <- UPDATE DATA
        });
        btnDelete.setOnAction((ActionEvent e) -> {
            deleteData(); // <- DELETE DATA
        });
        btnRefresh.setOnAction((ActionEvent e) -> {
            refresh(); // <- MENGEMBALIKAN TAMPILAN SEPERTI SEMULA
        });
        txtCARI.setOnKeyTyped((KeyEvent ke) -> {
            searchbyNK(); // <- SEARCH DATA BY NK
        });
        Scene scene = new Scene(splitPaneH, 1216, 618);
        scene.setFill(null);
        primaryStage.setScene(scene);

        primaryStage.setTitle("CRUD APPLICATION WITH JAVAFX");
        primaryStage.show();
        primaryStage.setFullScreen(true);
    }
 
    public static void main(String[] args) {
       
        launch(args);
       
    }
   
}