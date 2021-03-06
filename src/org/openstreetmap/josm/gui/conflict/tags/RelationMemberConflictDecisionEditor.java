// License: GPL. For details, see LICENSE file.
package org.openstreetmap.josm.gui.conflict.tags;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

import org.openstreetmap.josm.gui.util.TableCellEditorSupport;
import org.openstreetmap.josm.gui.widgets.JosmComboBox;

public class RelationMemberConflictDecisionEditor extends JosmComboBox implements TableCellEditor {

    public RelationMemberConflictDecisionEditor() {
        super(RelationMemberConflictDecisionType.values());
        setOpaque(true);
        setRenderer(new RelationMemberConflictDecisionRenderer());
        tableCellEditorSupport = new TableCellEditorSupport(this);
    }
    /* --------------------------------------------------------------------------------- */
    /* TableCellEditor                                                                   */
    /* --------------------------------------------------------------------------------- */
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        setSelectedItem(value);
        this.originalValue = (RelationMemberConflictDecisionType)value;
        return this;
    }

    private TableCellEditorSupport tableCellEditorSupport;
    private RelationMemberConflictDecisionType originalValue;

    public void addCellEditorListener(CellEditorListener l) {
        tableCellEditorSupport.addCellEditorListener(l);
    }

    public void cancelCellEditing() {
        setSelectedItem(originalValue);
        tableCellEditorSupport.fireEditingCanceled();
    }

    public Object getCellEditorValue() {
        return getSelectedItem();
    }

    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    public void removeCellEditorListener(CellEditorListener l) {
        tableCellEditorSupport.removeCellEditorListener(l);
    }

    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    public boolean stopCellEditing() {
        tableCellEditorSupport.fireEditingStopped();
        return true;
    }
}
