package by.judoka.qr.image;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by
 *
 * @author: JUDOKA
 * Date: 6/7/2021
 */

@Getter
@Setter
@Named
@RequestScoped
public class UploaderImage implements Serializable {

    @Inject
    private Image image;

    private UploadedFile file;

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        image.convertFromUploadFile(file);
        FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}