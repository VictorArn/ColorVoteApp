module ro.netvoip.voteapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens ro.netvoip.voteapp to javafx.fxml;
    exports ro.netvoip.voteapp;
}