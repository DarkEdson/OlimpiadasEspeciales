/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;

import Controller.AtletaJpaController;
import Controller.ControllerCrud;
import Entities.Atleta;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
//import javax.inject.Named;
//import javax.enterprise.context.Dependent;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.component.export.ExcelOptions;
import org.primefaces.component.export.PDFOptions;

/**
 *
 * @author axel.medina
 */
@ManagedBean(name="beanAtletas")
@ViewScoped
public class BeanAtleta implements Serializable {

    private static final long serialVersionUID = -8757285597845344548L;
    
    private ExcelOptions excelOpt;
     
    private PDFOptions pdfOpt;
    
    AtletaJpaController ControlTabla;
    ControllerCrud controlCrud;//REFERENCIA AL CONTROLLER ESTANDAR PARA EL CRUD
    /*VARIABLE PARA CONTENIDO DE LA FILA SELECCIONADA*/
    private Atleta selectedAtletas;
    /*VARIABLES PARA EL FORMULARIO*/


    /**
     * Creates a new instance of BeanInsertRoles
     */
     @PostConstruct
    public void initBeanAtletas() {
        this.controlCrud = new ControllerCrud();//REFERENCIA AL CONTROLLER ESTANDAR PARA EL CRUD
         this.ControlTabla = new AtletaJpaController();//REFERENCIA PARA EL CONTENIDO DE LA DATATABLE
         customizationOptions();
    }
    
    public void customizationOptions() {
        excelOpt = new ExcelOptions();
        excelOpt.setFacetBgColor("#F88017");
        excelOpt.setFacetFontSize("10");
        excelOpt.setFacetFontColor("#0000ff");
        excelOpt.setFacetFontStyle("BOLD");
        excelOpt.setCellFontColor("#00ff00");
        excelOpt.setCellFontSize("8");
         
        pdfOpt = new PDFOptions();
        pdfOpt.setFacetBgColor("#F88017");
        pdfOpt.setFacetFontColor("#0000ff");
        pdfOpt.setFacetFontStyle("BOLD");
        pdfOpt.setCellFontSize("12");
        
    }
 
    public ExcelOptions getExcelOpt() {
        return excelOpt;
    }
 
    public void setExcelOpt(ExcelOptions excelOpt) {
        this.excelOpt = excelOpt;
    }
 
    public PDFOptions getPdfOpt() {
        return pdfOpt;
    }
 
    public void setPdfOpt(PDFOptions pdfOpt) {
        this.pdfOpt = pdfOpt;
    }

        public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
         
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = header.getCell(i);
             
            cell.setCellStyle(cellStyle);
        }
    }
     
    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.LETTER);
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "images" + File.separator + "logoOSGT.png";
        String logo2 = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "images" + File.separator + "logoUMG.png";
         
        pdf.add(Image.getInstance(logo));
        pdf.add(Image.getInstance(logo2));
    }
    
    public List<Atleta> getAtleta() {
        return ControlTabla.findAtletaEntities();
    }
    
}
