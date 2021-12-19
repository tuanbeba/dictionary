package dictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javax.speech.EngineException;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class Control {
    private DictionaryManagement Dicmana = new DictionaryManagement();
    private ArrayList<dictionary.Word> resultList = new ArrayList<Word>();
    private Word WordNow = new Word();
    private String choosingWord = new String();
    private String WNow = "";
    private String ProNow = "";
    private String TypeNow = "";
    private String ExpNow = "";
    private String UsgNow = "";
    @FXML
    private TextField SubmitS;
    @FXML
    private ListView<String> ListWord;
    @FXML
    private Text TextWord;
    @FXML
    private Text sound;
    @FXML
    private TextArea meaning;
    @FXML
    private TextArea usage;
    @FXML
    private ImageView ShowListen;
    @FXML
    private Group groupWord;
    @FXML
    private Group groupEdit;
    @FXML
    private Text WordEdit;
    @FXML
    private TextField ProEdit;
    @FXML
    private TextField TypeEdit;
    @FXML
    private TextArea ExplaneEdit;
    @FXML
    private TextArea UsageEdit;
    @FXML
    private Group groupAdd;
    @FXML
    private TextArea WordAdd;
    @FXML
    private TextArea ProAdd;
    @FXML
    private TextArea TypeAdd;
    @FXML
    private TextArea ExpAdd;
    @FXML
    private TextArea UsgAdd;
    public Control() throws EngineException {
    }

    public void initResultList() {
        groupWord.toBack();
        groupAdd.toBack();
        this.suggest("");
    }

    public void search(KeyEvent event) {
        this.suggest(this.SubmitS.getText());
    }

    public void suggest(String WordSearch) {
        this.resultList = this.Dicmana.dictionarySearcher(WordSearch);
        ArrayList<String> wordsFound = new ArrayList<String>();
        for (int i = 0; i < this.resultList.size(); i++) {
            wordsFound.add(this.resultList.get(i).getWord());
        }
        ObservableList<String> items = FXCollections.observableArrayList(wordsFound);
        this.ListWord.setItems(items);
    }

    public void ShowWord(Word WordS) {
        groupAdd.toBack();
        groupWord.toFront();
        this.WordNow = WordS;
        this.TextWord.setText(WordS.getWord());
        this.sound.setText(WordS.getPronunciation());
        ArrayList<String> tmp;
        String res = "";
        if (!WordS.getWord_type().equals("")) {
            res = " - " + WordS.getWord_type() + ":\n";
        }

        tmp = WordS.getExplanations();
        for (int j = 0; j < tmp.size(); j++) {
            if (tmp.get(j).equals("Trạng từ")) {
                res += " - Trạng từ:\n";
                continue;
            }
            res += tmp.get(j) + "\n";
        }
        String t = "";
        tmp = WordS.getUsages();
        for (int j = 0; j < tmp.size(); j++) {
            t += tmp.get(j) + "\n";
        }

        this.meaning.setText(res);
        this.usage.setText(t);
    }

    public void Submit(ActionEvent event) {
        String Word = SubmitS.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if (this.resultList.size() == 0) {
            alert.setContentText("Không tìm thấy từ cần tìm kiếm!");
            alert.show();
            return;
        } else {
            if (!this.resultList.get(0).getWord().equals(Word)) {
                alert.setContentText("Không tìm thấy từ cần tìm kiếm!");
                alert.show();
                return;
            }
            ShowWord(this.resultList.get(0));
        }

    }

    public void ListenWord(MouseEvent event) throws EngineException {
        Dicmana.textToSpeech(this.WordNow.getWord());
    }

    public void WordClicked(MouseEvent event) {
        if (resultList.size() == 0) {
            return;
        }
        this.choosingWord = this.ListWord.getSelectionModel().getSelectedItem();
        if (this.choosingWord == null) {
            return;
        }
        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.get(i).getWord().equals(this.choosingWord)) {
                this.WordNow = resultList.get(i);
            }
        }
        if (event.getButton() == MouseButton.SECONDARY) {
            this.choosingWord = this.ListWord.getSelectionModel().getSelectedItem();
        }
        else {
            this.ShowWord(WordNow);
        }
    }

    public void ButtonShow(ActionEvent event) {
        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.get(i).getWord().equals(this.choosingWord)) {
                this.WordNow = resultList.get(i);
                this.ShowWord(resultList.get(i));
                return;
            }
        }
    }

    public void Erase(ActionEvent event) {
        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.get(i).getWord().equals(this.choosingWord)) {
                this.Dicmana.removeWord(resultList.get(i));
                this.suggest(this.SubmitS.getText());
                return;
            }
        }
    }
    public void BackToSearch1(ActionEvent event) {
        this.SubmitS.setText("");
        suggest("");
        this.groupWord.toBack();
        this.groupAdd.toBack();
        this.groupEdit.toBack();
    }
    public void BackToSearch2(ActionEvent event) {
        this.SubmitS.setText("");
        suggest("");
        this.groupWord.toBack();
        this.groupAdd.toBack();
        this.groupEdit.toBack();
        this.WordAdd.setText("");
        this.ProAdd.setText("");
        this.TypeAdd.setText("");
        this.ExpAdd.setText("");
        this.UsgAdd.setText("");
    }
    public void BackToSearch3(ActionEvent event) {
        this.SubmitS.setText("");
        suggest("");
        this.groupWord.toBack();
        this.groupAdd.toBack();
        this.groupEdit.toBack();
        this.WordEdit.setText("");
        this.ProEdit.setText("");
        this.TypeEdit.setText("");
        this.ExplaneEdit.setText("");
        this.UsageEdit.setText("");
    }
    public void Edit(ActionEvent event) {
        this.groupWord.toBack();
        this.groupAdd.toBack();
        this.groupEdit.toFront();
        this.WordEdit.setText(WordNow.getWord());
        this.ProEdit.setText(WordNow.getPronunciation());
        this.TypeEdit.setText(WordNow.getWord_type());
        String s = "";
        ArrayList<String> List = WordNow.getExplanations();
        for (int i = 0; i < List.size(); i++) {
            s += List.get(i) + "\n";
        }
        this.ExplaneEdit.setText(s);
        s = "";
        List = WordNow.getUsages();
        for (int i = 0; i < List.size(); i++) {
            s += List.get(i) + "\n";
        }
        this.UsageEdit.setText(s);
    }
    public void TextProEdit(KeyEvent event) {
        this.ProNow = this.ProEdit.getText();
    }
    public void TextTypeEdit(KeyEvent event) {
        this.TypeNow = this.TypeEdit.getText();
    }
    public void TextExpEdit(KeyEvent event) {
        this.ExpNow = this.ExplaneEdit.getText();
    }
    public void TextUsgEdit(KeyEvent event) {
        this.UsgNow = this.UsageEdit.getText();
    }
    public void SaveEdit(ActionEvent event) {
        if (ProNow.equals("")) {
            ProNow = ProEdit.getText();
        }
        if (TypeNow.equals("")) {
            TypeNow = TypeEdit.getText();
        }
        if (ExpNow.equals("")) {
            ExpNow = ExplaneEdit.getText();
        }
        if (UsgNow.equals("")) {
            UsgNow = UsageEdit.getText();
        }
        WordNow.setPronunciation(this.ProNow);
        WordNow.setWord_type(this.TypeNow);
        ArrayList<String> List = new ArrayList<String>();
        String s = this.ExpNow;
        List.add(s);
        WordNow.setExplanations(List);
        ArrayList<String> List1 = new ArrayList<String>();
        s = this.UsgNow;
        List1.add(s);
        WordNow.setUsages(List1);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("edited!");
        alert.show();
        this.WNow = "";
        this.ProNow = "";
        this.TypeNow = "";
        this.ExpNow = "";
        this.UsgNow = "";
    }
    public void AddW(ActionEvent event) {
        this.groupWord.toBack();
        this.SubmitS.setText("");
        this.suggest("");
        this.groupAdd.toFront();
    }
    public void TextWordAdd(KeyEvent event) {
        this.WNow = this.WordAdd.getText();
    }
    public void TextProAdd(KeyEvent event) {
        this.ProNow = this.ProAdd.getText();
    }
    public void TextTypeAdd(KeyEvent event) {
        this.TypeNow = this.TypeAdd.getText();
    }
    public void TextExpAdd(KeyEvent event) {
        this.ExpNow = this.ExpAdd.getText();
    }
    public void TextUsgAdd(KeyEvent event) {
        this.UsgNow = this.UsgAdd.getText();
    }
    public void SaveAdd(ActionEvent event) {
        Word New = new Word();
        New.setWord(WNow);
        New.setPronunciation(this.ProNow);
        New.setWord_type(this.TypeNow);
        ArrayList<String> List = new ArrayList<String>();
        String s = this.ExpNow;
        List.add(s);
        New.setExplanations(List);
        ArrayList<String> List1 = new ArrayList<String>();
        s = this.UsgNow;
        List1.add(s);
        New.setUsages(List1);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(Dicmana.addWord(New));
        alert.show();
        this.WNow = "";
        this.ProNow = "";
        this.TypeNow = "";
        this.ExpNow = "";
        this.UsgNow = "";
    }

}
