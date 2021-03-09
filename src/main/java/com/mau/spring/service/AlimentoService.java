package com.mau.spring.service;

import com.mau.spring.model.AccesibleDTO;
import com.mau.spring.model.Alimento;
import com.mau.spring.model.AlimentoNotFoundException;
import com.mau.spring.repository.AlimentoRepository;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class AlimentoService {
    private final AlimentoRepository alimentoRepository;

    @Autowired
    public AlimentoService(AlimentoRepository alimentoRepository) {
        this.alimentoRepository = alimentoRepository;
    }

    public void addAlimento(Alimento nuevaAlimento) {
        alimentoRepository.save(nuevaAlimento);
    }

    public List<Alimento> getAll(String name) {
        if (isNull(name))
            return alimentoRepository.findAll();
        else
            return alimentoRepository.findByNombre(name);
    }

    public void setAccesible(AccesibleDTO accesibleDTO) throws AlimentoNotFoundException {
        Optional<Alimento> optionalAlimento = alimentoRepository.findById(accesibleDTO.getNumero());
        if (optionalAlimento.isPresent()) {
            Alimento alimentoAModificar = optionalAlimento.get();

            alimentoAModificar.setEsAccesible(accesibleDTO.isEsAccesible());
            alimentoRepository.save(alimentoAModificar);
        } else {
            throw new AlimentoNotFoundException();
        }

    }

    public void cargarTablas() {
        cargarTabla("Cereales", "http://www.argenfoods.unlu.edu.ar/Tablas/Grupo/Cereales.xls");
        cargarTabla("Vegetales", "http://www.argenfoods.unlu.edu.ar/Tablas/Grupo/Vegetales.xls");
        cargarTabla("Frutas", "http://www.argenfoods.unlu.edu.ar/Tablas/Grupo/Frutas.xls");
        cargarTabla("Grasas", "http://www.argenfoods.unlu.edu.ar/Tablas/Grupo/Grasas.xls");
        cargarTabla("Pescados", "http://www.argenfoods.unlu.edu.ar/Tablas/Grupo/Pescados.xls");
        cargarTabla("PescadosAG", "http://www.argenfoods.unlu.edu.ar/Tablas/Grupo/PescadosAG.xls");
        cargarTabla("Carnes", "http://www.argenfoods.unlu.edu.ar/Tablas/Grupo/Carnes.xls");
        cargarTabla("CarnesAG", "http://www.argenfoods.unlu.edu.ar/Tablas/Grupo/CarnesAG.xls");
        cargarTabla("Leche", "http://www.argenfoods.unlu.edu.ar/Tablas/Grupo/Leche.xls");
        cargarTabla("Huevo", "http://www.argenfoods.unlu.edu.ar/Tablas/Grupo/Huevo.xls");
        cargarTabla("ProdAZ", "http://www.argenfoods.unlu.edu.ar/Tablas/Grupo/ProdAz.xls");
        cargarTabla("Misc", "http://www.argenfoods.unlu.edu.ar/Tablas/Grupo/Misc.xls");

    }

    private void cargarTabla(String clasificacion, String Url) {
        try {
            InputStream in = new URL(Url).openStream();
            Path tmpPath = Paths.get(clasificacion + ".xls");
            Files.copy(in, tmpPath, StandardCopyOption.REPLACE_EXISTING);
            FileInputStream fis = new FileInputStream(tmpPath.toFile());
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            HSSFSheet sheet = wb.getSheetAt(0);

            HashMap<String, Integer> campos = getCamposPresentes(wb);

            //busca donde comienzan los alimentos
            int i = 8;

            HashMap<String, Object> valores = new HashMap<>();

            for (; i < sheet.getPhysicalNumberOfRows(); i++) { //cargar los N alimentos
                HSSFRow filaActual = sheet.getRow(i);

                if (filaActual.getCell(campos.get("nº")) != null && filaActual.getCell(campos.get("nº")).getCellType() == CellType.NUMERIC) //si existe la fila
                {
                    campos.keySet().forEach((campo) -> {
                        int columnaCampo = campos.get(campo);
                        HSSFCell celda = filaActual.getCell(columnaCampo);
                        CellType tipoCampo = celda.getCellType();
                        Object valor;
                        switch (tipoCampo) {
                            case NUMERIC: {
                                valor = celda.getNumericCellValue();
                                break;
                            }
                            case STRING: {
                                valor = celda.getStringCellValue();
                                break;
                            }
                            default: {
                                valor = null;
                            }
                        }
                        valores.put(campo, valor);
                    });

                    Alimento nuevoAlimento = new Alimento(
                            ((Double) valores.get("nº")).intValue(),
                            (String) valores.get("alimento"),
                            clasificacion,
                            checkString(valores, "genero_-_especie_-_variedad"),
                            checkDouble(valores, "energia"),
                            checkDouble(valores, "agua"),
                            checkDouble(valores, "proteinas"),
                            checkDouble(valores, "grasa_total"),
                            checkDouble(valores, "carbohidratos_totales"),
                            checkDouble(valores, "carbohidratos_disponibles"),
                            checkDouble(valores, "fibra_dietetica"),
                            checkDouble(valores, "cenizas"),
                            checkDouble(valores, "sodio"),
                            checkDouble(valores, "potasio"),
                            checkDouble(valores, "calcio"),
                            checkDouble(valores, "fosforo"),
                            checkDouble(valores, "hierro"),
                            checkDouble(valores, "zinc"),
                            checkDouble(valores, "tiamina"),
                            checkDouble(valores, "rivoflavina"),
                            checkDouble(valores, "niacina"),
                            checkDouble(valores, "vitamina_c"),
                            checkDouble(valores, "ac._grasos_saturados"),
                            checkDouble(valores, "ac._grasos_monoinsaturados"),
                            checkDouble(valores, "ac._grasos_poliinsaturados"),
                            checkDouble(valores, "colesterol"),
                            null
                    );

                    this.addAlimento(nuevoAlimento);
                    Files.deleteIfExists(tmpPath);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, Integer> getCamposPresentes(HSSFWorkbook wb) {
        HSSFSheet sheet = wb.getSheetAt(0);

        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
        HashMap<String,Integer> camposPresentes = new HashMap<>();
        //sector leer la fila 5 para saber que columnas son las que hay
        HSSFRow fila5 = sheet.getRow(4);
        for (Cell cell : fila5) {
            if (formulaEvaluator.evaluateInCell(cell).getCellType() == CellType.STRING) {
                String nombreCampo = cell.getStringCellValue();
                String nombreCampoParseado = nombreCampo.trim().toLowerCase().replace(" ", "_").replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
                camposPresentes.put(nombreCampoParseado, cell.getColumnIndex());
            }
        }
        return camposPresentes;
    }

    private Double checkDouble(HashMap<String, Object> valores, String nutriente) {
        Object valor = valores.get(nutriente);
        Double valorDouble;
        try {
            valorDouble = Double.parseDouble(valor.toString());
        } catch (NullPointerException | NumberFormatException e) {
            valorDouble = 0.0;
        }

        return valorDouble;
    }

    private String checkString(HashMap<String, Object> valores, String campo) {
        Object valor = valores.get(campo);
        if (valor == null)
            return "";
        return (String) valor;
    }

    public Alimento get(Integer alimentoId) throws AlimentoNotFoundException {

        Optional<Alimento> alimento = alimentoRepository.findById(alimentoId);
        if (alimento.isPresent())
            return alimento.get();
        else throw new AlimentoNotFoundException();
    }
}
