package sample;

import com.sun.jdi.connect.spi.Connection;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.management.Query;
import javax.swing.plaf.synth.SynthSpinnerUI;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.spi.ResourceBundleProvider;

public class Controller implements Initializable{
    private Label label;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tftitle;
    @FXML
    private TextField tfauthor;
    @FXML
    private TextField tfyear;
    @FXML
    private TextField tfpages;
    @FXML
    private TableView<Books, Integer> colid;
    @FXML
    private TableView<Books, String> coltitle;
    @FXML
    private TableView<Books, String> colauthor;
    @FXML
    private TableView<Books, Integer> colyear;
    @FXML
    private TableView<Books, Integer> colpages;
    @FXML
    private Button btninsert;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;

    @FXML
    private void handleButtonAction(ActionEvent event){
        System.out.println("You clicked me!");
        label.setText(("Hello World"));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //TODO
    }
    public Connection getConnection(){
        Connection conn;
        try{
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
        }catch(Exception ex){
            System.out.println("Error: "+ ex.getMessage());
            return null;
        }
    }
    public ObservableList<Books>getBooksList(){
        ObservableList<Books>bookList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT*FROM books";
        Statement st;
        ResultSet rs;

        try{
           st=conn.createStatement();
           rs=st.executeQuery(Query);
           Books books;
           while(rs.next()){
               books = new Books(rs.getInt("id"), rs.getString("title"),rs.getString("author"),rs.getInt("year"),rs.getInt("pages"));
               bookList.add(books);
           }
        }catch (Exception ex){
            ex.printStackTrace();

        }
        return bookList;
    }
    public  void showBooks(){
        ObservableList<Books> list = getBooksList();

        colid.setCellValueFactory(new PropertyValueFactory<Books, Integer>("id"));
        colid.setCellValueFactory(new PropertyValueFactory<Books, Integer>("id"));
    }   colid.setCellValueFactory(new PropertyValueFactory<Books, Integer>("id"));
}
