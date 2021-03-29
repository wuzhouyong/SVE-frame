package com.ruoyi.project.common.poi;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class DocumentUtil {
    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    private String sheetName;

    private SXSSFWorkbook wb;

    private SXSSFSheet sheet;

    private final List<?> list;

    private final List<Column> columns;

    private final List<Column> fields;

    private int maxDepth;

    public DocumentUtil(List<?> list) {
        columns = new ArrayList<>();
        fields = new ArrayList<>();
        if (list == null) {
            list = new ArrayList<>();
        }
        this.list = list;
    }

    public Column createColumn(String name, String field, int width) {
        return new Column(name, field, width);
    }

    public Column createColumn(String name, List<Column> children) {
        return new Column(name, children);
    }

    public Column createColumn(String name, Column... children) {
        return new Column(name, Arrays.asList(children));
    }

    public void addColumn(String name, String field, int width) {
        this.columns.add(createColumn(name, field, width));
    }

    public void addColumn(String name, List<Column> children) {
        this.columns.add(createColumn(name, children));
    }

    public void addColumn(String name, Column... children) {
        this.columns.add(createColumn(name, children));
    }

    @Deprecated
    public void putColumn(String name, Column children) {
        Column column = getColumn(name, this.columns);
        if (column == null) {
            this.addColumn(name, children);
        } else {
            column.getChildrens().add(children);
        }
    }

    @Deprecated
    public void putColumn(String name, List<Column> children) {
        Column column = getColumn(name, this.columns);
        if (column == null) {
            this.addColumn(name, children);
        } else {
            column.getChildrens().addAll(children);
        }
    }

    @Deprecated
    public void putColumn(String name, Column... children) {
        Column column = getColumn(name, this.columns);
        if (column == null) {
            this.addColumn(name, children);
        } else {
            column.getChildrens().addAll(Arrays.asList(children));
        }
    }

    private Column getColumn(String name, List<Column> cols) {
        for (Column column : cols) {
            if (column.getField() == null) {
                if (column.getName().equals(name)) {
                    if (column.getChildrens() == null) {
                        column.setChildrens(new ArrayList<>());
                    }
                    return column;
                } else {
                    if (column.getChildrens() != null) {
                        Column c = getColumn(name, column.getChildrens());
                        if (c != null) {
                            return c;
                        }
                    }
                }
            }
        }
        return null;
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
            this.fields.sort((a, b) -> b.getFirstCol() - a.getFirstCol());
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

    private void generateHeader(List<Column> columns) {
        maxDepth = getMaxDepth(this.columns, 0);
        generateHeader(columns, null, maxDepth);
    }

    private void generateHeader(List<Column> columns, Column parent, int maxDepth) {
        int colIndex = 0, rowIndex = 0;
        if (parent != null) {
            colIndex = parent.getFirstCol();
            rowIndex = parent.getLastRow() + 1;
        }
        for (Column column : columns) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            column.setFirstRow(rowIndex);
            column.setLastRow(rowIndex);
            column.setFirstCol(colIndex);
            column.setLastCol(colIndex);
            if (column.getChildrens() != null && column.getChildrens().size() > 0) {
                column.setLastCol(colIndex + column.getChildrens().size() - 1);
                generateHeader(column.getChildrens(), column, maxDepth);
            } else {
                if (column.getWidth() > 0) {
                    this.sheet.setColumnWidth(colIndex, column.getWidth() * 256);
                }
                this.fields.add(column);
                column.setLastRow(maxDepth);
            }
            if (parent != null && column.getLastCol() > parent.getLastCol()) {
                parent.setLastCol(column.getLastCol());
            }
            if (column.getLastRow() > column.getFirstRow() || column.getLastCol() > column.getFirstCol()) {
                sheet.addMergedRegion(new CellRangeAddress(column.getFirstRow(), column.getLastRow(), column.getFirstCol(), column.getLastCol()));
            }
            Cell cell = row.createCell(column.getFirstCol());
            cell.setCellValue(column.getName());
            colIndex = column.getLastCol() + 1;
        }
    }

    private <T> int generateData(List<T> list, int firstRow) {
        int lastRow = firstRow;
        int totalRow = 0;
        int rowNum = 1;
        for (Object item : list) {
            Row row = sheet.getRow(lastRow);
            if (row == null) {
                row = sheet.createRow(lastRow);
            }
            if (this.isTree(item)) {
                TreeNode node = (TreeNode) item;
                if (node.getChildrens() != null && node.getChildrens().size() > 0) {
                    int total = generateData(node.getChildrens(), lastRow);
                    lastRow += total - 1;
                    totalRow += total;
                }
            } else {
                totalRow++;
            }
            Field[] fields = item.getClass().getDeclaredFields();
            for (Field field : fields) {
                Optional<Column> optional = this.fields.stream().filter(p -> p.getField().equals(field.getName())).findFirst();
                if (!optional.isPresent()) {
                    continue;
                }
                Column column = optional.get();
                try {
                    field.setAccessible(true);
                    String value = "";
                    if (field.getAnnotation(AutoIncrement.class) != null) {
                        value = String.valueOf(rowNum);
                    } else {
                        Object obj = field.get(item);
                        if (obj == null) {
                            PropertyDescriptor property = new PropertyDescriptor(field.getName(),item.getClass());
                            Method method = property.getReadMethod();
                            if (method.invoke(item) != null) {
                                obj = method.invoke(item);
                            }
                        }
                        if (obj != null) {
                            if (obj.getClass() == Boolean.class) {
                                value = (Boolean) obj ? "是" : "否";
                            } else if (obj.getClass() == Date.class) {
                                if (obj.getClass().isAnnotationPresent(JsonFormat.class)) {
                                    JsonFormat format = obj.getClass().getAnnotation(JsonFormat.class);
                                    value = DateUtils.parseDate((Date) obj, format.pattern());
                                } else {
                                    value = DateUtils.dateTime((Date) obj);
                                }
                            } else {
                                value = obj.toString();
                            }
                        }
                    }
                    Cell cell = row.createCell(column.getFirstCol());
                    cell.setCellValue(value);
                    if (lastRow > firstRow) {
                        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, column.getFirstCol(), column.getLastCol()));
                    }
                } catch (Exception ex) {
                    System.out.print(ex.getMessage());
                }
            }
            rowNum++;
            lastRow++;
            firstRow = lastRow;
        }
        return totalRow;
    }

    private <K, T extends TreeNode<K>> int getMaxDepth(List<T> list, int depth) {
        int maxDepth = 0;
        for (TreeNode item : list) {
            item.setDepth(depth);
            if (item.getChildrens() != null) {
                int res = getMaxDepth(item.getChildrens(), depth + 1);
                if (res > maxDepth) maxDepth = res;
            }
            if (depth > maxDepth) maxDepth = depth;
        }
        return maxDepth;
    }

    private <T> boolean isTree(T row) {
        Class<?> cls = row.getClass();
        Class<?>[] interfacesArray = cls.getInterfaces();//获取这个类的所以接口类数组
        for (Class<?> item : interfacesArray) {
            if (item == TreeNode.class) { //判断是否有继承的接口
                return true;
            }
        }
        return false;
    }
}

