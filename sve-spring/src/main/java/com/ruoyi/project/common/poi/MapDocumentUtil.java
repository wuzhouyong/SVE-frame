package com.ruoyi.project.common.poi;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

public class MapDocumentUtil {
    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    private String sheetName;
    private SXSSFWorkbook wb;
    private SXSSFSheet sheet;

    private final List<Map<String, Object>> list;
    private final List<Map<String, Object>> columns;
    private final List<Map<String, Object>> fields;
    private int maxDepth;

    private final String titleName;
    private final String fieldName;
    private final String childrenName = "children";
    private final String firstColName = "first_col";
    private final String lastColName = "last_col";

    public MapDocumentUtil(List<Map<String, Object>> list) {
        this(list, null, "name", "field");
    }

    public MapDocumentUtil(List<Map<String, Object>> list, List<Map<String, Object>> columns, String titleName, String fieldName) {
        fields = new ArrayList<>();
        if (list == null) {
            list = new ArrayList<>();
        }
        this.list = list;
        if (columns == null) {
            columns = new ArrayList<>();
        }
        this.columns = columns;
        this.titleName = titleName;
        this.fieldName = fieldName;
    }

    public Map<String, Object> createColumn(String name, String field) {
        Map<String, Object> column = new LinkedHashMap<>();
        column.put(this.titleName, name);
        column.put(this.fieldName, field);
        return column;
    }

    public Map<String, Object> createColumn(String name, List<Map<String, Object>> children) {
        Map<String, Object> column = new LinkedHashMap<>();
        column.put(this.titleName, name);
        column.put(this.childrenName, children);
        return column;
    }

    public Map<String, Object> createColumn(String name, Map<String, Object>... children) {
        Map<String, Object> column = new LinkedHashMap<>();
        column.put(this.titleName, name);
        column.put(this.childrenName, Arrays.asList(children));
        return column;
    }

    public void addColumn(String name, String field) {
        this.columns.add(createColumn(name, field));
    }

    public void addColumn(String name, List<Map<String, Object>> children) {
        this.columns.add(createColumn(name, children));
    }

    public void addColumn(String name, Map<String, Object>... children) {
        this.columns.add(createColumn(name, children));
    }

    public AjaxResult exportExcel(String sheetName) {
        this.sheetName = sheetName;
        this.wb = new SXSSFWorkbook(500);
        return exportExcel();
    }

    private AjaxResult exportExcel() {
        OutputStream out = null;
        try {
            sheet = wb.createSheet(sheetName);
            generateHeader(this.columns);
            this.fields.sort((a, b) -> (int) b.get(this.firstColName) - (int) a.get(this.firstColName));
            generateData(this.list, maxDepth + 1);
            String filename = encodingFilename(sheetName);
            out = new FileOutputStream(getAbsoluteFile(filename));
            wb.write(out);
            return AjaxResult.success(filename);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    public String encodingFilename(String filename) {
        if (!StringUtils.endsWith(filename, ".xlsx")) {
            filename += ".xlsx";
        }
        return filename;
    }

    public String getAbsoluteFile(String filename) {
        String downloadPath = RuoYiConfig.getDownloadPath() + filename;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        return downloadPath;
    }

    private void generateHeader(List<Map<String, Object>> columns) {
        maxDepth = getMaxDepth(this.columns, 0);
        generateHeader(columns, null, maxDepth);
    }

    private void generateHeader(List<Map<String, Object>> columns, Map<String, Object> parent, int maxDepth) {
        int colIndex = 0, rowIndex = 0;
        String firstRowName = "first_row", lastRowName = "last_row";
        if (parent != null) {
            colIndex = (int) parent.get(this.firstColName);
            rowIndex = (int) parent.get(lastRowName) + 1;
        }
        for (Map<String, Object> column : columns) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            Cell cell = row.createCell(colIndex);
            cell.setCellValue(column.get(this.titleName).toString());
            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setWrapText(true);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cell.setCellStyle(cellStyle);
            column.put(firstRowName, rowIndex);
            column.put(lastRowName, rowIndex);
            column.put(this.firstColName, colIndex);
            column.put(this.lastColName, colIndex);
            if (column.get(this.childrenName) != null) {
                List<Map<String, Object>> children = (List<Map<String, Object>>) column.get(this.childrenName);
                column.put(this.lastColName, colIndex + children.size() - 1);
                generateHeader(children, column, maxDepth);
            } else {
                this.fields.add(column);
                column.put(lastRowName, maxDepth);
            }
            if (parent != null && (int) column.get(this.lastColName) > (int) parent.get(this.lastColName)) {
                parent.put(this.lastColName, column.get(this.lastColName));
            }
            if ((int) column.get(lastRowName) > (int) column.get(firstRowName) || (int) column.get(this.lastColName) > (int) column.get(this.firstColName)) {
                sheet.addMergedRegion(new CellRangeAddress((int) column.get(firstRowName), (int) column.get(lastRowName), (int) column.get(this.firstColName), (int) column.get(this.lastColName)));
            }
            colIndex = (int) column.get(this.lastColName) + 1;
        }
    }

    private int generateData(List<Map<String, Object>> list, int firstRow) {
        int lastRow = firstRow;
        int totalRow = 0;
        for (Map<String, Object> item : list) {
            Row row = sheet.getRow(lastRow);
            if (row == null) {
                row = sheet.createRow(lastRow);
            }
            if (item.get(this.childrenName) != null) {
                List<Map<String, Object>> children = (List<Map<String, Object>>) item.get(this.childrenName);
                int total = generateData(children, lastRow);
                lastRow += total - 1;
                totalRow += total;
            } else {
                totalRow++;
            }
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                Optional<Map<String, Object>> optional = this.fields.stream().filter(p -> p.get(this.fieldName).toString().equalsIgnoreCase(entry.getKey())).findFirst();
                if (optional.isPresent()) {
                    Map<String, Object> column = optional.get();
                    Object obj = entry.getValue();
                    String value = obj == null ? "" : obj.toString();
                    Cell cell = row.createCell((int) column.get(this.firstColName));
                    cell.setCellValue(value);
                    if (lastRow > firstRow) {
                        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, (int) column.get(this.firstColName), (int) column.get(this.lastColName)));
                    }
                }
            }
            lastRow++;
            firstRow = lastRow;
        }
        return totalRow;
    }

    private int getMaxDepth(List<Map<String, Object>> list, int depth) {
        int maxDepth = 0;
        for (Map<String, Object> item : list) {
            item.put("depth", depth);
            if (item.get(this.childrenName) != null) {
                List<Map<String, Object>> children = (List<Map<String, Object>>) item.get(this.childrenName);
                int res = getMaxDepth(children, depth + 1); //当只有一层children,depth+1，如果还有，则继续加1
                if (res > maxDepth) maxDepth = res; //当有子类有children时，返回children的深度，当children还有children时会加1
            }
            if (depth > maxDepth) maxDepth = depth;
        }
        return maxDepth;
    }

}

