package com.alibaba.excel.write.handler.impl;

import java.util.List;

import com.alibaba.excel.constant.OrderConstant;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

/**
 * fill cell style.
 *
 * @author Jiaju Zhuang
 */
@Slf4j
public class FillStyleCellWriteHandler implements CellWriteHandler {

    @Override
    public int order() {
        return OrderConstant.FILL_STYLE;
    }

    @Override
    public void afterCellDispose(CellWriteHandlerContext context) {
        List<WriteCellData<?>> cellDataList = context.getCellDataList();
        if (CollectionUtils.isEmpty(cellDataList) || cellDataList.size() > 1) {
            return;
        }
        WriteCellData<?> cellData = cellDataList.get(0);
        WriteCellStyle writeCellStyle = cellData.getWriteCellStyle();
        if (writeCellStyle == null) {
            return;
        }
        WriteWorkbookHolder writeWorkbookHolder = context.getWriteWorkbookHolder();
        context.getCell().setCellStyle(writeWorkbookHolder.createCellStyle(writeCellStyle));
    }

}
