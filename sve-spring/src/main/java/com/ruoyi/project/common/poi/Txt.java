package com.ruoyi.project.common.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.ArrayList;
import java.util.List;

/**
 * @author solang
 * @date 2021-03-23 9:38
 */
public class Txt {

    private String sheetName;

    private SXSSFWorkbook wb;

    private SXSSFSheet sheet;


    private final List<?> list;
    private final List<Column> columns;

    private final List<Column> fields;

    private int maxDepth;

    public Txt(List<?> list) {
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


    private void generateHeader(List<Column> columns, Column parent, int maxDepth) {//有父类对象，先给父类对象赋值，在传递给子
        //最后子节点运行结束，初始值colIndex=0，rowIndex=1
        //同级1分支结束，初始值变为15(上次结束拿到的值)
        //同级另1分支结束，初始值变为15(上次结束拿到的值)
        int colIndex = 0;
        int rowIndex = 0;

        //同级1分支(严重超标情况明细)最后子节点，从默认值0，0开始重新运行，在取父级1，1，0，14修改默认值，走到for里面，循环15次方法结束
        //执行同级1分支(严重超标情况明细)递归方法下面一段，此时默认值colIndex=0，rowIndex=1
        //修改默认值父级lastcol,没有跳出循环，接着循环同级1另一分支，默认值colIndex=15，rowIndex=1，不会走这段
        //进入同级1另一分支最后子节点递归方法，重新开始运行，默认值colIndex=0，rowIndex=0,在取父级1，1，15，18修改默认值
        //走到for里面，循环4次方法结束，同级1另一分支结束，不会走这里
        //父级0，0，0，14，默认值colIndex = 15(上次结束拿到的值)，rowIndex = 1(是压栈时赋的值)
        //最后执行总父类，默认值0，0之前已经运行，从递归方法开始执行
        //每一次递归，方法开始，默认值为0，0，for循环子类，有多少子，循环多少次

        //这里主要赋值是同级1另一分支最后子节点，取同级分支相同父类属性1，1，15，18，也就是先给父类属性赋值，这也就是为什么要在这里执行
        //是给另一分支最后子节点赋起始值，拿到父节点的属性
        if (parent != null) {
            colIndex = parent.getFirstCol();//现在看这个是不是有区别，不能取last,也不能不要
            rowIndex = parent.getLastRow() + 1;//不能取first,如果父级跨行，就不能取，如果没跨行就能取first
        }
        for (Column column : columns) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) row.createCell(rowIndex);
            //这四个值用来合并单元格,只有头需要合并
            column.setFirstRow(rowIndex);
            column.setLastRow(rowIndex);//后面需要修改
            column.setFirstCol(colIndex);
            column.setLastCol(colIndex);//后面需要修改
            if (column.getChildrens() != null && column.getChildrens().size() > 0) {//操作父类
                column.setLastCol(colIndex + column.getChildrens().size() - 1);
                generateHeader(column.getChildrens(), column, maxDepth);
            } else {//操作最后子节点 子类
                if (column.getWidth() > 0) {
                    //设置第colIndex列的列宽为column.getWidth()个字符宽度
                    this.sheet.setColumnWidth(colIndex, column.getWidth() * 256);
                }
                this.fields.add(column);//没问题
                column.setLastRow(maxDepth);//没问题
            }
            //同级1，第一次运行column.getLastCol()=14， parent.getLastCol()=1，设置最大值14
            // 运行结束 last+1,初始值colIndex=15，最后子节点有多少数据，colIndex就等于多少数据
            //同级1另一分支，第一次运行column.getLastCol()=18， parent.getLastCol()=14，设置最大值18
            if (parent != null && column.getLastCol() > parent.getLastCol()) {
                parent.setLastCol(column.getLastCol());//重新设置父级属性，方便传递给初始值，拿到上次结束值
            }
            //合并条件:只合并父级
            //同级1，第一次运行first=1   1   first=0  14 ->合并
            //同级另1分支，运行first=1   1   first=15  18 ->合并
            //运行总：first=0   0   first=0  18 ->合并，第一次就赋值，不用重新赋值
            if (column.getLastRow() > column.getFirstRow() || column.getLastCol() > column.getFirstCol()) {
                sheet.addMergedRegion(new CellRangeAddress(column.getFirstRow(), column.getLastRow(), column.getFirstCol(), column.getLastCol()));
            }
            Cell cell = row.getCell(column.getFirstCol());
            if (cell == null) cell = row.createCell(column.getFirstCol());
            cell.setCellValue(column.getName());
            colIndex = column.getLastCol() + 1;
        }
    }

    private <K, T extends TreeNode<K>> int getMaxDepth(List<T> list, int depth) {
        //初始值 maxDepth= 0，0，同级1分支递归开始默认值0，递归结束取循环，赋值2
        //同级另1分支运行，初始值和同级1分支一样，因为没有跳出循环，递归方法重运行，默认值0，结束初始值取上级返回值2，和同级1分支做比较，取最大值2
        //总级运行，初始值0，取上级最大值2，结束返回2
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


}
